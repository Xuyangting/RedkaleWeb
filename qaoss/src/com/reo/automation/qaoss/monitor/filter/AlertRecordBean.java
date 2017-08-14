/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class AlertRecordBean implements FilterBean{
    private long alertrecordid;
    private int moduleid;
    private int alerttypeid;
    private String alertlevel;
    private String env;
    private LongRange alerttime;

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

    public int getAlerttypeid() {
        return alerttypeid;
    }

    public void setAlerttypeid(int alerttypeid) {
        this.alerttypeid = alerttypeid;
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

    public LongRange getAlerttime() {
        return alerttime;
    }

    public void setAlerttime(LongRange alerttime) {
        this.alerttime = alerttime;
    }
    

}
