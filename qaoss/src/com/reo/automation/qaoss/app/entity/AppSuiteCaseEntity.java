/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author timen.xu
 */
@Entity
@Table(name = "app.app_suitecase")
public class AppSuiteCaseEntity extends BaseEntity{
    @Id
    private int id;
    
    private int suite_id;
    
    private int case_id;
    
    @Transient
    private String case_name;

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuite_id() {
        return suite_id;
    }

    public void setSuite_id(int suite_id) {
        this.suite_id = suite_id;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    } 
}
