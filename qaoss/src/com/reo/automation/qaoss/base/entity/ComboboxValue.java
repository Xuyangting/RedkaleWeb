/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.entity;

/**
 *
 * @author jerry.ouyang
 */
public class ComboboxValue {
    private long id;
    private String pname;

    public ComboboxValue(long id, String pname) {
        this.id = id;
        this.pname = pname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    
    
}
