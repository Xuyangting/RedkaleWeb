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

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name="requestname")
public class RequestName {
    @Id
    @GeneratedValue
    private int requestid;
    private String requestname;

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public String getRequestname() {
        return requestname;
    }

    public void setRequestname(String requestname) {
        this.requestname = requestname;
    }
}
