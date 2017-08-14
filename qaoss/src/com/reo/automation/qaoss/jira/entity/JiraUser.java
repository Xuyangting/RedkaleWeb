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
@Table(name = "cwd_user")
public class JiraUser extends BaseEntity {
    @Id
    private int id;
    
    private String lower_user_name;
    private String display_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLower_user_name() {
        return lower_user_name;
    }

    public void setLower_user_name(String lower_user_name) {
        this.lower_user_name = lower_user_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
  
    
}
