/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.base.service.BasedAppService;
import com.reo.automation.qaoss.app.entity.ElementsEntity;
import com.reo.automation.qaoss.app.filter.ElementsFilter;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class ElementsService extends BasedAppService{
    @Resource
    private ApplicationService applicationService;
    
    @Resource
    private AppModuleService appModuleService;
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, ElementsFilter bean) {
        Map<Integer, String> appMap = applicationService.queryName();
        Map<Integer, String> moduleMap = appModuleService.queryName();
        
        Sheet<ElementsEntity> sheet = source.querySheet(ElementsEntity.class, flipper, bean);
        for(ElementsEntity record : sheet.list()) {
            record.setPlatform_name(appMap.get(record.getPlatform_id()));
            record.setModule_name(moduleMap.get(record.getModule_id()));
        }
        return sheet;
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(ElementsEntity bean) {
        source.updateColumns(bean, "platform_id", "module_id", "page", "english", "name", "android_address", "ios_address");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(ElementsEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(ElementsEntity bean){
        source.delete(bean);
    }
}
