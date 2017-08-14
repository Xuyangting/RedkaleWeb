/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.ModulesEntity;
import com.reo.automation.qaoss.interfaces.filter.ModulesFilter;
import java.util.HashMap;
import java.util.Map;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class ModulesService extends BasedTestService {
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, ModulesFilter bean) {
        return source.querySheet(ModulesEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(ModulesEntity bean) {
        source.updateColumns(bean, "module_id", "name", "owner", "ctime", "mtime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(ModulesEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(ModulesEntity bean){
        source.delete(bean);
    }
    
    /**
     * 返回一个map<moduleid, modulename>, 用于根据moduleid查找modulename
     * @return 
     */
    public Map<Integer, String> queryModuleName() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        source.queryList(ModulesEntity.class, new BasedFilterBean()).forEach(e -> map.put(e.getModule_id(), e.getName()));
        return map;
    }
}
