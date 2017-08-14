/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import org.redkale.source.FilterExpress;
import static org.redkale.source.FilterExpress.LIKE;
import org.redkale.source.FilterGroup;

/**
 *
 * @author jerry.ouyang
 */
public class ModuleBean implements FilterBean{
    private int moduleid;
    
    @FilterColumn(name = "modulename", express=FilterExpress.LIKE)
    @FilterGroup("[OR]")
    private String modulename;
    
    @FilterColumn(express = LIKE)
    @FilterGroup("[OR]")
    private String moduledesc;
    
    private int status;
    
    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        if (modulename != null && !modulename.isEmpty())
            this.modulename = modulename;
    }

    public String getModuledesc() {
        return moduledesc;
    }

    public void setModuledesc(String moduledesc) {
        this.moduledesc = moduledesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
