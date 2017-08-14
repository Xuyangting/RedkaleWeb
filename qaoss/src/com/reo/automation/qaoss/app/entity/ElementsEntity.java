/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.entity;

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
@Table(name = "app.app_elements")
public class ElementsEntity extends BaseEntity{
    @Id
    private int id;
    
    private int platform_id;
    
    @Transient
    private String platform_name;
    
    private int module_id;
    
    @Transient
    private String module_name;  
    
    private String page;
            
    private String english;
            
    private String name;
            
    private String android_address;
    
    private String ios_address;
            
    private long ctime;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAndroid_address() {
        return android_address;
    }

    public void setAndroid_address(String android_address) {
        this.android_address = android_address;
    }

    public String getIos_address() {
        return ios_address;
    }

    public void setIos_address(String ios_address) {
        this.ios_address = ios_address;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
             
}
