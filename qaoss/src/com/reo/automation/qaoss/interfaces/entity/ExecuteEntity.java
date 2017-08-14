/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author timen.xu
 */
@Entity
@Table(name = "test.execute_case")
public class ExecuteEntity  extends BaseEntity {
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_chinese_name() {
        return job_chinese_name;
    }

    public void setJob_chinese_name(String job_chinese_name) {
        this.job_chinese_name = job_chinese_name;
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

    public long getExecute_time() {
        return execute_time;
    }

    public void setExecute_time(long execute_time) {
        this.execute_time = execute_time;
    }
    
    private int job_id;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }
    
    private String job_name;
    
    private String job_chinese_name;
    
    private String test_result;
    
    private String test_report;
    
    private String execute_by_author;
    
    private long execute_time; 
    
    
}
