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
@Table(name = "app.app_testjob")
public class AppTestJobEntity  extends BaseEntity{
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

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }


    public String getJenkins() {
        return jenkins;
    }

    public void setJenkins(String jenkins) {
        this.jenkins = jenkins;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
    
    private String job_name;
    
    private String job_description;
    
    private int suite_id;

    public int getSuite_id() {
        return suite_id;
    }

    public void setSuite_id(int suite_id) {
        this.suite_id = suite_id;
    }
    
    private String jenkins;
    
    private String creator;
    
    private long ctime;
    
    @Transient
    private String suite_name;

    public String getSuite_name() {
        return suite_name;
    }

    public void setSuite_name(String suite_name) {
        this.suite_name = suite_name;
    }
    
    
}
