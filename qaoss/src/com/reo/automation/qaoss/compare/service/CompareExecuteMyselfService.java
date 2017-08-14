/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.compare.service;

import com.reo.automation.qaoss.base.service.BasedAppService;
import com.reo.automation.qaoss.compare.entity.CompareExecuteMyselfEntity;
import com.reo.automation.qaoss.compare.filter.CompareExecuteMyselfFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class CompareExecuteMyselfService extends BasedAppService {
    /**
     * 通过id查询test report
     * @param ctime
     * @return 
     */
    public Integer queryIdByCtime(long ctime) {
        CompareExecuteMyselfFilter bean = new CompareExecuteMyselfFilter();
        bean.setCtime(ctime);
        return source.find(CompareExecuteMyselfEntity.class, bean).getId();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, CompareExecuteMyselfFilter bean) {
        return source.querySheet(CompareExecuteMyselfEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(CompareExecuteMyselfEntity bean) {
        source.updateColumns(bean, "result", "report", "author", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(CompareExecuteMyselfEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(CompareExecuteMyselfEntity bean){
        source.delete(bean);
    }
}
