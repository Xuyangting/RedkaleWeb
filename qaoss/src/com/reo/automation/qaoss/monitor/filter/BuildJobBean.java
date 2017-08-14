/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;

/**
 *
 * @author jerry.ouyang
 */
public class BuildJobBean implements FilterBean{
    @FilterColumn(express=LIKE)
    private String name;
    
    private int moduleid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }
    
}
