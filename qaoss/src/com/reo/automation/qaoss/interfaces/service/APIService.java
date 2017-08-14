/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.APIEntity;
import com.reo.automation.qaoss.interfaces.filter.APIFilter;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class APIService extends BasedTestService{
    @Resource
    private ModulesService moduleService;
    
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, APIFilter bean) {
        Map<Integer, String> moduleMap = moduleService.queryModuleName();
        Sheet<APIEntity> sheet = source.querySheet(APIEntity.class, flipper, bean);
        for(APIEntity record : sheet.list()) {
            record.setModule_name(moduleMap.get(record.getModuleid()));
        }
        return sheet;
    }
    
    /**
     * 更新单个
     * @param bean 
     */
    public void update(APIEntity bean) {
        source.updateColumns(bean, "api_id", "moduleid", "name", "host", "resource", "method", "submitparams", "headers", "cookies", "encrypt", "owner", "ctime");
    }
    
    /**
     * 插入单个
     * @param bean 
     */
    public void insert(APIEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个
     * @param bean 
     */
    public void delete(APIEntity bean){
        source.delete(bean);
    }
}
