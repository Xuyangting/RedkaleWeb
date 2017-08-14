/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;
import org.redkale.source.FilterGroup;
/**
 *
 * @author timen.xu
 */
public class TestJobFilter implements FilterBean{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private int testpackage;

    public int getTestpackage() {
        return testpackage;
    }

    public void setTestpackage(int testpackage) {
        this.testpackage = testpackage;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String name;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String chinesename;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }
    
    
}
