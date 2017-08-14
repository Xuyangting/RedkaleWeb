/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.ApplicationEntity;
import com.reo.automation.qaoss.app.filter.ApplicationFilter;
import com.reo.automation.qaoss.app.service.ApplicationService;
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
@WebServlet("/app/application/*")
public class ApplicationServlet  extends BaseServlet {
    @Resource
    private ApplicationService applicationService;
    
    @AuthIgnore
    @WebAction(url = "/app/application/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        ApplicationFilter bean = req.getJsonParameter(ApplicationFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        resp.finishJson(applicationService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/app/application/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        ApplicationFilter bean = req.getJsonParameter(ApplicationFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(1000);
        resp.finishJson(applicationService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/application/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        ApplicationEntity bean = req.getJsonParameter(ApplicationEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        applicationService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/application/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        ApplicationEntity bean = req.getJsonParameter(ApplicationEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        applicationService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
