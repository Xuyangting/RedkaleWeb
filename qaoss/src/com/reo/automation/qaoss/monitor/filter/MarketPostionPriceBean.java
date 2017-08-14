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
public class MarketPostionPriceBean implements FilterBean {
    private String ticker;
    
    @FilterColumn(name="timestamp")
    private LongRange range;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public LongRange getRange() {
        return range;
    }

    public void setRange(LongRange range) {
        this.range = range;
    }
    
    
}
