/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppTestMethodEntity;
import com.reo.automation.qaoss.app.filter.AppTestMethodFilter;
import com.reo.automation.qaoss.app.service.AppTestMethodService;
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
@WebServlet("/app/testMethod/*")
public class AppTestMethodServlet extends BaseServlet{
    @Resource
    private AppTestMethodService  AppTestMethodService;
    
    @AuthIgnore
    @WebAction(url = "/app/testMethod/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppTestMethodFilter bean = req.getJsonParameter(AppTestMethodFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(1000);
        resp.finishJson(AppTestMethodService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/testMethod/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestMethodEntity bean = req.getJsonParameter(AppTestMethodEntity.class, "bean");    
        
        AppTestMethodService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/testMethod/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppTestMethodEntity bean = req.getJsonParameter(AppTestMethodEntity.class, "bean");

        AppTestMethodService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
