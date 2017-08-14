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
public class AppTestCaseFilter  implements FilterBean{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private String platform_name;

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private String ename;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private String module_name;

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
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private int platform_id;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private int module_id;
    
}
