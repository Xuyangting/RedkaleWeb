/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import org.redkale.source.FilterExpress;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class BuildRecordDetailBean implements FilterBean{
    private long buildjobid;
    private String requestname;
    
    @FilterColumn(name="requestname", express=FilterExpress.IN)
    private String[] requestnames;
    
    private LongRange buildtimestamp;

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }

    public String getRequestname() {
        return requestname;
    }

    public void setRequestname(String requestname) {
        this.requestname = requestname;
    }

    public LongRange getBuildtimestamp() {
        return buildtimestamp;
    }

    public void setBuildtimestamp(LongRange buildtimestamp) {
        this.buildtimestamp = buildtimestamp;
    }

    public String[] getRequestnames() {
        return requestnames;
    }

    public void setRequestnames(String[] requestnames) {
        this.requestnames = requestnames;
    }
    
}
