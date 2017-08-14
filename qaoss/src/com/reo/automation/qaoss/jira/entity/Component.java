/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name = "component")
public class Component  extends BaseEntity{
    @Id
    private long id;
    private long project;
    private String cname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProject() {
        return project;
    }

    public void setProject(long projectId) {
        this.project = projectId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
