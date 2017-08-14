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
@Table(name = "customfieldvalue")
public class CustomFieldValue extends BaseEntity {
    @Id
    private long id;
    
    private long issue;
    private long customfield;
    private String stringvalue;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIssue() {
        return issue;
    }

    public void setIssue(long issue) {
        this.issue = issue;
    }

    public long getCustomfield() {
        return customfield;
    }

    public void setCustomfield(long customfield) {
        this.customfield = customfield;
    }

    public String getStringvalue() {
        return stringvalue;
    }

    public void setStringvalue(String stringvalue) {
        this.stringvalue = stringvalue;
    }

}
