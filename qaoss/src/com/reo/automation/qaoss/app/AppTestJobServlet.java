/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppTestJobEntity;
import com.reo.automation.qaoss.app.filter.AppTestJobFilter;
import com.reo.automation.qaoss.app.service.AppTestJobService;
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
@WebServlet("/app/testjob/*")
public class AppTestJobServlet  extends BaseServlet{
    @Resource
    private AppTestJobService appTestJobService;
    
    @AuthIgnore
    @WebAction(url = "/app/testjob/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppTestJobFilter bean = req.getJsonParameter(AppTestJobFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        resp.finishJson(appTestJobService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/app/testjob/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppTestJobFilter bean = req.getJsonParameter(AppTestJobFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(1000);
        resp.finishJson(appTestJobService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/testjob/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestJobEntity bean = req.getJsonParameter(AppTestJobEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        // 创建者
        bean.setCreator(currentUser(req).getMembername()); 
        
        appTestJobService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/testjob/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestJobEntity bean = req.getJsonParameter(AppTestJobEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        // 创建者
        bean.setCreator(currentUser(req).getMembername()); 
        
        appTestJobService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
