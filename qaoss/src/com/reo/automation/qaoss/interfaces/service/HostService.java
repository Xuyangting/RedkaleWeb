/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.HostEntity;
import com.reo.automation.qaoss.interfaces.filter.HostFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class HostService extends BasedTestService{
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, HostFilter bean) {
        return source.querySheet(HostEntity.class, flipper, bean);
    }
    
    /**
     * 更新单个
     * @param bean 
     */
    public void update(HostEntity bean) {
        source.updateColumns(bean, "env", "hostname", "host");
    }
    
    /**
     * 插入单个
     * @param bean 
     */
    public void insert(HostEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个调度任务
     * @param bean 
     */
    public void delete(HostEntity bean){
        source.delete(bean);
    }
}
