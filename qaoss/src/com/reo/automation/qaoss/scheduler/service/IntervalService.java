/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.scheduler.service;

import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.scheduler.entity.IntervalEntity;
import java.util.HashMap;
import java.util.Map;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class IntervalService extends BasedMonitorService{
    /**
     * 查询所有的调度任务
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, BasedFilterBean bean) {
        return source.querySheet(IntervalEntity.class, flipper, bean);
    }
    
    /**
     * 更新单个调度任务
     * @param bean 
     */
    public void update(IntervalEntity bean) {
        source.updateColumns(bean, "job_name", "weeks", "days", "hours", "minutes", "seconds", "start_date", "end_date", "timezone", "enable", "description");
    }
    
    /**
     * 插入单个调度任务
     * @param bean 
     */
    public void insert(IntervalEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个调度任务
     * @param bean 
     */
    public void delete(IntervalEntity bean){
        source.delete(bean);
    }
}
