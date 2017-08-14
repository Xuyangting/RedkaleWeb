/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author timen.xu
 */
@Entity
@Table(name = "test.stats_cases")
public class StatsCaseEntity extends BaseEntity{
    @Id
    private int id;
    
    private Date time;
    
    private String module;
    
    private int num;
    
    private int automatable_num;
    
    private int complete_num;
    
    private float complete_rate;
    
    private float coverage;
    
    private String remark;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAutomatable_num() {
        return automatable_num;
    }

    public void setAutomatable_num(int automatable_num) {
        this.automatable_num = automatable_num;
    }

    public int getComplete_num() {
        return complete_num;
    }

    public void setComplete_num(int complete_num) {
        this.complete_num = complete_num;
    }

    public float getComplete_rate() {
        return complete_rate;
    }

    public void setComplete_rate(float complete_rate) {
        this.complete_rate = complete_rate;
    }

    public float getCoverage() {
        return coverage;
    }

    public void setCoverage(float coverage) {
        this.coverage = coverage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    
}
