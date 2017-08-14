/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.IN;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class BuildRecordDetailStatiBean implements FilterBean{
    
    @FilterColumn(name="buildjobid", express=IN)
    private long[] buildjobids;
    private String requestname;
    private LongRange buildtimestamp;

    public long[] getBuildjobids() {
        return buildjobids;
    }

    public void setBuildjobids(long[] buildjobids) {
        this.buildjobids = buildjobids;
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
}
