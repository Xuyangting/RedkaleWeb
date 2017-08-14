/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.monitor.entity.MarketPostionPrice;
import com.reo.automation.qaoss.monitor.filter.MarketPostionPriceBean;
import java.util.List;

/**
 *
 * @author jerry.ouyang
 */
public class MarketPostionPriceService extends BasedMonitorService {
    public void insert(MarketPostionPrice bean) {
        source.insert(bean);
    }
    
    public List<MarketPostionPrice> queryList(MarketPostionPriceBean bean) {
        return source.queryList(MarketPostionPrice.class, bean);
    }
}
