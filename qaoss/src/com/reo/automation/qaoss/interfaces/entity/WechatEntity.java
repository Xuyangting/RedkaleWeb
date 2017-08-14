/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author timen.xu
 */
@Entity
@Table(name = "test.wechatgroup")
public class WechatEntity extends BaseEntity{
    @Id
    private int id;
    
    private String type;
    
    private int recipients;
    
    private String name;
    
    private long ctime;
    
    private String members;

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRecipients() {
        return recipients;
    }

    public void setRecipients(int recipients) {
        this.recipients = recipients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }
    
    
    
    
}
