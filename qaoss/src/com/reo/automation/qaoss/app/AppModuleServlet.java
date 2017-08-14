/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppModuleEntity;
import com.reo.automation.qaoss.app.filter.AppModuleFilter;
import com.reo.automation.qaoss.app.service.AppModuleService;
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
@WebServlet("/app/module/*")
public class AppModuleServlet  extends BaseServlet {
    @Resource
    private AppModuleService appModuleService;
    
    @AuthIgnore
    @WebAction(url = "/app/module/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppModuleFilter bean = req.getJsonParameter(AppModuleFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("platform_id");
        resp.finishJson(appModuleService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/app/module/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppModuleFilter bean = req.getJsonParameter(AppModuleFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("platform_id");
        flipper.setLimit(1000);
        resp.finishJson(appModuleService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/module/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppModuleEntity bean = req.getJsonParameter(AppModuleEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        appModuleService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/module/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppModuleEntity bean = req.getJsonParameter(AppModuleEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        appModuleService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
