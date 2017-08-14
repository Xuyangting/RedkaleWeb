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
@Table(name = "test.api")
public class APIEntity extends BaseEntity {
    @Id
    private int id;
    
    private String api_id;

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }
    
    private int moduleid;
    
    private String name;
    
    private String host;
    
    private String resource;
    
    private String method;
    
    private String headers;
    
    private String cookies;
    
    private String encrypt;
    
    private String owner;
    
    private long ctime;
    
    private String submitparams;
    
    @Transient
    private String module_name;

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }
    
    public String getSubmitparams() {
        return submitparams;
    }

    public void setSubmitparams(String submitparams) {
        this.submitparams = submitparams;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
    
    
}
