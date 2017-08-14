/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Table(name="module_request_map")
public class ModuleRequestMap extends BaseEntity{
    @Id
    @GeneratedValue
    private int requestid;
    
    private int moduleid;
    private String requestname;
    private int mapid;

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }

    public String getRequestname() {
        return requestname;
    }

    public void setRequestname(String requestname) {
        this.requestname = requestname;
    }

    public int getMapid() {
        return mapid;
    }

    public void setMapid(int mapid) {
        this.mapid = mapid;
    }    
}
