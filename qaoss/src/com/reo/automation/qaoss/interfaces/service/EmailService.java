/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.EmailEntity;
import com.reo.automation.qaoss.interfaces.filter.EmailFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class EmailService extends BasedTestService {
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, EmailFilter bean) {
        return source.querySheet(EmailEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(EmailEntity bean) {
        source.updateColumns(bean, "name", "recipients", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(EmailEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(EmailEntity bean){
        source.delete(bean);
    }
}
