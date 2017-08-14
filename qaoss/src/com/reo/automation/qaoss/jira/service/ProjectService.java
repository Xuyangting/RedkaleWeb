/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.service;

import com.reo.automation.qaoss.jira.entity.Project;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedJiraService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.redkale.boot.Application;
import org.redkale.source.FilterExpress;
import org.redkale.source.FilterNode;
import org.redkale.util.SelectColumn;

/**
 *
 * @author jerry.ouyang
 */
public class ProjectService extends BasedJiraService{
    //排除jira testing project
    private final Long[] excludeIds = {11200l};
    
    public List<Project> queryProjectList() {
        return source.queryList(Project.class, new BasedFilterBean());
    }
    
    /**
     * 只返回id pname的list
     * @return 
     */
    public List<Project> queryPIdNameList() {
        SelectColumn sc = new SelectColumn();
        sc.setColumns(new String[] {"id", "pname"});
        return source.queryList(Project.class, sc, new BasedFilterBean());
    }
    
    public Map<Long, String> queryProjectMap() {
        Map<Long, String> map = new HashMap<Long, String>();
        
        queryProjectList().forEach(e -> {
            if(!Arrays.asList(excludeIds).contains(e.getId()))
                map.put(e.getId(), e.getPname());
        });
        return map;
    }
    
    public List<Long> queryProjectIdList() {
        //排除jira testing project
        return source.queryColumnList("id", Project.class, FilterNode.create("id", FilterExpress.NOTEQUAL, 11200));
    }
    
    public static void main(String[] args) throws Exception {
        ProjectService service = Application.singleton(ProjectService.class);
        
        service.queryProjectIdList().forEach(System.out::println);
    }
}
