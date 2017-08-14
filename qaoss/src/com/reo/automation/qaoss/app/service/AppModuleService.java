/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppModuleEntity;
import com.reo.automation.qaoss.app.filter.AppModuleFilter;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedAppService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppModuleService  extends BasedAppService{
    @Resource
    private ApplicationService applicationService;
    
    /**
     * 返回一个map<moduleid, modulename>, 用于根据moduleid查找modulename
     * @return 
     */
    public Map<Integer, String> queryName() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        source.queryList(AppModuleEntity.class, new BasedFilterBean()).forEach(e -> map.put(e.getId(), e.getName()));
        return map;
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppModuleFilter bean) {
        Map<Integer, String> moduleMap = applicationService.queryName();
        Sheet<AppModuleEntity> sheet = source.querySheet(AppModuleEntity.class, flipper, bean);
        for(AppModuleEntity record : sheet.list()) {
            record.setPlatform_name(moduleMap.get(record.getPlatform_id()));
        }
        return sheet;
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppModuleEntity bean) {
        source.updateColumns(bean, "name", "platform_id", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppModuleEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppModuleEntity bean){
        source.delete(bean);
    }
}
