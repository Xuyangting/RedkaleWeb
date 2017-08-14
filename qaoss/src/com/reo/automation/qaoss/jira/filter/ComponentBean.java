/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.filter;

import org.redkale.source.FilterBean;

/**
 *
 * @author jerry.ouyang
 */
public class ComponentBean implements FilterBean{
    private long project; 

    public ComponentBean() {
    }
    
    public ComponentBean(long projectId) {
        this.project = projectId;
    }
    

    public long getProject() {
        return project;
    }

    public void setProject(long projectId) {
        this.project = projectId;
    }
    
}
