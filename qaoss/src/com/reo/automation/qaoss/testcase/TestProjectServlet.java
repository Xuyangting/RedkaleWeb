/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase;

import com.reo.automation.qaoss.testcase.service.TestProjectService;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import org.redkale.source.FilterBean;
import org.redkale.source.Flipper;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/testproject/*")
public class TestProjectServlet extends BaseServlet{
    @Resource
    private TestProjectService service;
    
    @WebAction(url ="/testproject/queryall")
    public void query(HttpRequest req, HttpResponse resp) {
        Flipper flipper = new Flipper();
        FilterBean bean = new BasedFilterBean();
        resp.finishJson(service.querySheet(flipper, bean));
    }    
}
