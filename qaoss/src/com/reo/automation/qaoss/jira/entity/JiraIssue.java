/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name = "jiraissue")
public class JiraIssue extends BaseEntity {
    @Id
    private int id;
    private long project;
    private String reporter;
    private String assignee;
    private String creator;
    private String issuetype;
    private String priority;
    private String issuestatus;
    private Date created;
    private Date updated;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getProject() {
        return project;
    }

    public void setProject(long project) {
        this.project = project;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(String issuetype) {
        this.issuetype = issuetype;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIssuestatus() {
        return issuestatus;
    }

    public void setIssuestatus(String issuestatus) {
        this.issuestatus = issuestatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
