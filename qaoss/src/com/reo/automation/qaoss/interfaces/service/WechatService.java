/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.WechatEntity;
import com.reo.automation.qaoss.interfaces.filter.WechatFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class WechatService  extends BasedTestService {
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, WechatFilter bean) {
        return source.querySheet(WechatEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(WechatEntity bean) {
        source.updateColumns(bean, "type", "recipients", "name", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(WechatEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(WechatEntity bean){
        source.delete(bean);
    }
}
