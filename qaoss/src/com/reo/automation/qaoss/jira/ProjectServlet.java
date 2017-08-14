/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira;

import com.reo.automation.qaoss.jira.service.ProjectService;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/project/*")
public class ProjectServlet extends BaseServlet{
    @Resource
    private ProjectService projectService;
    
    @WebAction(url = "/project/js/queryprojects")
    public void queryJSProjects(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("allprojects", projectService.queryPIdNameList());
    }
}
