/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.WechatEntity;
import com.reo.automation.qaoss.interfaces.filter.WechatFilter;
import com.reo.automation.qaoss.interfaces.service.WechatService;
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
@WebServlet("/interface/wechat/*")
public class WechatServlet extends BaseServlet {
    @Resource
    private WechatService wechatService;
    
    @AuthIgnore
    @WebAction(url = "/interface/wechat/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        WechatFilter bean = req.getJsonParameter(WechatFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        resp.finishJson(wechatService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/interface/wechat/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        WechatFilter bean = req.getJsonParameter(WechatFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        flipper.setLimit(1000);
        resp.finishJson(wechatService.querySheet(flipper, bean));
    } 
    
    @WebAction(url = "/interface/wechat/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        WechatEntity bean = req.getJsonParameter(WechatEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        wechatService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/interface/wechat/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        WechatEntity bean = req.getJsonParameter(WechatEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        wechatService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
