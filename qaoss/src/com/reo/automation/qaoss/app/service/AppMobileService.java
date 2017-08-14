/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppMobileEntity;
import com.reo.automation.qaoss.app.filter.AppMobileFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppMobileService  extends BasedAppService{
    /**
     * 通过id查询test report
     * @param id
     * @return 
     */
    public String queryMobileNameById(int id) {
        AppMobileFilter bean = new AppMobileFilter();
        bean.setId(id);
        return source.find(AppMobileEntity.class, bean).getName();
    }
    
    /**
     * 通过id查询test report
     * @param id
     * @return 
     */
    public String queryMobileTypeById(int id) {
        AppMobileFilter bean = new AppMobileFilter();
        bean.setId(id);
        return source.find(AppMobileEntity.class, bean).getType();
    }
    
    /**
     * 通过id查询test report
     * @param id
     * @return 
     */
    public String queryMobileUDIDById(int id) {
        AppMobileFilter bean = new AppMobileFilter();
        bean.setId(id);
        return source.find(AppMobileEntity.class, bean).getUdid();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppMobileFilter bean) {
        return source.querySheet(AppMobileEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppMobileEntity bean) {
        source.updateColumns(bean, "name", "type", "udid", "system", "ratio", "size");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppMobileEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppMobileEntity bean){
        source.delete(bean);
    }
}
