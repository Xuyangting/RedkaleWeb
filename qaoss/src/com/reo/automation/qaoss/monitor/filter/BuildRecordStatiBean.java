/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class BuildRecordStatiBean implements FilterBean {    
    @FilterColumn(name = "buildtimestamp")
    private LongRange timestamprange;
    
    private long buildjobid;

    public LongRange getTimestamprange() {
        return timestamprange;
    }

    public void setTimestamprange(LongRange timestamprange) {
        this.timestamprange = timestamprange;
    }

    public long getBuildjobid() {
        return buildjobid;
    }

    public void setBuildjobid(long buildjobid) {
        this.buildjobid = buildjobid;
    }
}
