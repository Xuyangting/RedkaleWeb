/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name = "tcversions")
public class Tcversions extends BaseEntity{
    @Id
    private int id;
//    private int version;
//    private int status;
    private int importance;
    private int author_id;
    
    @Transient
    private String authorname;
    
    private Date creation_ts;
//    private int active;
//    private int is_open;
//    private int execution_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getVersion() {
//        return version;
//    }
//
//    public void setVersion(int version) {
//        this.version = version;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public Date getCreation_ts() {
        return creation_ts;
    }

    public void setCreation_ts(Date creation_ts) {
        this.creation_ts = creation_ts;
    }

//    public int getActive() {
//        return active;
//    }
//
//    public void setActive(int active) {
//        this.active = active;
//    }
//
//    public int getIs_open() {
//        return is_open;
//    }
//
//    public void setIs_open(int is_open) {
//        this.is_open = is_open;
//    }
//
//    public int getExecution_type() {
//        return execution_type;
//    }
//
//    public void setExecution_type(int execution_type) {
//        this.execution_type = execution_type;
//    }    
}
