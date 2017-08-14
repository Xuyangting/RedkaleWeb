/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.scheduler.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author timen.xu
 */
@Entity
@Table(name = "app.app_testaccount")
public class TestAccountEntity extends BaseEntity{
    @Id
    private int id;
    
    private String qa_account;
    
    private String qa_password;
    
    private String qa_trade_password;
    
    private String stage_account;
    
    private String stage_password;
    
    private String stage_trade_password;
    
    private String live_account;
    
    private String live_password;
    
    private String live_trade_password;
    
    private String owner;
    
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQa_account() {
        return qa_account;
    }

    public void setQa_account(String qa_account) {
        this.qa_account = qa_account;
    }

    public String getQa_password() {
        return qa_password;
    }

    public void setQa_password(String qa_password) {
        this.qa_password = qa_password;
    }

    public String getQa_trade_password() {
        return qa_trade_password;
    }

    public void setQa_trade_password(String qa_trade_password) {
        this.qa_trade_password = qa_trade_password;
    }

    public String getStage_account() {
        return stage_account;
    }

    public void setStage_account(String stage_account) {
        this.stage_account = stage_account;
    }

    public String getStage_password() {
        return stage_password;
    }

    public void setStage_password(String stage_password) {
        this.stage_password = stage_password;
    }

    public String getStage_trade_password() {
        return stage_trade_password;
    }

    public void setStage_trade_password(String stage_trade_password) {
        this.stage_trade_password = stage_trade_password;
    }

    public String getLive_account() {
        return live_account;
    }

    public void setLive_account(String live_account) {
        this.live_account = live_account;
    }

    public String getLive_password() {
        return live_password;
    }

    public void setLive_password(String live_password) {
        this.live_password = live_password;
    }

    public String getLive_trade_password() {
        return live_trade_password;
    }

    public void setLive_trade_password(String live_trade_password) {
        this.live_trade_password = live_trade_password;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
