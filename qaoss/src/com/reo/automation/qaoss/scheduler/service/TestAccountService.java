/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.scheduler.service;

import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.scheduler.entity.TestAccountEntity;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class TestAccountService  extends BasedTestService{
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, BasedFilterBean bean) {
        return source.querySheet(TestAccountEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(TestAccountEntity bean) {
        source.updateColumns(bean, "qa_account", "qa_password", "qa_trade_password", "stage_account", "stage_password", "stage_trade_password", "live_account", "live_password", "live_trade_password", "owner", "remark");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(TestAccountEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(TestAccountEntity bean){
        source.delete(bean);
    }
}
