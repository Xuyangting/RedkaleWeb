/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppTestCaseEntity;
import com.reo.automation.qaoss.app.filter.AppTestCaseFilter;
import com.reo.automation.qaoss.app.service.AppTestCaseService;
import java.io.IOException;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import static org.redkale.oss.base.Services.ACTION_CREATE;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import static org.redkale.oss.base.Services.ACTION_UPDATE;
import org.redkale.source.Flipper;

/**
 *
 * @author timen.xu
 */
@WebServlet("/app/testcase/*")
public class AppTestCaseServlet  extends BaseServlet{
    @Resource
    private AppTestCaseService appTestCaseService;
    
    @AuthIgnore
    @WebAction(url = "/app/testcase/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppTestCaseFilter bean = req.getJsonParameter(AppTestCaseFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        resp.finishJson(appTestCaseService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/app/testcase/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppTestCaseFilter bean = req.getJsonParameter(AppTestCaseFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(1000);
        resp.finishJson(appTestCaseService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/testcase/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestCaseEntity bean = req.getJsonParameter(AppTestCaseEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        appTestCaseService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/testcase/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestCaseEntity bean = req.getJsonParameter(AppTestCaseEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        appTestCaseService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
