/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.ModulesEntity;
import com.reo.automation.qaoss.interfaces.service.ModulesService;
import com.reo.automation.qaoss.interfaces.filter.ModulesFilter;
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
@WebServlet("/interface/module/*")
public class ModulesServlet extends BaseServlet{
    @Resource
    private ModulesService moduleService;
    
    @AuthIgnore
    @WebAction(url = "/interface/module/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        ModulesFilter bean = req.getJsonParameter(ModulesFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        resp.finishJson(moduleService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/interface/module/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        ModulesFilter bean = req.getJsonParameter(ModulesFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        flipper.setLimit(1000);
        resp.finishJson(moduleService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/interface/module/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        ModulesEntity bean = req.getJsonParameter(ModulesEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        moduleService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/interface/module/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        ModulesEntity bean = req.getJsonParameter(ModulesEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        moduleService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
