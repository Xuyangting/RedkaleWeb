/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.filter;

import java.util.List;
import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import org.redkale.source.FilterExpress;

/**
 *
 * @author jerry.ouyang
 */
public class CustomFieldValueBean implements FilterBean{
    @FilterColumn(name = "issue", express=FilterExpress.IN)
    private List issues;
    
    private long customfield;

    public List getIssues() {
        return issues;
    }

    public void setIssues(List issues) {
        this.issues = issues;
    }

    public long getCustomfield() {
        return customfield;
    }

    public void setCustomfield(long customfield) {
        this.customfield = customfield;
    }
    
    
}
