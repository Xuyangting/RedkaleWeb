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
@Table(name = "buildrecorddetailstatistics")
public class BuildRecordDetailStati extends BaseEntity{
    @Id
    @GeneratedValue
    private long brdsid;
    
    private long buildjobid;
    private String requestname;
    private long buildtimestamp;
    private float onehundred;
    private float twohundreds;
    private float fivehundreds;
    private float onethousand;
    private String brdetaildate;
    private long success;
    private long failed;
    private float successrate;
    private long inserttime;

    public long getBrdsid() {
        return brdsid;
    }

    public void setBrdsid(long brdsid) {
        this.brdsid = brdsid;
    }

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }

    public String getRequestname() {
        return requestname;
    }

    public void setRequestname(String requestname) {
        this.requestname = requestname;
    }

    public long getBuildtimestamp() {
        return buildtimestamp;
    }

    public void setBuildtimestamp(long buildtimestamp) {
        this.buildtimestamp = buildtimestamp;
    }

    public float getOnehundred() {
        return onehundred;
    }

    public void setOnehundred(float onehundred) {
        this.onehundred = onehundred;
    }

    public float getTwohundreds() {
        return twohundreds;
    }

    public void setTwohundreds(float twohundreds) {
        this.twohundreds = twohundreds;
    }

    public float getFivehundreds() {
        return fivehundreds;
    }

    public void setFivehundreds(float fivehundreds) {
        this.fivehundreds = fivehundreds;
    }

    public float getOnethousand() {
        return onethousand;
    }

    public void setOnethousand(float onethousand) {
        this.onethousand = onethousand;
    }

    public String getBrdetaildate() {
        return brdetaildate;
    }

    public void setBrdetaildate(String brdetaildate) {
        this.brdetaildate = brdetaildate;
    }

    public long getInserttime() {
        return inserttime;
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

    public void setInserttime(long inserttime) {
        this.inserttime = inserttime;
    }
}
