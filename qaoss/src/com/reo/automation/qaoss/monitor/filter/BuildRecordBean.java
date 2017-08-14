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
public class BuildRecordBean implements FilterBean{
    private long buildrecordid;
    
    @FilterColumn(name="buildrecordid", express=IN)
    private long[] buildrecordids;
    private long buildjobid;
    private int buildno;
    private String result;
    
    private LongRange buildtimestamp;

    public long getBuildrecordid() {
        return buildrecordid;
    }

    public void setBuildrecordid(long buildrecordid) {
        this.buildrecordid = buildrecordid;
    }

    public long[] getBuildrecordids() {
        return buildrecordids;
    }

    public void setBuildrecordids(long[] buildrecordids) {
        this.buildrecordids = buildrecordids;
    }

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }

    public int getBuildno() {
        return buildno;
    }

    public void setBuildno(int buildno) {
        this.buildno = buildno;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LongRange getBuildtimestamp() {
        return buildtimestamp;
    }

    public void setBuildtimestamp(LongRange buildtimestamp) {
        this.buildtimestamp = buildtimestamp;
    }
    
    
}
