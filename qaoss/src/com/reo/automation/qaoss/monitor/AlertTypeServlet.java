/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor;

import com.reo.automation.qaoss.monitor.entity.AlertType;
import com.reo.automation.qaoss.monitor.filter.AlertTypeBean;
import com.reo.automation.qaoss.monitor.service.AlertTypeService;
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
@WebServlet("/alerttype/*")
public class AlertTypeServlet extends BaseServlet{
    @Resource
    private AlertTypeService alertTypeService;
    
    @WebAction(url = "/alerttype/querysheet", actionid = ACTION_QUERY)
    public void querySheet(HttpRequest req, HttpResponse resp) {
        AlertTypeBean bean = req.getJsonParameter(AlertTypeBean.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("alerttypeid");
        resp.finishJson(alertTypeService.querySheet(flipper, bean));
    }
    
    @AuthIgnore
    @WebAction(url = "/alerttype/js/queryall")
    public void queryJSAll(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("alerttypemap", alertTypeService.queryAll());
    }
    
    @WebAction(url = "/alerttype/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
       AlertType bean = req.getJsonParameter(AlertType.class, "bean");
        
        if(alertTypeService.queryAll().containsKey(bean.getAlerttypename())) {
            RetResult ret = OssRetCodes.retResult(OssRetCodes.RET_NAME_REPEATED, "类型名称重复");
            resp.finishJson(ret);
            return;
        }
        
        long time = System.currentTimeMillis();
        bean.setCreatetime(time);
        bean.setCreator(currentUser(req).getMembername());
        bean.setUpdatetime(time);
        bean.setUpdater(currentUser(req).getMembername());
        
        alertTypeService.insert(bean);
        
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/alerttype/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        AlertType bean = req.getJsonParameter(AlertType.class, "bean");
        
        if(alertTypeService.queryAll().containsKey(bean.getAlerttypename()) && bean.getAlerttypeid()!= alertTypeService.queryAll().get(bean.getAlerttypename())) {
            RetResult ret = OssRetCodes.retResult(OssRetCodes.RET_NAME_REPEATED, "类型名称重复");
            resp.finishJson(ret);
            return;
        }
        
        long time = System.currentTimeMillis();
        bean.setUpdatetime(time);
        bean.setUpdater(currentUser(req).getMembername());
        
        alertTypeService.update(bean);
        
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
