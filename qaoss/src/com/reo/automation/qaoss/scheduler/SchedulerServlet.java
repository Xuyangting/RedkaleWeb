/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.scheduler;

import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.scheduler.entity.SchedulerEntity;
import com.reo.automation.qaoss.scheduler.service.SchedulerService;
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
@WebServlet("/scheduler/*")
public class SchedulerServlet extends BaseServlet{
    @Resource
    private SchedulerService schedulerService;
    
    @HttpBaseServlet.AuthIgnore
    @HttpBaseServlet.WebAction(url = "/scheduler/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        BasedFilterBean bean = new BasedFilterBean();
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        resp.finishJson(schedulerService.querySheet(flipper, bean));
    }   
    
    @HttpBaseServlet.WebAction(url = "/scheduler/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        SchedulerEntity bean = req.getJsonParameter(SchedulerEntity.class, "bean");     
        schedulerService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @HttpBaseServlet.WebAction(url = "/scheduler/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        SchedulerEntity bean = req.getJsonParameter(SchedulerEntity.class, "bean");        
        schedulerService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
