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
@Table(name = "alerttype")
public class AlertType extends BaseEntity {
    @Id
    @GeneratedValue
    private int alerttypeid;
    private String alerttypename;
    private String alerttypedesc;
    private long createtime = System.currentTimeMillis();
    private String creator = "";
    private long updatetime = System.currentTimeMillis();
    private String updater = "";
    private int status = 10;

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

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
