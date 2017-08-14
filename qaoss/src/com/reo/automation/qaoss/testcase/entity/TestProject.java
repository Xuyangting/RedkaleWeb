/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name="testprojects")
public class TestProject extends BaseEntity{
    @Id
    private int id;
    private String prefix;
    private int tc_counter;

    public TestProject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getTc_counter() {
        return tc_counter;
    }

    public void setTc_counter(int tc_counter) {
        this.tc_counter = tc_counter;
    }    
}
