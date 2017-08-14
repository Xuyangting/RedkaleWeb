/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor;

import com.reo.automation.qaoss.monitor.entity.Module;
import com.reo.automation.qaoss.monitor.filter.ModuleBean;
import com.reo.automation.qaoss.monitor.service.ModuleService;
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
import org.redkale.service.RetResult;
import org.redkale.source.Flipper;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/module/*")
public class ModuleServlet extends BaseServlet {

    @Resource
    private ModuleService moduleService;

    @WebAction(url = "/module/querysheet", actionid = ACTION_QUERY)
    public void querySheet(HttpRequest req, HttpResponse resp) {
        ModuleBean bean = req.getJsonParameter(ModuleBean.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("moduleid");
        resp.finishJson(moduleService.querySheet(flipper, bean));
    }

    @AuthIgnore
    @WebAction(url = "/module/js/queryall")
    public void queryJSAll(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("modulemap", moduleService.queryAll());
    }
    
    @AuthIgnore
    @WebAction(url = "/module/js/queryall2")
    public void queryJSAll2(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("modulemap2", moduleService.queryAll2());
    }
    
    @WebAction(url = "/module/create", actionid = ACTION_CREATE)
    public void createModule(HttpRequest req, HttpResponse resp) throws IOException {
        Module bean = req.getJsonParameter(Module.class, "bean");
        
        if(moduleService.queryAll().containsKey(bean.getModulename())) {
            RetResult ret = OssRetCodes.retResult(OssRetCodes.RET_NAME_REPEATED, "模块名称重复");
            resp.finishJson(ret);
            return;
        }
        
        long time = System.currentTimeMillis();
        bean.setCreatetime(time);
        bean.setCreator(currentUser(req).getMembername());
        bean.setUpdatetime(time);
        bean.setUpdater(currentUser(req).getMembername());
        
        moduleService.insert(bean);
        
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/module/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        Module bean = req.getJsonParameter(Module.class, "bean");
        
        if(moduleService.queryAll().containsKey(bean.getModulename()) && bean.getModuleid() != moduleService.queryAll().get(bean.getModulename())) {
            RetResult ret = OssRetCodes.retResult(OssRetCodes.RET_NAME_REPEATED, "模块名称重复");
            resp.finishJson(ret);
            return;
        }
        
        long time = System.currentTimeMillis();
        bean.setUpdatetime(time);
        bean.setUpdater(currentUser(req).getMembername());
        
        moduleService.update(bean);
        
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
