/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase;

import com.reo.automation.qaoss.testcase.service.TcversionsService;
import com.reo.automation.qaoss.testcase.service.NodesHierarchyService;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import static org.redkale.oss.base.Services.ACTION_QUERY;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/testcase/*")
public class TestCaseServlet extends BaseServlet{
    @Resource
    private NodesHierarchyService nodesHierarchyService;
    
    @Resource
    private TcversionsService tcversionsService;
        
    @WebAction(url = "/testcase/js/querycasesbyproject", actionid = ACTION_QUERY)
    public void queryCasesByProject(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("caseprojectmap", nodesHierarchyService.countCaseByProject());
    }
    
    @WebAction(url = "/testcase/js/querycasesbyuser", actionid = ACTION_QUERY)
    public void queryCasesByUser(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("caseusermap", tcversionsService.countTcversionsByUser());
    }
}
