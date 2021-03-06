/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppMobileEntity;
import com.reo.automation.qaoss.app.filter.AppMobileFilter;
import com.reo.automation.qaoss.app.service.AppMobileService;
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
@WebServlet("/app/mobile/*")
public class AppMobileServlet extends BaseServlet  {
    @Resource
    private AppMobileService appMobileService;
    
    @AuthIgnore
    @WebAction(url = "/app/mobile/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppMobileFilter bean = req.getJsonParameter(AppMobileFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        resp.finishJson(appMobileService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/app/mobile/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppMobileFilter bean = req.getJsonParameter(AppMobileFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(1000);
        resp.finishJson(appMobileService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/mobile/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppMobileEntity bean = req.getJsonParameter(AppMobileEntity.class, "bean");    
        appMobileService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/mobile/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppMobileEntity bean = req.getJsonParameter(AppMobileEntity.class, "bean");
        appMobileService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
