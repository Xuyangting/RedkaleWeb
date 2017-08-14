/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.monitor.entity.Module;
import com.reo.automation.qaoss.monitor.filter.ModuleBean;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedMonitorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.redkale.source.FilterBean;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author jerry.ouyang
 */
public class ModuleService extends BasedMonitorService{
    /**
     * 查询所有的module内容
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet<Module> querySheet(Flipper flipper, ModuleBean bean) {
        return source.querySheet(Module.class, flipper, bean);
    }
    
    public String queryName(int moduleid) {
        ModuleBean bean = new ModuleBean();
        bean.setModuleid(moduleid);
        return source.find(Module.class, bean).getModulename();
    }
    
    /**
     * 返回一个map<modulename, moduleid>，用于根据moudlename查找moduleid
     * @return 
     */
    public Map<String, Integer> queryAll() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        source.queryList(Module.class, new BasedFilterBean()).forEach(e -> map.put(e.getModulename(), e.getModuleid()));
        return map;
    }
    
    /**
     * 返回一个map<moduleid, modulename>, 用于根据moduleid查找modulename
     * @return 
     */
    public Map<Integer, String> queryAll2() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        source.queryList(Module.class, new BasedFilterBean()).forEach(e -> map.put(e.getModuleid(), e.getModulename()));
        return map;
    }
    
    /**
     * 查詢所有的module id
     * @return 
     */
    public List<Integer> queryIdList() {
        return source.queryColumnList("moduleid", Module.class, new BasedFilterBean());
    }
    
    /**
     * 查询所有正常状态的module id
     * @return 
     */
    public List<Integer> queryNormalIdList() {
        ModuleBean bean = new ModuleBean();
        bean.setStatus(10);
        return source.queryColumnList("moduleid", Module.class, bean);
    }
    
    public Module findModule(ModuleBean bean) {
        return source.find(Module.class, bean);
    }
    
    public void insert(Module module) {
        source.insert(module);
    }
    
    public void update(Module module) {
        source.updateColumns(module, "modulename", "moduledesc", "updatetime", "updater", "status");
    }
}
