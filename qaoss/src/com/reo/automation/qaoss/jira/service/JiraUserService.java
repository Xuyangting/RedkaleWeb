/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.service;

import com.reo.automation.qaoss.jira.entity.JiraGroupMember;
import com.reo.automation.qaoss.jira.entity.JiraUser;
import com.reo.automation.qaoss.base.service.BasedJiraService;
import com.reo.automation.qaoss.base.service.Constant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.redkale.boot.Application;
import org.redkale.source.FilterExpress;
import org.redkale.source.FilterNode;

/**
 *
 * @author jerry.ouyang
 */
public class JiraUserService extends BasedJiraService{
    
    /**
     * 查询jira tester组中的成员列表, 返回格式为JiraUser List
     * @return 
     */
    public List<JiraUser> queryJiraTestGroupMembers() {
        List<JiraGroupMember> groupMembers = source.queryList(JiraGroupMember.class, FilterNode.create("parent_id", Constant.JiraTestGroupId));
        List<Integer> userids = new ArrayList<Integer>();
        groupMembers.forEach(e -> userids.add(e.getChild_id()));
        List<JiraUser> testers = source.queryList(JiraUser.class, FilterNode.create("id", FilterExpress.IN, (Serializable) userids));
        
        return testers;
    }
    
    /**
     * 查询jira tester组中的成员列表, 返回格式为lower user name List
     * @return 
     */
    public List<String> queryJiraTestGroupMemNames() {
        List<JiraGroupMember> groupMembers = source.queryList(JiraGroupMember.class, FilterNode.create("parent_id", Constant.JiraTestGroupId));
        List<Integer> userids = new ArrayList<Integer>();
        groupMembers.forEach(e -> userids.add(e.getChild_id()));
        
        List<String> testernamess = source.queryColumnList("lower_user_name", JiraUser.class, FilterNode.create("id", FilterExpress.IN, (Serializable) userids));//   .queryList(JiraUser.class, FilterNode.create("id", FilterExpress.IN, (Serializable) userids));
        
        return testernamess;
    }
    
    /**
     * 查询 jira tester组中的成员列表，返回格式为lower_user_name, display_name
     * @return 
     */
    public Map<String, String> queryJiraTestGroupMembersMap() {
        Map<String, String> map = new HashMap<String, String>();
        List<JiraGroupMember> groupMembers = source.queryList(JiraGroupMember.class, FilterNode.create("parent_id", Constant.JiraTestGroupId));
        List<Integer> userids = new ArrayList<Integer>();
        groupMembers.forEach(e -> userids.add(e.getChild_id()));
        List<JiraUser> testers = source.queryList(JiraUser.class, FilterNode.create("id", FilterExpress.IN, (Serializable) userids));
        testers.forEach(e -> map.put(e.getLower_user_name(), e.getDisplay_name()));
        return map;
    }
    
    public static void main(String[] args) throws Exception {
        JiraUserService service = Application.singleton(JiraUserService.class);
        
        service.queryJiraTestGroupMembers().forEach(e -> System.out.println(e.getDisplay_name()));
        
        service.queryJiraTestGroupMemNames().forEach(System.out::println);       
        
        
    }
}
