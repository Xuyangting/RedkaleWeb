/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.service;

import com.reo.automation.qaoss.testcase.entity.TestProject;
import com.reo.automation.qaoss.base.service.BasedTestLinkService;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import java.util.ArrayList;
import java.util.List;
import org.redkale.boot.Application;
import org.redkale.source.FilterBean;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author jerry.ouyang
 */
public class TestProjectService extends BasedTestLinkService{    
    public Sheet<TestProject> querySheet(Flipper flipper, FilterBean bean) {
        return source.querySheet(TestProject.class, flipper, bean);
    }
    
    public List<Integer> queryTestProjectsIds() {
        FilterBean bean = new BasedFilterBean();
        List<Integer> projectIds = new ArrayList<Integer>();
        source.queryList(TestProject.class, bean).forEach(e -> projectIds.add(e.getId()));
        return projectIds;
    }
    
    public static void main(String[] args) throws Exception {
        TestProjectService service = Application.singleton(TestProjectService.class);
        FilterBean bean = new BasedFilterBean();
        Flipper flipper = new Flipper();
        System.out.println(service.querySheet(flipper, bean).getTotal());
    }
}
