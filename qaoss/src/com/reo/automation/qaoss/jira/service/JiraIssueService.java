/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.service;

import com.reo.automation.qaoss.jira.entity.JiraIssue;
import com.reo.automation.qaoss.jira.filter.JiraIssueBean;
import com.reo.automation.qaoss.base.service.BasedJiraService;
import com.reo.automation.qaoss.base.service.Constant;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.boot.Application;

/**
 *
 * @author jerry.ouyang
 */
public class JiraIssueService extends BasedJiraService {

    @Resource
    private ProjectService projectService;

    @Resource
    private JiraUserService jiraUserService;
    
    private Long[] excludeIds = {11200l};
    
    /**
     * 查找jira-tester组内成员创建的，未被拒绝的缺陷列表，不包含jira testing project的相关内容
     * @return 
     */
    public List<JiraIssue> queryEffectiveBugList() {
        List<String> testerNames = jiraUserService.queryJiraTestGroupMemNames();
        JiraIssueBean bean = new JiraIssueBean();
        bean.setIssuetype(Constant.BugType);
        bean.setExckydeorojectids(excludeIds);
        bean.setExcludeissuestatuses(new String[] {Constant.IssueRejectedStatus});
        bean.setCreators(testerNames.toArray(new String[testerNames.size()]));
        List<JiraIssue> issues = source.queryList(JiraIssue.class, bean);
        return issues;
    }
    
    public List<Long> queryEffectiveBugIds() {
        List<String> testerNames = jiraUserService.queryJiraTestGroupMemNames();
        JiraIssueBean bean = new JiraIssueBean();
        bean.setIssuetype(Constant.BugType);
        bean.setExckydeorojectids(excludeIds);
        bean.setExcludeissuestatuses(new String[] {Constant.IssueRejectedStatus});
        bean.setCreators(testerNames.toArray(new String[testerNames.size()]));
        return source.queryColumnList("id", JiraIssue.class, bean);
    }
    
    
    /**
     * 查找jira-tester组内成员创建的，Rejected状态的缺陷列表，不包含jira testing project的相关内容
     * @return 
     */
    public List<JiraIssue> queryRejectedBugList() {
        List<String> testerNames = jiraUserService.queryJiraTestGroupMemNames();
        JiraIssueBean bean = new JiraIssueBean();
        bean.setIssuetype(Constant.BugType);
        bean.setExckydeorojectids(excludeIds);
        bean.setIssuestatus(Constant.IssueRejectedStatus);
        bean.setCreators(testerNames.toArray(new String[testerNames.size()]));
        List<JiraIssue> issues = source.queryList(JiraIssue.class, bean);
        return issues;
    }


    /**
     * 根据project id查找jira-tester组内成员创建的非Rejected状态的缺陷列表
     * @param project
     * @return 
     */    
    public List<Long> queryBugListByPId(Long project) {
        List<String> testerNames = jiraUserService.queryJiraTestGroupMemNames();
        JiraIssueBean bean = new JiraIssueBean();
        bean.setIssuetype(Constant.BugType);
        bean.setProject(project);
        bean.setExcludeissuestatuses(new String[] {Constant.IssueRejectedStatus});
        bean.setCreators(testerNames.toArray(new String[testerNames.size()]));
        return source.queryColumnList("id", JiraIssue.class, bean);
    }
    
//    /**
//     * 根据project id统计jira-tester组内成员创建的非Rejected状态的缺陷数量
//     * @param project
//     * @return 
//     */
//    public int countBugByPId(Long project) {
//        return queryBugListByPId(project).size();
//    }
//    
    
    /**
     * 按项目对Bug进行统计
     *
     * @return
     */
    public Map<String, Integer> countEffectiveBugByProject() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<JiraIssue> bugs = queryEffectiveBugList();
        Map<String, String> users = jiraUserService.queryJiraTestGroupMembersMap();
        
        Map<Long, String> projectMap = projectService.queryProjectMap();
        
        //排除jira testing project
        
        for (JiraIssue bug : bugs) {
            String projectName = projectMap.get(bug.getProject());
            
            if (!map.containsKey(projectName)) {
                map.put(projectName, 1);
            } else {
                int value = map.get(projectName) + 1;
                map.put(projectName, value);
            }
        }
        return map;
    }

//    /**
//     * 按项目对bug进行统计,由于数据库请求过多暂时不予使用
//     * @return 
//     */
//    public Map<String, Integer> countEffectiveBugByProject1() {
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        Map<Long, String> projectMap = projectService.queryProjectMap();
//        JiraIssueBean bean = new JiraIssueBean();
//        
//        projectMap.forEach((k, v) -> {
//            bean.setIssuetype(Constant.BugType);
//            bean.setProject(k);
//            int count = source.getNumberResult(JiraIssue.class, FilterFunc.COUNT, "id", bean).intValue();
//            if(count > 0)
//                map.put(v, count);
//        });
//        
//        return map;
//    }
    
    /**
     * 
     * @return 
     */
    public Map<String, Integer> countEffectiveBugByCreator() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<JiraIssue> bugs = queryEffectiveBugList();
        Map<String, String> users = jiraUserService.queryJiraTestGroupMembersMap();

        bugs.forEach(e -> {
            String lowerUserName = e.getCreator().toLowerCase();       
            if (!(map.containsKey(users.get(lowerUserName)))) {
                map.put(users.get(lowerUserName), 1);
            } else {
                int value = map.get(users.get(lowerUserName)) + 1;
                map.put(users.get(lowerUserName), value);
            }
        });
        return map;
    }
    
    /**
     * 根据输入的查询条件查找所有的bug
     * @param bean
     * @return 
     */
    public List<JiraIssue> queryAllList(JiraIssueBean bean) {
        return source.queryList(JiraIssue.class, bean);
    }

    public static void main(String[] args) throws Exception {
        JiraIssueService service = Application.singleton(JiraIssueService.class);
//        System.out.println(service.queryEffectiveBugList().size());
//        System.out.println(service.queryRejectedBugList().size());
//
//        long begin = System.currentTimeMillis();
//        service.countEffectiveBugByProject().forEach((k, v) -> System.out.println(k + "--->" + v));
//        long end = System.currentTimeMillis();
//        System.out.println(end - begin);
//
//        begin = System.currentTimeMillis();
//        service.countEffectiveBugByCreator().forEach((k, v) -> System.out.println(k + "--->" + v));
//        end = System.currentTimeMillis();
//        System.out.println(end - begin);
        JiraIssueBean bean = new JiraIssueBean();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bean.setBeginDate(sdf.format(new Date(1480521600000l)));        
        bean.setEndDate(sdf.format(new Date(1480521600000l + Constant.ONE_DAY_MILLISECONDS - 1)));
        List<JiraIssue> list = service.queryAllList(bean);
        System.out.println(list.size());
        System.out.println(list.get(0).toString());
    }
}
