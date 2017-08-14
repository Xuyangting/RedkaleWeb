/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
public class Project extends BaseEntity {
    @Id
    private long id;
    
    private String pname;
    private String lead;
    private String pkey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }
    
    
}
