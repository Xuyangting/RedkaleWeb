/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor;

import com.reo.automation.qaoss.monitor.entity.BuildJob;
import com.reo.automation.qaoss.monitor.filter.BuildJobBean;
import com.reo.automation.qaoss.monitor.service.BuildJobService;
import com.reo.automation.qaoss.monitor.service.ModuleService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import static org.redkale.oss.base.Services.ACTION_CREATE;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import static org.redkale.oss.base.Services.ACTION_UPDATE;
import org.redkale.service.RetResult;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/buildjob/*")
public class BuildJobServlet extends BaseServlet{
    @Resource
    private BuildJobService jobService;
    
    @Resource
    private ModuleService moduleService;    
    
    @WebAction(url = "/buildjob/querysheet", actionid = ACTION_QUERY)
    public void querySheet(HttpRequest req, HttpResponse resp) {
        BuildJobBean bean = req.getJsonParameter(BuildJobBean.class, "bean");
        Flipper flipper = req.getFlipper();
        Map<Integer, String> map = moduleService.queryAll2();
        Sheet<BuildJob> sheet = jobService.querySheet(flipper, bean);
        List<BuildJob> list = sheet.list();
        
        for(BuildJob job : list) {
            job.setModulename(map.get(job.getModuleid()));
        }
        sheet.setRows(list);
        resp.finishJson(sheet);
    }
    
    @WebAction(url = "/buildjob/create", actionid = ACTION_CREATE)
    public void create(HttpRequest req, HttpResponse resp) {
        BuildJob job = req.getJsonParameter(BuildJob.class, "bean");
        
        BuildJob bean = jobService.findBuildJob(job.getName());
        if(bean != null) {
            resp.finishJson(new RetResult(OssRetCodes.RET_NAME_REPEATED, "类型名称重复"));
            return;
        }
        
        jobService.create(job);
        resp.finishJson(new RetResult(0));
    }
    
    @WebAction(url = "/buildjob/update", actionid = ACTION_UPDATE)
    public void update(HttpRequest req, HttpResponse resp) {
        BuildJob job = req.getJsonParameter(BuildJob.class, "bean");
        
        BuildJob bean = jobService.findBuildJob(job.getName());
        if(bean != null && bean.getBuildjobid() != job.getBuildjobid()) {
            resp.finishJson(new RetResult(OssRetCodes.RET_NAME_REPEATED, "类型名称重复"));
            return;
        }
        
        jobService.update(job);
        resp.finishJson(new RetResult(0));
    }
    
    @AuthIgnore
    @WebAction(url = "/buildjob/js/querylist")
    public void queryJsList(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("allbuildjob", jobService.queryList());
    }
}
