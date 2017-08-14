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
@Table(name = "app.app_execute")
public class AppExecuteEntity  extends BaseEntity{
    @Id
    private int id;
    
    private int mobile_id;
    
    @Transient
    private String mobile_name;
    
    @Transient
    private String platform_name;

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }
    

    public String getMobile_name() {
        return mobile_name;
    }

    public void setMobile_name(String mobile_name) {
        this.mobile_name = mobile_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    
    private String test_env;

    public String getTest_env() {
        return test_env;
    }

    public void setTest_env(String test_env) {
        this.test_env = test_env;
    }
    
    private int job_id;
    
    @Transient
    private String job_name;
    
    private String test_result;
    
    private String test_report;
    
    private String execute_by_author;
    
    private long ctime;
    
    private String app_address;

    public String getApp_address() {
        return app_address;
    }

    public void setApp_address(String app_address) {
        this.app_address = app_address;
    }
    
    public int getMobile_id() {
        return mobile_id;
    }

    public void setMobile_id(int mobile_id) {
        this.mobile_id = mobile_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getTest_result() {
        return test_result;
    }

    public void setTest_result(String test_result) {
        this.test_result = test_result;
    }

    public String getTest_report() {
        return test_report;
    }

    public void setTest_report(String test_report) {
        this.test_report = test_report;
    }

    public String getExecute_by_author() {
        return execute_by_author;
    }

    public void setExecute_by_author(String execute_by_author) {
        this.execute_by_author = execute_by_author;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
    
    
    
    
}
