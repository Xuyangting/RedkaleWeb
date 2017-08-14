/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author timen.xu
 */
@Entity
@Table(name = "app.app_casestep")
public class AppCaseStepEntity   extends BaseEntity{
    @Id
    private int id;
    
    private int case_id;
    
    private String step_type;
    
    private float step_run_id;
    
    private String step_method;
    
    private String parameter_1;
    
    private String parameter_2;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getStep_type() {
        return step_type;
    }

    public void setStep_type(String step_type) {
        this.step_type = step_type;
    }

    public float getStep_run_id() {
        return step_run_id;
    }

    public void setStep_run_id(float step_run_id) {
        this.step_run_id = step_run_id;
    }

    public String getStep_method() {
        return step_method;
    }

    public void setStep_method(String step_method) {
        this.step_method = step_method;
    }

    public String getParameter_1() {
        return parameter_1;
    }

    public void setParameter_1(String parameter_1) {
        this.parameter_1 = parameter_1;
    }

    public String getParameter_2() {
        return parameter_2;
    }

    public void setParameter_2(String parameter_2) {
        this.parameter_2 = parameter_2;
    }  
    
}
