/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces;

import com.reo.automation.qaoss.interfaces.entity.ExecuteEntity;
import com.reo.automation.qaoss.interfaces.filter.ExecuteFilter;
import com.reo.automation.qaoss.interfaces.service.ExecuteService;
import com.reo.automation.qaoss.interfaces.service.TestJobService;
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
import org.redkale.service.RetResult;

/**
 *
 * @author timen.xu
 */
@WebServlet("/interface/execute/*")
public class ExecuteServlet  extends BaseServlet{
    @Resource
    private ExecuteService executeService;
    
    @Resource
    private TestJobService testJobService;
    
    /**
     * 查询所有
     * @param req
     * @param resp 
     */
    @WebAction(url = "/interface/execute/queryall", actionid = ACTION_QUERY)
    public void queryAll(HttpRequest req, HttpResponse resp) { 
        ExecuteFilter bean = req.getJsonParameter(ExecuteFilter.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("execute_time desc");
        resp.finishJson(executeService.querySheet(flipper, bean));
    }   
    
    /**
     * 更新
     * @param req
     * @param resp
     * @throws IOException 
     */
    @WebAction(url = "/interface/execute/update", actionid = ACTION_UPDATE)
    public void updateModule(HttpRequest req, HttpResponse resp) throws IOException {
        ExecuteEntity bean = req.getJsonParameter(ExecuteEntity.class, "bean"); 
        
        // 更新时间
        long time = System.currentTimeMillis();
        bean.setExecute_time(time);
        // 更新者
        bean.setExecute_by_author(currentUser(req).getMembername());
        
        executeService.update(bean);    
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
    
    /**
     * 创建
     * @param req
     * @param resp
     * @throws IOException  
     */
    @WebAction(url = "/interface/execute/create", actionid = ACTION_CREATE)
    public void createAlertType(HttpRequest req, HttpResponse resp) throws IOException {
        ExecuteEntity bean = req.getJsonParameter(ExecuteEntity.class, "bean");
        
        if(testJobService.queryByJobId(bean.getJob_id()) == "0") {
            RetResult ret = OssRetCodes.retResult(OssRetCodes.RET_TESTJOB_ERROR, "任务编号不存在");
            resp.finishJson(ret);
            return;
        }
        
        // 创建时间
        long time = System.currentTimeMillis();
        bean.setExecute_time(time);
        // 创建者
        bean.setExecute_by_author(currentUser(req).getMembername());
        // 默认尚未生成测试报告
        bean.setTest_report("<p>尚未生成测试报告，请稍等...</p>");
        // 测试任务 info
        int job_id = bean.getJob_id();
        String job_english_name = testJobService.queryByJobId(job_id);
        bean.setJob_name(job_english_name);
        // 插入数据库 
        executeService.insert(bean); 
        // 刚创建的时间
        long execute_time = bean.getExecute_time();
        int execute_id = executeService.queryIDByExecuteTime(execute_time);
        // 获取jenkins链接
        String jenkins_link = testJobService.queryJenkinsByJobId(job_id);
        // 反馈给web，提交执行任务成功
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
        // 调用Jenkins任务进行执行
        String command = "curl -X POST " + jenkins_link + "/buildWithParameters --user tom.weng:123456 -d jobName=" + job_english_name + " -d taskId=" + Integer.toString(execute_id);     
        Runtime.getRuntime().exec(command);
    }
    
    /**
     * 上传测试报告
     * @param req
     * @param resp
     * @throws IOException 
     */
    @AuthIgnore
    @WebAction(url = "/interface/execute/upload/", comment = "上传测试报告")
    public void updateface(HttpRequest req, HttpResponse resp) throws IOException {
        resp.finishJson(OssRetCodes.retResult(OssRetCodes.SUCCESS));
    }
}
