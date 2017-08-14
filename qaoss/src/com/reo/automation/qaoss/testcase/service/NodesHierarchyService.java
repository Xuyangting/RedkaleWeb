/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.service;

import com.reo.automation.qaoss.testcase.filter.NodesHierarchyBean;
import com.reo.automation.qaoss.testcase.entity.NodesHierarchy;
import com.reo.automation.qaoss.base.service.BasedTestLinkService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.util.SelectColumn;

/**
 *
 * @author jerry.ouyang
 */
public class NodesHierarchyService extends BasedTestLinkService{
    @Resource
    private TestProjectService testProjectService;
    
    private enum NodeType {
        TESTPROJECT(1), TESTSUITE(2), TESTCASE(3);
        
        private int value;
        
        private NodeType(int val) {
            value = val;
        }
        
        private void setValue(int val) {
            value = val;
        }
        
        private int getValue() {
            return value;
        }
    };
    
    /**
     * 根据project id查询project name
     * @param testProjectId
     * @return 
     */
    public String queryTestProjectName(int testProjectId) {
        NodesHierarchyBean bean = new NodesHierarchyBean();
        bean.setId(testProjectId);
        bean.setNode_type_id(NodeType.TESTPROJECT.getValue());
        SelectColumn sc = new SelectColumn();
        sc.setColumns(new String[]{"id", "name"});
        NodesHierarchy nh = source.find(NodesHierarchy.class, sc, bean);
        return nh.getName();
    }
    /**
     * 根据project id查询出下级test suite id列表
     * @param testProjectId
     * @return 
     */
    private List<Integer> querySuiteIdsByProjectId(int testProjectId) {
        NodesHierarchyBean bean = new NodesHierarchyBean();
        int [] projectId = new int[1];
        projectId[0] = testProjectId;
        bean.setParent_ids(projectId);
        return source.queryColumnList("id", NodesHierarchy.class, bean);
    }
    /**
     * 根据test suite id列表查询出所有的子test suite id列表
     * @param testSuiteIds
     * @param allSuiteIds 
     */
    private void querySuiteIdsBySuiteIds(List<Integer> testSuiteIds, List<Integer> allSuiteIds) {
        NodesHierarchyBean bean = new NodesHierarchyBean();
        int[] suiteIds = new int[testSuiteIds.size()];
        for(int i=0; i<testSuiteIds.size(); i++) {
            suiteIds[i] = testSuiteIds.get(i);
        }
        bean.setParent_ids(suiteIds);
        bean.setNode_type_id(NodeType.TESTSUITE.getValue());
        List<Integer> result = source.queryColumnList("id", NodesHierarchy.class, bean);
        
        if(result.size() > 0) {
            allSuiteIds.addAll(result);
            querySuiteIdsBySuiteIds(result, allSuiteIds);
        }
    }
    /**
     * 根据test suite id列表查询出所有的子test case id列表
     * @param allSuiteIds
     * @return 
     */
    private List<Integer> queryCaseIdsBySuiteIds(List<Integer> allSuiteIds) {
        NodesHierarchyBean bean = new NodesHierarchyBean();
        int[] suiteIds = new int[allSuiteIds.size()];
        for(int i=0; i<allSuiteIds.size(); i++) {
            suiteIds[i] = allSuiteIds.get(i);
        }
        bean.setParent_ids(suiteIds);
        bean.setNode_type_id(NodeType.TESTCASE.getValue());
        
        List<Integer> result = source.queryColumnList("id", NodesHierarchy.class, bean);
        return result;
    }
    /**
     * 根据project id查询出所有的testcase id
     * @param testProjectId
     * @return 
     */
    private List<Integer> queryCaseIdsByProjectId(int testProjectId) {
        List<Integer> allSuiteIds = new ArrayList();
        //根据project id查询出下级test suite id列表
        allSuiteIds = querySuiteIdsByProjectId(testProjectId);
        //根据已知的test suite id列表查询所有的子、孙test suite id
        querySuiteIdsBySuiteIds(querySuiteIdsByProjectId(testProjectId), allSuiteIds);
        //返回test case id列表
        return queryCaseIdsBySuiteIds(allSuiteIds);
    }
    
    public Map<String, Integer> countCaseByProject() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<Integer> list = testProjectService.queryTestProjectsIds();
        
        list.forEach(e -> map.put(queryTestProjectName(e), queryCaseIdsByProjectId(e).size()));
        return map;
    }
        
    public static void main(String[] args) throws Exception {
        NodesHierarchyService service = Application.singleton(NodesHierarchyService.class);
                
        int testProjectId = 1;
        System.out.println(service.queryTestProjectName(testProjectId) + "---" + service.queryCaseIdsByProjectId(testProjectId).size());
        
        testProjectId = 2839;
        System.out.println(service.queryTestProjectName(testProjectId) + "---" + service.queryCaseIdsByProjectId(testProjectId).size());
        
        testProjectId = 3250;
        System.out.println(service.queryTestProjectName(testProjectId) + "---" + service.queryCaseIdsByProjectId(testProjectId).size());
        
        testProjectId = 5355;
        System.out.println(service.queryTestProjectName(testProjectId) + "---" + service.queryCaseIdsByProjectId(testProjectId).size());
        
        testProjectId = 7558;
        System.out.println(service.queryTestProjectName(testProjectId) + "---" + service.queryCaseIdsByProjectId(testProjectId).size());
    }
}
