/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.ApplicationEntity;
import com.reo.automation.qaoss.app.filter.ApplicationFilter;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedAppService;
import java.util.HashMap;
import java.util.Map;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class ApplicationService  extends BasedAppService{
    /**
     * 返回一个map<moduleid, modulename>, 用于根据moduleid查找modulename
     * @return 
     */
    public Map<Integer, String> queryName() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        source.queryList(ApplicationEntity.class, new BasedFilterBean()).forEach(e -> map.put(e.getId(), e.getName()));
        return map;
    }
    
    /**
     * 通过id查询getName
     * @param id
     * @return 
     */
    public String queryNameById(int id) {
        ApplicationFilter bean = new ApplicationFilter();
        bean.setId(id);
        return source.find(ApplicationEntity.class, bean).getName();
    }
    
    /**
     * 通过id查询getEname
     * @param id
     * @return 
     */
    public String queryEnameById(int id) {
        ApplicationFilter bean = new ApplicationFilter();
        bean.setId(id);
        return source.find(ApplicationEntity.class, bean).getEname();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, ApplicationFilter bean) {
        return source.querySheet(ApplicationEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(ApplicationEntity bean) {
        source.updateColumns(bean, "name", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(ApplicationEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(ApplicationEntity bean){
        source.delete(bean);
    }
}
