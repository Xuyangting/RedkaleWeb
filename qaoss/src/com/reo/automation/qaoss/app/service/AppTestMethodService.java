/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppTestMethodEntity;
import com.reo.automation.qaoss.app.filter.AppTestMethodFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppTestMethodService  extends BasedAppService{
    /**
     * 通过name查询description
     * @param name
     * @return 
     */
    public String queryDescriptionByName(String name) {
        AppTestMethodFilter bean = new AppTestMethodFilter();
        bean.setName(name);
        return source.find(AppTestMethodEntity.class, bean).getDescription();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppTestMethodFilter bean) {
        return source.querySheet(AppTestMethodEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppTestMethodEntity bean) {
        source.updateColumns(bean, "name", "description");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppTestMethodEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppTestMethodEntity bean){
        source.delete(bean);
    }
}
