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
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Table(name="alertrecordstatistics")
@Entity
public class AlertRecordStatistics extends BaseEntity{
    @Id
    @GeneratedValue
    private long alertrecordstatid;
    
    private long alerttimestamp;
    private String alertdate;
    private int count;
    private String env;
    private long inserttime;

    public long getAlertrecordstatid() {
        return alertrecordstatid;
    }

    public void setAlertrecordstatid(long alertrecordstatid) {
        this.alertrecordstatid = alertrecordstatid;
    }

    public long getAlerttimestamp() {
        return alerttimestamp;
    }

    public void setAlerttimestamp(long alerttimestamp) {
        this.alerttimestamp = alerttimestamp;
    }

    public String getAlertdate() {
        return alertdate;
    }

    public void setAlertdate(String alertdate) {
        this.alertdate = alertdate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public long getInserttime() {
        return inserttime;
    }

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }
    
    
}
