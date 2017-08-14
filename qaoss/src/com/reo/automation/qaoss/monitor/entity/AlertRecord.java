/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name="alertrecord")
public class AlertRecord extends BaseEntity {
    @Id
//    @DistributeGenerator(initialValue = 1000000)
    @GeneratedValue
    private long alertrecordid;
    private int moduleid;
    @Transient
    private String modulename;    
    private int alerttypeid;
    @Transient
    private String alerttypename;
    private String alertdescription;
    private String alertlevel = "1";
    private String env = "L";
    private String casename = "";
    private String expectedresponse = "";
    private String actualresponse = "";
    private long alerttime = System.currentTimeMillis();
    private String requestid;
    private String remark = "";
    private long inserttime = System.currentTimeMillis();

    public long getAlertrecordid() {
        return alertrecordid;
    }

    public void setAlertrecordid(long alertrecordid) {
        this.alertrecordid = alertrecordid;
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }

    public String getAlertdescription() {
        return alertdescription;
    }

    public void setAlertdescription(String alertdescription) {
        this.alertdescription = alertdescription;
    }

    public String getAlertlevel() {
        return alertlevel;
    }

    public void setAlertlevel(String alertlevel) {
        this.alertlevel = alertlevel;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public int getAlerttypeid() {
        return alerttypeid;
    }

    public void setAlerttypeid(int alerttypeid) {
        this.alerttypeid = alerttypeid;
    }
    public String getExpectedresponse() {
        return expectedresponse;
    }

    public void setExpectedresponse(String expectedresponse) {
        this.expectedresponse = expectedresponse;
    }

    public String getActualresponse() {
        return actualresponse;
    }

    public void setActualresponse(String actualresponse) {
        this.actualresponse = actualresponse;
    }

    public long getAlerttime() {
        return alerttime;
    }

    public void setAlerttime(long alerttime) {
        this.alerttime = alerttime;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getInserttime() {
        return inserttime;
    }

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getAlerttypename() {
        return alerttypename;
    }

    public void setAlerttypename(String alerttypename) {
        this.alerttypename = alerttypename;
    }
}
