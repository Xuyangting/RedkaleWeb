/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppTestSuiteEntity;
import com.reo.automation.qaoss.app.filter.AppTestSuiteFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppTestSuiteService extends BasedAppService{
    @Resource
    private AppSuiteCaseService appSuiteCaseService;
    
    @Resource
    private ApplicationService applicationService;
    
    /**
     * 通过id查询 getPlatform_id
     * @param id
     * @return 
     */
    public int queryPlatformById(int id) {
        AppTestSuiteFilter bean = new AppTestSuiteFilter();
        bean.setId(id);
        return source.find(AppTestSuiteEntity.class, bean).getPlatform_id();
    }
    
    /**
     * 通过id查询 getPlatform_id
     * @param id
     * @return 
     */
    public String queryNameById(int id) {
        AppTestSuiteFilter bean = new AppTestSuiteFilter();
        bean.setId(id);
        return source.find(AppTestSuiteEntity.class, bean).getName();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppTestSuiteFilter bean) {
        Sheet<AppTestSuiteEntity> sheet = source.querySheet(AppTestSuiteEntity.class, flipper, bean);
        for(AppTestSuiteEntity record : sheet.list()) {
            record.setCase_num((int)appSuiteCaseService.queryBySuiteId(flipper, record.getId()).getTotal());
            record.setPlatform_name(applicationService.queryNameById(record.getPlatform_id()));
        }
        return sheet;
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppTestSuiteEntity bean) {
        source.updateColumns(bean, "name", "platform_id", "description", "creator", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppTestSuiteEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppTestSuiteEntity bean){
        source.delete(bean);
    }
}
