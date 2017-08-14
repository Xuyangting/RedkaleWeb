    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.TestSuiteEntity;
import com.reo.automation.qaoss.interfaces.service.TestSuiteService;
import com.reo.automation.qaoss.interfaces.filter.TestSuiteFilter;
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
@WebServlet("/interface/testsuite/*")
public class TestSuiteServlet extends BaseServlet{
    @Resource
    private TestSuiteService testSuiteService;
    
    @AuthIgnore
    @WebAction(url = "/interface/testsuite/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        TestSuiteFilter bean = req.getJsonParameter(TestSuiteFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("name");
        resp.finishJson(testSuiteService.querySheet(flipper, bean));
    } 
    
    @AuthIgnore
    @WebAction(url = "/interface/testsuite/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        TestSuiteFilter bean = req.getJsonParameter(TestSuiteFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("name");
        flipper.setLimit(1000);
        resp.finishJson(testSuiteService.querySheet(flipper, bean));
    }
    
    @WebAction(url = "/interface/testsuite/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        TestSuiteEntity bean = req.getJsonParameter(TestSuiteEntity.class, "bean");   
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        testSuiteService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/interface/testsuite/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        TestSuiteEntity bean = req.getJsonParameter(TestSuiteEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        testSuiteService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    } 
}
