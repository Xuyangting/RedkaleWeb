/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.compare;

import com.reo.automation.qaoss.compare.entity.StockEntity;
import com.reo.automation.qaoss.compare.filter.StockFilter;
import com.reo.automation.qaoss.compare.service.StockService;
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
@WebServlet("/compare/stock/*")
public class StockServlet extends BaseServlet {
    @Resource
    private StockService stockService;
    
    @HttpBaseServlet.AuthIgnore
    @HttpBaseServlet.WebAction(url = "/compare/stock/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        StockFilter bean = req.getJsonParameter(StockFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("stock_type");
        resp.finishJson(stockService.querySheet(flipper, bean));
    } 
    
    @HttpBaseServlet.AuthIgnore
    @HttpBaseServlet.WebAction(url = "/compare/stock/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        StockFilter bean = req.getJsonParameter(StockFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("stock_type");
        flipper.setLimit(1000);
        resp.finishJson(stockService.querySheet(flipper, bean));
    } 
    
    @HttpBaseServlet.WebAction(url = "/compare/stock/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        StockEntity bean = req.getJsonParameter(StockEntity.class, "bean");    
        
        stockService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @HttpBaseServlet.WebAction(url = "/compare/stock/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        StockEntity bean = req.getJsonParameter(StockEntity.class, "bean");
        
        stockService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @HttpBaseServlet.WebAction(url = "/compare/stock/delete", actionid = ACTION_CREATE)
    public void deleteAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        StockEntity bean = req.getJsonParameter(StockEntity.class, "bean");
        
        stockService.delete(bean);
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
