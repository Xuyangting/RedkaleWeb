/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.filter;

import java.util.Date;
import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import org.redkale.source.FilterExpress;
import org.redkale.source.FilterGroup;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class JiraIssueBean implements FilterBean{
    private String issuetype;
    private String creator;
    private String assignee;
    private Long project;
    private String issuestatus;
        
    @FilterColumn(name="project", express=FilterExpress.NOTIN)
    private Long[] excludeprojectdis;
    
    @FilterColumn(name="creator", express=FilterExpress.IN)
    private String[] creators;
    
    @FilterColumn(name="issuestatus", express=FilterExpress.NOTIN)
    private String[] excludeissuestatuses;
    
    @FilterColumn(name="created", express=FilterExpress.GREATERTHANOREQUALTO)
    private String beginDate;
    
    @FilterColumn(name="created", express=FilterExpress.LESSTHANOREQUALTO)
    private String endDate;

    public String getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long[] getExcludeprojectids() {
        return this.excludeprojectdis;
    }

    public void setExckydeorojectids(Long[] projectids) {
        this.excludeprojectdis = projectids;
    }

    public String[] getCreators() {
        return creators;
    }

    public void setCreators(String[] creators) {
        this.creators = creators;
    }

    public String getIssuestatus() {
        return issuestatus;
    }

    public void setIssuestatus(String issuestatus) {
        this.issuestatus = issuestatus;
    }

    public String[] getExcludeissuestatuses() {
        return excludeissuestatuses;
    }

    public void setExcludeissuestatuses(String[] issuestatuses) {
        this.excludeissuestatuses = issuestatuses;
    }

    public Long[] getExcludeprojectdis() {
        return excludeprojectdis;
    }

    public void setExcludeprojectdis(Long[] excludeprojectdis) {
        this.excludeprojectdis = excludeprojectdis;
    }    

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
