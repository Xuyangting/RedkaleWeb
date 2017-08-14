/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.service;

import com.reo.automation.qaoss.jira.entity.Component;
import com.reo.automation.qaoss.jira.filter.ComponentBean;
import com.reo.automation.qaoss.base.service.BasedJiraService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jerry.ouyang
 */
public class ComponentService extends BasedJiraService{
    
    /**
     * 根据project id返回component对象列表
     * @param projectId
     * @return 
     */
    public List<Component> queryComponentListByPId(long projectId) {
        ComponentBean bean = new ComponentBean(projectId);
        return source.queryList(Component.class, bean);
    }
    
    /**
     * 根据project id返回component的map<Id, cname>
     * @param projectId
     * @return 
     */
    public Map<Long, String> queryComponentsByPId(long projectId) {
        Map<Long, String> result = new HashMap<Long, String>();
        queryComponentListByPId(projectId).forEach(e -> {
            result.put(e.getId(), e.getCname());
        });
        return result;
    }
}
