/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.filter.HostFilter;
import com.reo.automation.qaoss.interfaces.entity.HostEntity;
import com.reo.automation.qaoss.interfaces.service.HostService;
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
@WebServlet("/host/*")
public class HostServlet extends BaseServlet{
    @Resource
    private HostService hostService;
    
    @AuthIgnore
    @WebAction(url = "/host/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        HostFilter bean = req.getJsonParameter(HostFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("env, id");
        resp.finishJson(hostService.querySheet(flipper, bean));
    }   
    
    @AuthIgnore
    @WebAction(url = "/host/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        HostFilter bean = req.getJsonParameter(HostFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("env, id");
        flipper.setLimit(10000);
        resp.finishJson(hostService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/host/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        HostEntity bean = req.getJsonParameter(HostEntity.class, "bean");     
        hostService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/host/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        HostEntity bean = req.getJsonParameter(HostEntity.class, "bean");        
        hostService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
