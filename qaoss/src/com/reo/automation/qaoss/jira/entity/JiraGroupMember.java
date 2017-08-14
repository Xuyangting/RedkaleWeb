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
@Table(name = "cwd_membership")
public class JiraGroupMember  extends BaseEntity{
    @Id
    private int id;
    private int parent_id;
    private int child_id;
//    private String lower_child_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getChild_id() {
        return child_id;
    }

    public void setChild_id(int child_id) {
        this.child_id = child_id;
    }

//    public String getLower_child_name() {
//        return lower_child_name;
//    }
//
//    public void setLower_child_name(String lower_child_name) {
//        this.lower_child_name = lower_child_name;
//    }
}
