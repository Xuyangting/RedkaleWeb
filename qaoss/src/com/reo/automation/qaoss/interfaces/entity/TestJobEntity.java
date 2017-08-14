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
@Table(name = "test.testjob")
public class TestJobEntity extends BaseEntity{
    @Id
    private int id;
    
    private String name;
    
    private String chinesename;
    
    private String define;
    
    private String env;
    
    private int testpackage;
            
    private int retryCount;
    
    private int warningThreshold;
                    
    private int warningType;
                            
    private int emailAlert;
                                    
    private int wechatAlert;
    
    private String creator;
    
    private long ctime;
    
    private String jenkins;

    public String getJenkins() {
        return jenkins;
    }

    public void setJenkins(String jenkins) {
        this.jenkins = jenkins;
    }

    
    @Transient
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public int getTestpackage() {
        return testpackage;
    }

    public void setTestpackage(int testpackage) {
        this.testpackage = testpackage;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getWarningThreshold() {
        return warningThreshold;
    }

    public void setWarningThreshold(int warningThreshold) {
        this.warningThreshold = warningThreshold;
    }

    public int getWarningType() {
        return warningType;
    }

    public void setWarningType(int warningType) {
        this.warningType = warningType;
    }

    public int getEmailAlert() {
        return emailAlert;
    }

    public void setEmailAlert(int emailAlert) {
        this.emailAlert = emailAlert;
    }

    public int getWechatAlert() {
        return wechatAlert;
    }

    public void setWechatAlert(int wechatAlert) {
        this.wechatAlert = wechatAlert;
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
    
    
    
    
}
