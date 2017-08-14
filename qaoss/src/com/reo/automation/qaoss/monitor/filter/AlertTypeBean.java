/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;
import org.redkale.source.FilterGroup;

/**
 *
 * @author jerry.ouyang
 */
public class AlertTypeBean implements FilterBean{
    private int alerttypeid;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String alerttypename;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String alerttypedesc;

    public int getAlerttypeid() {
        return alerttypeid;
    }

    public void setAlerttypeid(int alerttypeid) {
        this.alerttypeid = alerttypeid;
    }

    public String getAlerttypename() {
        return alerttypename;
    }

    public void setAlerttypename(String alerttypename) {
        this.alerttypename = alerttypename;
    }

    public String getAlerttypedesc() {
        return alerttypedesc;
    }

    public void setAlerttypedesc(String alerttypedesc) {
        this.alerttypedesc = alerttypedesc;
    }

}
