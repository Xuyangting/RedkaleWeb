/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.compare;

import com.reo.automation.qaoss.compare.entity.CompareExecuteMyselfEntity;
import com.reo.automation.qaoss.compare.filter.CompareExecuteMyselfFilter;
import com.reo.automation.qaoss.compare.service.CompareExecuteMyselfService;
import java.io.IOException;
import javax.annotation.Resource;
import org.redkale.net.http.HttpBaseServlet;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import static org.redkale.oss.base.Services.ACTION_CREATE;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import org.redkale.source.Flipper;

/**
 *
 * @author timen.xu
 */
@WebServlet("/compare/execute_myself/*")
public class CompareExecuteMyselfServlet extends BaseServlet{
    @Resource
    private CompareExecuteMyselfService compareExecuteService;
    
    @HttpBaseServlet.AuthIgnore
    @HttpBaseServlet.WebAction(url = "/compare/execute_myself/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        CompareExecuteMyselfFilter bean = req.getJsonParameter(CompareExecuteMyselfFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        resp.finishJson(compareExecuteService.querySheet(flipper, bean));
    } 

    
    @HttpBaseServlet.WebAction(url = "/compare/execute_myself/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        CompareExecuteMyselfEntity bean = req.getJsonParameter(CompareExecuteMyselfEntity.class, "bean");
        
        // 创建时间
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        // 创建者
        bean.setAuthor(currentUser(req).getMembername());
        
        compareExecuteService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
        
        // 刚创建的时间
        long execute_time = bean.getCtime();
        int test_id = compareExecuteService.queryIdByCtime(execute_time);
        // 调用Jenkins任务进行执行
        String command = "curl -X POST http://10.9.19.221:8080/job/Automation/job/AT_ALL_COMPARE_STOCK_MYSELF/buildWithParameters --user tom.weng:123456" + 
                " -d test_job_id=" + Integer.toString(test_id);     
        Runtime.getRuntime().exec(command);
    }
}
