/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;
import static org.redkale.source.FilterExpress.STARTSWITH;
import org.redkale.source.FilterGroup;
/**
 *
 * @author timen.xu
 */
public class TestCaseFilter implements FilterBean{
    @FilterColumn(express=STARTSWITH)
    @FilterGroup("[AND]")
    private String api;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String ename;

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    
}
