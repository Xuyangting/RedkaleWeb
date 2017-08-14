/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name="buildrecord")
public class BuildRecord extends BaseEntity{
    @Id
    private long buildrecordid;
    private long buildjobid;
    
    @Transient
    private String buildjobname;
    
    private int buildno;
    private String buildparameters;
    private String result;
    private int total;
    private int passed;
    private int failed;
    private int skipped;
    private int duration;
    private long buildtimestamp;
    private long inserttime;

    public long getBuildrecordid() {
        return buildrecordid;
    }

    public void setBuildrecordid(long buildrecordid) {
        this.buildrecordid = buildrecordid;
    }

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }

    public String getBuildjobname() {
        return buildjobname;
    }

    public void setBuildjobname(String buildjobname) {
        this.buildjobname = buildjobname;
    }

    public int getBuildno() {
        return buildno;
    }

    public void setBuildno(int buildno) {
        this.buildno = buildno;
    }

    public String getBuildparameters() {
        return buildparameters;
    }

    public void setBuildparameters(String buildparameters) {
        this.buildparameters = buildparameters;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getBuildtimestamp() {
        return buildtimestamp;
    }

    public void setBuildtimestamp(long buildtimestamp) {
        this.buildtimestamp = buildtimestamp;
    }

    public long getInserttime() {
        return inserttime;
    }

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }
    
    
}
