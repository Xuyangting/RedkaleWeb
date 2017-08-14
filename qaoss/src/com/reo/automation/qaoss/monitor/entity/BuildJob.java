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
import javax.persistence.Transient;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name="buildjob")
public class BuildJob extends BaseEntity {
    @Id
    @GeneratedValue
    private long buildjobid;
    private String name;
    private String buildjoburl;
    private int lastbuildno = 0;
    private int status = STATUS_NORMAL;
    private int moduleid = 0;
    
    @Transient
    private String modulename;

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildjoburl() {
        return buildjoburl;
    }

    public void setBuildjoburl(String buildjoburl) {
        this.buildjoburl = buildjoburl;
    }

    public int getLastbuildno() {
        return lastbuildno;
    }

    public void setLastbuildno(int lastbuildno) {
        this.lastbuildno = lastbuildno;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }
}
