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
public class APIFilter implements FilterBean{
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private String api_id;

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private int moduleid;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private String name;
    
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
    
    
}
