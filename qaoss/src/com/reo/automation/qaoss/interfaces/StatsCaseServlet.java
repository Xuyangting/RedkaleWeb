/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.filter.StatsCaseFilter;
import com.reo.automation.qaoss.interfaces.service.StatsCaseService;
import javax.annotation.Resource;
import org.redkale.net.http.HttpBaseServlet;
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
@WebServlet("/interface/stats_case/*")
public class StatsCaseServlet extends BaseServlet {
    @Resource
    private StatsCaseService statsCaseService;
    
    @HttpBaseServlet.AuthIgnore
    @HttpBaseServlet.WebAction(url = "/interface/stats_case/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        StatsCaseFilter bean = req.getJsonParameter(StatsCaseFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("id");
        flipper.setLimit(10000);
        resp.finishJson(statsCaseService.querySheet(flipper, bean));
    } 
}
