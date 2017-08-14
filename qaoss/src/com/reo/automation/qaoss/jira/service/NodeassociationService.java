/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.service;

import com.reo.automation.qaoss.jira.entity.Nodeassociation;
import com.reo.automation.qaoss.jira.filter.NodeassociationBean;
import com.reo.automation.qaoss.base.service.BasedJiraService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.redkale.boot.Application;

/**
 *
 * @author jerry.ouyang
 */
public class NodeassociationService extends BasedJiraService{    
    /**
     * 根据jira issue id查询nodeassociation的sink_node_id列表
     * @param source_node_ids
     * @return 
     */
    public List<Long> querySinkNodeIdList(List<Long> source_node_ids) {
        NodeassociationBean bean = new NodeassociationBean();
        bean.setSource_node_ids(source_node_ids);
        return source.queryColumnList("sink_node_id", Nodeassociation.class, bean);
    }
    
    /**
     * 根据jira issue id查询nodeassociation的sink_node_id列表，然后根据sink_node_id进行统计
     * @param source_node_ids
     * @return 
     */
    public Map<Long, Integer> countNodeassociation(List<Long> source_node_ids) {
        Map<Long, Integer> count = new HashMap<Long, Integer> ();
        querySinkNodeIdList(source_node_ids).forEach(e -> {
            if(count.containsKey(e))
                count.put(e, count.get(e) + 1);
            else
                count.put(e, 1);
        });
        return count;
    }
    
    public static void main(String[] args) throws Exception {
        NodeassociationService service = Application.singleton(NodeassociationService.class);
        JiraIssueService jiraIssueService = Application.singleton(JiraIssueService.class);
        ComponentService cService = Application.singleton(ComponentService.class);
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<Long> issueIds = jiraIssueService.queryBugListByPId(10100l);
        Map<Long, String> componentMap = cService.queryComponentsByPId(10100l);
        service.countNodeassociation(issueIds).forEach((l, i) -> map.put(componentMap.get(l), i));
        map.forEach((s, i) -> System.out.println(s + "--->" + i));
    }
}
