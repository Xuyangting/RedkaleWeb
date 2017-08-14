/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira;

import com.reo.automation.qaoss.base.service.Constant;
import com.reo.automation.qaoss.base.utils.Utils;
import com.reo.automation.qaoss.jira.entity.JiraIssue;
import com.reo.automation.qaoss.jira.filter.JiraIssueBean;
import com.reo.automation.qaoss.jira.service.ComponentService;
import com.reo.automation.qaoss.jira.service.CustomFieldService;
import com.reo.automation.qaoss.jira.service.JiraIssueService;
import com.reo.automation.qaoss.jira.service.NodeassociationService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import org.redkale.service.RetResult;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/bug/*")
public class BugServlet extends BaseServlet{
    @Resource
    private JiraIssueService jiraIssueService;
    
    @Resource
    private CustomFieldService customFieldService;
    
    @Resource
    private ComponentService componentService;
    
    @Resource
    private NodeassociationService nodeassociationService;
    
    /**
     * 按项目进行有效缺陷统计
     * @param req
     * @param resp
     */
    @WebAction(url = "/bug/js/querybugbyproject", actionid=ACTION_QUERY)
    public void queryJSBugByProject(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("bugbyproject", jiraIssueService.countEffectiveBugByProject());
    }
    
    /**
     * 按创建者进行有效缺陷统计
     * @param req
     * @param resp 
     */
    @WebAction(url = "/bug/js/querybugbycreator", actionid=ACTION_QUERY)
    public void queryJSBugByCreator(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("bugbycreator", jiraIssueService.countEffectiveBugByCreator());
    }
    
    /**
     * 根据缺陷原因进行有效缺陷统计
     * @param req
     * @param resp 
     */
    @WebAction(url = "/bug/js/querybugbycause", actionid=ACTION_QUERY)
    public void queryJSBugByCause(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("bugbycause", customFieldService.countEffectiveBugByDefectCause());
    }
    
    /**
     * 按component统计指定project的有效缺陷
     * @param req
     * @param resp 
     */
    @WebAction(url = "/bug/queryprojectbugbycomp/", actionid=ACTION_QUERY)
    public void queryProjectBugByComponent(HttpRequest req, HttpResponse resp) {
        long projectId = Long.parseLong(req.getRequstURILastPath());
        Map<String, Integer> map = new HashMap<String, Integer> ();
        Map<Long, String> componentsMap = componentService.queryComponentsByPId(projectId);
        //根据project id查询指定项目的issue id列表
        List<Long> issueIds = jiraIssueService.queryBugListByPId(projectId);
        //根据issue id列表查询node association统计issue component记录
        Map<Long, Integer> naCountMap = nodeassociationService.countNodeassociation(issueIds);
        naCountMap.forEach((l, i) -> {
            map.put(componentsMap.get(l), i);
        });
        
        resp.finishJson(map);
    }
    
    /**
     * 
     * @param req
     * @param resp 
     */
    @WebAction(url = "/bug/querybycreated", actionid=ACTION_QUERY)
    public void queryByCreatedDate(HttpRequest req, HttpResponse resp) {
        try {
            JiraIssueBean bean = req.getJsonParameter(JiraIssueBean.class, "bean");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LongRange range = new LongRange(df.parse(bean.getBeginDate()).getTime(), df.parse(bean.getEndDate()).getTime());
            long max = range.getMax();
            long min = range.getMin();
            //判断开始时间与结束时间之间的大小关系
            if(min > max) {
                resp.finishJson(new RetResult(OssRetCodes.RET_PARAMS_ILLEGAL, "开始时间大于结束时间"));
                return;
            }
            //格式化开始时间与结束时间，开始时间取开始的毫秒数，结束时间取最后的毫秒数
            range.setMin(Utils.getBeginMils(min));
            range.setMax(Utils.getBeginMils(max) + Constant.ONE_DAY_MILLISECONDS - 1);
            
            List<JiraIssue> issues = jiraIssueService.queryAllList(bean);
            List<Long> timestamps = Utils.splitRange(range);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            
            Map<String, Long> map = new LinkedHashMap<String, Long>();
            for(long timestamp : timestamps) {
                long count = issues.stream().filter(e -> e.getCreated().getTime() >= timestamp).filter(e -> e.getCreated().getTime() <= timestamp + Constant.ONE_DAY_MILLISECONDS -1).count();
                String sdate = sdf.format(new Date(timestamp));
                if(count > 0)
                    map.put(sdate, count);
            }
            
            resp.finishJson(map);
        } catch (ParseException ex) {
            Logger.getLogger(BugServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
