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
@Entity
@Table(name = "buildrecordstatistics")
public class BuildRecordStatistics extends BaseEntity{
    @Id
    @GeneratedValue
    private long buildrecordstatisticsid;
    
    private long buildjobid;
    private long buildtimestamp;
    private long success;
    private long failed;
    private float successrate;
    private String buildrecorddate;
    private long inserttime;

    public long getBuildrecordstatisticsid() {
        return buildrecordstatisticsid;
    }

    public void setBuildrecordstatisticsid(long buildrecordstatisticsid) {
        this.buildrecordstatisticsid = buildrecordstatisticsid;
    }

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }

    public long getBuildtimestamp() {
        return buildtimestamp;
    }

    public void setBuildtimestamp(long buildtimestamp) {
        this.buildtimestamp = buildtimestamp;
    }


    public long getSuccess() {
        return success;
    }

    public void setSuccess(long success) {
        this.success = success;
    }

    public long getFailed() {
        return failed;
    }

    public void setFailed(long failed) {
        this.failed = failed;
    }

    public float getSuccessrate() {
        return successrate;
    }

    public void setSuccessrate(float successrate) {
        this.successrate = successrate;
    }

    public String getBuildrecorddate() {
        return buildrecorddate;
    }

    public void setBuildrecorddate(String buildrecorddate) {
        this.buildrecorddate = buildrecorddate;
    }

    public long getInserttime() {
        return inserttime;
    }

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }    
}
