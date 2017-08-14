/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.compare.service;

import com.reo.automation.qaoss.base.service.BasedAppService;
import com.reo.automation.qaoss.compare.entity.CompareExecuteEntity;
import com.reo.automation.qaoss.compare.filter.CompareExecuteFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class CompareExecuteService extends BasedAppService {
    /**
     * 通过id查询test report
     * @param ctime
     * @return 
     */
    public Integer queryIdByCtime(long ctime) {
        CompareExecuteFilter bean = new CompareExecuteFilter();
        bean.setCtime(ctime);
        return source.find(CompareExecuteEntity.class, bean).getId();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, CompareExecuteFilter bean) {
        return source.querySheet(CompareExecuteEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(CompareExecuteEntity bean) {
        source.updateColumns(bean, "env", "result", "report", "author", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(CompareExecuteEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(CompareExecuteEntity bean){
        source.delete(bean);
    }
}
