/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppSuiteCaseEntity;
import com.reo.automation.qaoss.app.filter.AppSuiteCaseFilter;
import com.reo.automation.qaoss.app.service.AppSuiteCaseService;
import java.io.IOException;
import javax.annotation.Resource;
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
@WebServlet("/app/testsuitecase/*")
public class AppSuiteCaseServlet extends BaseServlet  {
    @Resource
    private AppSuiteCaseService appSuiteCaseService;
    
    @AuthIgnore
    @WebAction(url = "/app/testsuitecase/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppSuiteCaseFilter bean = req.getJsonParameter(AppSuiteCaseFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("case_id");
        resp.finishJson(appSuiteCaseService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/testsuitecase/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppSuiteCaseEntity bean = req.getJsonParameter(AppSuiteCaseEntity.class, "bean");
        
        appSuiteCaseService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    } 
    
    @WebAction(url = "/app/testsuitecase/delete", actionid = ACTION_CREATE)
    public void deleteTestCase(HttpRequest req, HttpResponse resp) throws IOException {
        AppSuiteCaseEntity bean = req.getJsonParameter(AppSuiteCaseEntity.class, "bean");
        
        appSuiteCaseService.delete(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    } 
}
