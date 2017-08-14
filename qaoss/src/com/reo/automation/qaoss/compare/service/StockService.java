/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.compare.service;

import com.reo.automation.qaoss.compare.entity.StockEntity;
import com.reo.automation.qaoss.compare.filter.StockFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class StockService extends BasedAppService {
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, StockFilter bean) {
        return source.querySheet(StockEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(StockEntity bean) {
        source.updateColumns(bean, "stock_code", "stock_type", "description");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(StockEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(StockEntity bean){
        source.delete(bean);
    }
}
