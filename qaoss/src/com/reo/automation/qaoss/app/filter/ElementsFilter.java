/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;
import org.redkale.source.FilterGroup;

/**
 *
 * @author timen.xu
 */
public class ElementsFilter implements FilterBean{
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private int platform_id;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private int module_id;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String name;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String english;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String page;

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
    
    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
