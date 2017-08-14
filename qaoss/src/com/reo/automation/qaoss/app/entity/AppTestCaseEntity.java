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
@Table(name = "app.app_testcase")
public class AppTestCaseEntity  extends BaseEntity{
    @Id
    private int id;
    
    private String ename;
    
    private int platform_id;
    
    @Transient
    private String platform_name;
    
    private int module_id;
    
    @Transient
    private String module_name;
    
    private String description;
    
    private long ctime;
    
    @Transient
    private String test_step_num;

    public String getTest_step_num() {
        return test_step_num;
    }

    public void setTest_step_num(String test_step_num) {
        this.test_step_num = test_step_num;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
    
    
}
