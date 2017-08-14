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
public class AlertRecordStatiBean implements FilterBean{
    private LongRange alerttimestamp;
    private String env;

    public LongRange getAlerttimestamp() {
        return alerttimestamp;
    }

    public void setAlerttimestamp(LongRange alerttimestamp) {
        this.alerttimestamp = alerttimestamp;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
    
}
