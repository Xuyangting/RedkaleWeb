/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.scheduler;

import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.scheduler.entity.CrontabEntity;
import com.reo.automation.qaoss.scheduler.service.CrontabService;
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
@WebServlet("/crontab/*")
public class CrontabServlet extends BaseServlet{
    @Resource
    private CrontabService crontabService;
    
    @AuthIgnore
    @WebAction(url = "/crontab/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        BasedFilterBean bean = new BasedFilterBean();
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        resp.finishJson(crontabService.querySheet(flipper, bean));
    }   
    
    @WebAction(url = "/crontab/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        CrontabEntity bean = req.getJsonParameter(CrontabEntity.class, "bean");     
        crontabService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/crontab/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        CrontabEntity bean = req.getJsonParameter(CrontabEntity.class, "bean");        
        crontabService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
