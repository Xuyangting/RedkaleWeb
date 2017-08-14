/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.ElementsEntity;
import com.reo.automation.qaoss.app.filter.ElementsFilter;
import com.reo.automation.qaoss.app.service.ElementsService;
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
@WebServlet("/app/element/*")
public class ElementsServlet extends BaseServlet {
    @Resource
    private ElementsService elementsService;
    
    @AuthIgnore
    @WebAction(url = "/app/element/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        ElementsFilter bean = req.getJsonParameter(ElementsFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("platform_id,module_id,page");
        resp.finishJson(elementsService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/app/element/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        ElementsFilter bean = req.getJsonParameter(ElementsFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("platform_id,module_id,page");
        flipper.setLimit(1000);
        resp.finishJson(elementsService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/app/element/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        ElementsEntity bean = req.getJsonParameter(ElementsEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        elementsService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/app/element/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        ElementsEntity bean = req.getJsonParameter(ElementsEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        elementsService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
