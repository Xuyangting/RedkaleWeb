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
@Table(name = "customfieldoption")
public class CustomFieldOption extends BaseEntity {
    @Id
    private long id;
    
    private long customfield;
    private String customvalue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomfield() {
        return customfield;
    }

    public void setCustomfield(long customfield) {
        this.customfield = customfield;
    }

    public String getCustomvalue() {
        return customvalue;
    }

    public void setCustomvalue(String customvalue) {
        this.customvalue = customvalue;
    }
    
}
