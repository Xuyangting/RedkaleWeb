/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.scheduler.service;

import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.scheduler.entity.CrontabEntity;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class CrontabService extends BasedMonitorService{
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, BasedFilterBean bean) {
        return source.querySheet(CrontabEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(CrontabEntity bean) {
        source.updateColumns(bean, "job_name", "years", "months", "days", "weeks", "day_of_week", "hours", "minutes", "seconds", "start_date", "end_date", "timezone", "enable", "description");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(CrontabEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(CrontabEntity bean){
        source.delete(bean);
    }
}
