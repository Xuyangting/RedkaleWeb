/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.TestJobEntity;
import com.reo.automation.qaoss.interfaces.service.TestJobService;
import com.reo.automation.qaoss.interfaces.filter.TestJobFilter;
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
@WebServlet("/interface/testjob/*")
public class TestJobServlet extends BaseServlet{
    @Resource
    private TestJobService testjobService;
    
    @AuthIgnore
    @WebAction(url = "/interface/testjob/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        TestJobFilter bean = req.getJsonParameter(TestJobFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("name");
        resp.finishJson(testjobService.querySheet(flipper, bean));
    }  
    
    @AuthIgnore
    @WebAction(url = "/interface/testjob/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        TestJobFilter bean = req.getJsonParameter(TestJobFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("name");
        flipper.setLimit(1000);
        resp.finishJson(testjobService.querySheet(flipper, bean));
    }  
    
    @WebAction(url = "/interface/testjob/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        TestJobEntity bean = req.getJsonParameter(TestJobEntity.class, "bean");    
        
        // 创建时间
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        // 创建者
        bean.setCreator(currentUser(req).getMembername()); 
        
        testjobService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/interface/testjob/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        TestJobEntity bean = req.getJsonParameter(TestJobEntity.class, "bean");
        
        // 创建时间
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        // 创建者
        bean.setCreator(currentUser(req).getMembername());   
        
        testjobService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    } 
}
