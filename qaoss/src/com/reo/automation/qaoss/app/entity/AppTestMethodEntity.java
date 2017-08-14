/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author timen.xu
 */
@Entity
@Table(name = "app.app_testmethod")
public class AppTestMethodEntity  extends BaseEntity{
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    private String name;
    
    private String description;


    public String getParameter_two_type() {
        return parameter_two_type;
    }

    public void setParameter_two_type(String parameter_two_type) {
        this.parameter_two_type = parameter_two_type;
    }
    
    private String parameter_one_type;

    public String getParameter_one_type() {
        return parameter_one_type;
    }

    public void setParameter_one_type(String parameter_one_type) {
        this.parameter_one_type = parameter_one_type;
    }
    
    private String parameter_two_type;
    
    
}
