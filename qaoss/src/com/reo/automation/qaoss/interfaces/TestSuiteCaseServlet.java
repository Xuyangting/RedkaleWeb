/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.TestSuiteCaseEntity;
import com.reo.automation.qaoss.interfaces.service.TestSuiteCaseService;
import com.reo.automation.qaoss.interfaces.service.TestCaseService;
import com.reo.automation.qaoss.interfaces.filter.TestSuiteCaseFilter;
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
@WebServlet("/interface/testsuitecase/*")
public class TestSuiteCaseServlet extends BaseServlet {
    @Resource
    private TestSuiteCaseService testsuiteService;
    
    @Resource
    private TestCaseService testCaseService;
    
    @AuthIgnore
    @WebAction(url = "/interface/testsuitecase/querysheet", actionid = ACTION_QUERY)
    public void queryByPackageid(HttpRequest req, HttpResponse resp) { 
        int packageid = req.getIntParameter("packageid", ACTION_QUERY);
        Flipper flipper = req.getFlipper();
        flipper.setSort("sno, id");
        resp.finishJson(testsuiteService.queryByPackageId(flipper, packageid));
    }  
    
    @AuthIgnore
    @WebAction(url = "/interface/testsuitecase/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        TestSuiteCaseFilter bean = req.getJsonParameter(TestSuiteCaseFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("sno, id");
        resp.finishJson(testsuiteService.querySheet(flipper, bean));
    }   
    
    @WebAction(url = "/interface/testsuitecase/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        TestSuiteCaseEntity bean = req.getJsonParameter(TestSuiteCaseEntity.class, "bean");    
        
        testsuiteService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    @WebAction(url = "/interface/testsuitecase/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        TestSuiteCaseEntity bean = req.getJsonParameter(TestSuiteCaseEntity.class, "bean");
        
        testsuiteService.insert(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    } 
    
    @WebAction(url = "/interface/testsuitecase/delete", actionid = ACTION_CREATE)
    public void deleteTestCase(HttpRequest req, HttpResponse resp) throws IOException {
        TestSuiteCaseEntity bean = req.getJsonParameter(TestSuiteCaseEntity.class, "bean");
        
        testsuiteService.delete(bean); 
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    } 
}
