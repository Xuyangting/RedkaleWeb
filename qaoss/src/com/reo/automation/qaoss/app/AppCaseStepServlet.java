/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppCaseStepEntity;
import com.reo.automation.qaoss.app.filter.AppCaseStepFilter;
import com.reo.automation.qaoss.app.service.AppCaseStepService;
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
import static org.redkale.oss.base.Services.ACTION_UPDATE;
import org.redkale.source.Flipper;

/**
 *
 * @author timen.xu
 */
@WebServlet("/app/casestep/*")
public class AppCaseStepServlet  extends BaseServlet{
    @Resource
    private AppCaseStepService appCaseStepService;
    
    
    @HttpBaseServlet.WebAction(url = "/app/casestep/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppCaseStepFilter bean = req.getJsonParameter(AppCaseStepFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("step_run_id");
        resp.finishJson(appCaseStepService.querySheet(flipper, bean));
    } 
    
    @HttpBaseServlet.WebAction(url = "/app/casestep/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppCaseStepFilter bean = req.getJsonParameter(AppCaseStepFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("step_run_id");
        flipper.limit(10000);
        resp.finishJson(appCaseStepService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/casestep/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppCaseStepEntity bean = req.getJsonParameter(AppCaseStepEntity.class, "bean");    
        appCaseStepService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/casestep/delete", actionid = ACTION_UPDATE)
    public void deleteModule(HttpRequest req, HttpResponse resp) throws IOException {
        AppCaseStepEntity bean = req.getJsonParameter(AppCaseStepEntity.class, "bean");    
        appCaseStepService.delete(bean);
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/casestep/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppCaseStepEntity bean = req.getJsonParameter(AppCaseStepEntity.class, "bean");
        appCaseStepService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
