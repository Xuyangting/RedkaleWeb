/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app;

import com.reo.automation.qaoss.app.entity.AppExecuteEntity;
import com.reo.automation.qaoss.app.filter.AppExecuteFilter;
import com.reo.automation.qaoss.app.service.AppExecuteService;
import com.reo.automation.qaoss.app.service.AppMobileService;
import com.reo.automation.qaoss.app.service.AppTestJobService;
import com.reo.automation.qaoss.app.service.AppTestSuiteService;
import com.reo.automation.qaoss.app.service.ApplicationService;
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
import org.redkale.source.Flipper;

/**
 *
 * @author timen.xu
 */
@WebServlet("/app/execute/*")
public class AppExecuteServlet  extends BaseServlet {
    @Resource
    private AppExecuteService appExecuteService;
    
    @Resource
    private AppMobileService appMobileService;
    
    @Resource
    private AppTestJobService appTestJobService;
    
    @Resource
    private AppTestSuiteService appTestSuiteService;
    
    @Resource
    private ApplicationService applicationService;
    
    @HttpBaseServlet.AuthIgnore
    @HttpBaseServlet.WebAction(url = "/app/execute/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        AppExecuteFilter bean = req.getJsonParameter(AppExecuteFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        resp.finishJson(appExecuteService.querySheet(flipper, bean));
    } 
    
    @HttpBaseServlet.AuthIgnore
    @HttpBaseServlet.WebAction(url = "/app/execute/queryList", actionid = ACTION_QUERY)
    public void queryList(HttpRequest req, HttpResponse resp) { 
        AppExecuteFilter bean = req.getJsonParameter(AppExecuteFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("ctime desc");
        flipper.setLimit(1000);
        resp.finishJson(appExecuteService.querySheet(flipper, bean));
    } 

    
    /**
     * 创建
     * @param req
     * @param resp
     * @throws IOException  
     */
    @WebAction(url = "/app/execute/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        AppExecuteEntity bean = req.getJsonParameter(AppExecuteEntity.class, "bean");
        
        // 创建时间
        long time = System.currentTimeMillis();
        bean.setCtime(time);
        // 创建者
        bean.setExecute_by_author(currentUser(req).getMembername());
        String app_address = bean.getApp_address();
        
        int mobile_id = bean.getMobile_id();
        String test_platform = appMobileService.queryMobileTypeById(mobile_id);
        String test_device = appMobileService.queryMobileUDIDById(mobile_id);
        int test_job_id = bean.getJob_id();
        String test_env = bean.getTest_env();
        String jenkins_link = appTestJobService.queryJenkinsById(test_job_id);
        int test_suite_id = appTestJobService.querySuiteById(test_job_id);
        int test_platform_id = appTestSuiteService.queryPlatformById(test_suite_id);
        String test_app = applicationService.queryEnameById(test_platform_id);
        
        // 默认尚未生成测试报告
        bean.setTest_report("<a href=\"" + jenkins_link + "\" target=\"_Blank\">" + jenkins_link + "</a>");    
        // 插入数据库 
        appExecuteService.insert(bean); 
        // 刚创建的时间
        long execute_time = bean.getCtime();
        int test_execute_id = appExecuteService.queryIdByCtime(execute_time);
        
        // 反馈给web，提交执行任务成功
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));

        // 调用Jenkins任务进行执行
        String command = "curl -X POST " + jenkins_link + 
                "/buildWithParameters --user tom.weng:123456" + 
                " -d TestApp=" + test_app +
                " -d TestPlatform=" + test_platform + 
                " -d TestDevice=" + test_device + 
                " -d TestEnv=" + test_env + 
                " -d TestJobId=" + Integer.toString(test_job_id) + 
                " -d TestExecuteId=" + Integer.toString(test_execute_id) + 
                " -d TestAppAddress=http://10.9.16.12" + app_address;     
        Runtime.getRuntime().exec(command);
    }
}
