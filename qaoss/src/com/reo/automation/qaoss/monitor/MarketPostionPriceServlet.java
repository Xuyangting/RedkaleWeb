/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor;

import com.reo.automation.qaoss.monitor.entity.MarketPostionPrice;
import com.reo.automation.qaoss.monitor.filter.MarketPostionPriceBean;
import com.reo.automation.qaoss.monitor.service.MarketPostionPriceService;
import javax.annotation.Resource;
import org.redkale.convert.json.JsonConvert;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import org.redkale.service.RetResult;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/mpp/*")
public class MarketPostionPriceServlet extends BaseServlet {
    @Resource
    private MarketPostionPriceService service;
    
    @AuthIgnore
    @WebAction(url = "/mpp/insert")
    public void insert(HttpRequest req, HttpResponse resp) {
        String content = req.getBodyUTF8();
        if(content == null || content == "null" || content == "")
            resp.finishJson(new RetResult(OssRetCodes.RET_PARAMS_ILLEGAL, "输入内容错误!"));
        MarketPostionPrice bean = JsonConvert.root().convertFrom(MarketPostionPrice.class, content);
        service.insert(bean);
        resp.finishJson(new RetResult(0));
    }
    
    @WebAction(url = "/mpp/querylist", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) {
        MarketPostionPriceBean bean = req.getJsonParameter(MarketPostionPriceBean.class, "bean");
        resp.finishJson(service.queryList(bean));
    }
}
