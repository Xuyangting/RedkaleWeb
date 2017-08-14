/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.filter.StatsAPIFilter;
import com.reo.automation.qaoss.interfaces.service.StatsAPIService;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import org.redkale.source.Flipper;

/**
 *
 * @author timen.xu
 */
@WebServlet("/interface/stats_api/*")
public class StatsAPIServlet extends BaseServlet {
    @Resource
    private StatsAPIService statsAPIService;
    
    @AuthIgnore
    @WebAction(url = "/interface/stats_api/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        StatsAPIFilter bean = req.getJsonParameter(StatsAPIFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(10000);
        resp.finishJson(statsAPIService.querySheet(flipper, bean));
    } 
}
