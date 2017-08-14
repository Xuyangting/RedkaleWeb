/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name="buildrecorddetail")
public class BuildRecordDetail extends BaseEntity{
    @Id
    private long buildrecorddetailid;
    private long buildjobid;
    private int buildno;
    private String testsuitename;
    private String requestname;
    private int duration;
    private String result;
    private int age;
    private String errordetails;
    private String errorstacktrace;
    private int failedsince;
    private String skipped;
    private String skippedmessage;
    private String stderr;
    private String stdout;
    private long buildtimestamp;

    public long getBuildrecorddetailid() {
        return buildrecorddetailid;
    }

    public void setBuildrecorddetailid(long buildrecorddetailid) {
        this.buildrecorddetailid = buildrecorddetailid;
    }

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }

    public int getBuildno() {
        return buildno;
    }

    public void setBuildno(int buildno) {
        this.buildno = buildno;
    }

    public String getTestsuitename() {
        return testsuitename;
    }

    public void setTestsuitename(String testsuitename) {
        this.testsuitename = testsuitename;
    }

    public String getRequestname() {
        return requestname;
    }

    public void setRequestname(String requestname) {
        this.requestname = requestname;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getErrordetails() {
        return errordetails;
    }

    public void setErrordetails(String errordetails) {
        this.errordetails = errordetails;
    }

    public String getErrorstacktrace() {
        return errorstacktrace;
    }

    public void setErrorstacktrace(String errorstacktrace) {
        this.errorstacktrace = errorstacktrace;
    }

    public int getFailedsince() {
        return failedsince;
    }

    public void setFailedsince(int failedsince) {
        this.failedsince = failedsince;
    }

    public String getSkipped() {
        return skipped;
    }

    public void setSkipped(String skipped) {
        this.skipped = skipped;
    }

    public String getSkippedmessage() {
        return skippedmessage;
    }

    public void setSkippedmessage(String skippedmessage) {
        this.skippedmessage = skippedmessage;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }
    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public long getBuildtimestamp() {
        return buildtimestamp;
    }

    public void setBuildtimestamp(long buildtimestamp) {
        this.buildtimestamp = buildtimestamp;
    }
    
    //有两种状态算成功 分别为： PASSED , FIXED
    public boolean isSuccessful() {
        return (this.getResult().equals("PASSED") || this.getResult().equals("FIXED"));
    }
}
