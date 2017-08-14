/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.TestCaseEntity;
import com.reo.automation.qaoss.interfaces.service.TestCaseService;
import com.reo.automation.qaoss.interfaces.filter.TestCaseFilter;
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
@WebServlet("/interface/testcase/*")
public class TestCaseServlet extends BaseServlet{
   @Resource
    private TestCaseService testcaseService;
    
    @AuthIgnore
    @WebAction(url = "/interface/testcase/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        TestCaseFilter bean = req.getJsonParameter(TestCaseFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        resp.finishJson(testcaseService.querySheet(flipper, bean));
    }   
    
    @AuthIgnore
    @WebAction(url = "/interface/testcase/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        TestCaseFilter bean = req.getJsonParameter(TestCaseFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        flipper.setLimit(1000);
        resp.finishJson(testcaseService.querySheet(flipper, bean));
    }  
    
    @WebAction(url = "/interface/testcase/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        TestCaseEntity bean = req.getJsonParameter(TestCaseEntity.class, "bean");    
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        testcaseService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/interface/testcase/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        TestCaseEntity bean = req.getJsonParameter(TestCaseEntity.class, "bean");
        
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        
        testcaseService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    } 
}
