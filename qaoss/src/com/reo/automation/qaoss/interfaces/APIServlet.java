/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.APIEntity;
import com.reo.automation.qaoss.interfaces.service.APIService;
import com.reo.automation.qaoss.interfaces.filter.APIFilter;
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
@WebServlet("/interface/api/*")
public class APIServlet extends BaseServlet{
    @Resource
    private APIService apiService;
    
    @WebAction(url = "/interface/api/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        APIFilter bean = req.getJsonParameter(APIFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        resp.finishJson(apiService.querySheet(flipper, bean));
    }  
    
    @WebAction(url = "/interface/api/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        APIFilter bean = req.getJsonParameter(APIFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        flipper.setLimit(1000);
        resp.finishJson(apiService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/interface/api/queryListByApiId", actionid = ACTION_QUERY)
    public void queryListOrderById(HttpRequest req, HttpResponse resp) { 
        APIFilter bean = req.getJsonParameter(APIFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("api_id");
        flipper.setLimit(1000);
        resp.finishJson(apiService.querySheet(flipper, bean));
    }
    
    @WebAction(url = "/interface/api/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        APIEntity bean = req.getJsonParameter(APIEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        apiService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/interface/api/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        APIEntity bean = req.getJsonParameter(APIEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        apiService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
