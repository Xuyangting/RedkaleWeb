/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppTestSuiteEntity;
import com.reo.automation.qaoss.app.filter.AppTestSuiteFilter;
import com.reo.automation.qaoss.app.service.AppTestSuiteService;
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
@WebServlet("/app/testsuite/*")
public class AppTestSuiteServlet  extends BaseServlet{
    @Resource
    private AppTestSuiteService appTestSuiteService;
    
    @AuthIgnore
    @WebAction(url = "/app/testsuite/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppTestSuiteFilter bean = req.getJsonParameter(AppTestSuiteFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        resp.finishJson(appTestSuiteService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/app/testsuite/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppTestSuiteFilter bean = req.getJsonParameter(AppTestSuiteFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(1000);
        resp.finishJson(appTestSuiteService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/testsuite/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestSuiteEntity bean = req.getJsonParameter(AppTestSuiteEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        appTestSuiteService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/testsuite/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestSuiteEntity bean = req.getJsonParameter(AppTestSuiteEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        appTestSuiteService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
