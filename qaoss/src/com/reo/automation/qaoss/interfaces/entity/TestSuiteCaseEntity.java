/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.entity;

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
@Table(name = "test.packagecases")
public class TestSuiteCaseEntity extends BaseEntity{
    @Id
    private int id;
    
    private int stepid;
    
    private int packageid;
    
    private int caseid;
    
    @Transient
    private String case_name;
    
    private float sno;
    
    private String comment;
    
    private String cache;
    
    private String transferParams;
    
    private int depend;
    
    private int dependtype;
    
    private short ishttp;
    
    private int sleeptime;
    
    private int loopNum;
    
    private int disable;
    
    private int isStep;
    
    private int retry;
    
    public int getStepid() {
        return stepid;
    }

    public void setStepid(int stepid) {
        this.stepid = stepid;
    }
    

    public float getSno() {
        return sno;
    }

    public void setSno(float sno) {
        this.sno = sno;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackageid() {
        return packageid;
    }

    public void setPackageid(int packageid) {
        this.packageid = packageid;
    }

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getTransferParams() {
        return transferParams;
    }

    public void setTransferParams(String transferParams) {
        this.transferParams = transferParams;
    }

    public int getDepend() {
        return depend;
    }

    public void setDepend(int depend) {
        this.depend = depend;
    }

    public short getIshttp() {
        return ishttp;
    }

    public void setIshttp(short ishttp) {
        this.ishttp = ishttp;
    }

    public int getSleeptime() {
        return sleeptime;
    }

    public void setSleeptime(int sleeptime) {
        this.sleeptime = sleeptime;
    }

    public int getLoopNum() {
        return loopNum;
    }

    public void setLoopNum(int loopNum) {
        this.loopNum = loopNum;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public int getIsStep() {
        return isStep;
    }

    public void setIsStep(int isStep) {
        this.isStep = isStep;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }
    
    

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }
    
    
    public int getDependtype() {
        return dependtype;
    }

    public void setDependtype(int dependtype) {
        this.dependtype = dependtype;
    }
    
    
}
