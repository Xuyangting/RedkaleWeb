/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppSuiteCaseEntity;
import com.reo.automation.qaoss.app.filter.AppSuiteCaseFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppSuiteCaseService   extends BasedAppService{ 
    @Resource
    private AppTestCaseService appTestCaseService;
    /**
     * 通过packageid进行查询
     * @param flipper
     * @param suite_id
     * @return 
     */
    public Sheet queryBySuiteId(Flipper flipper, int suite_id) {
        AppSuiteCaseFilter bean = new AppSuiteCaseFilter();
        bean.setSuite_id(suite_id);
        return source.querySheet(AppSuiteCaseEntity.class, flipper, bean);
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppSuiteCaseFilter bean) {
        Sheet<AppSuiteCaseEntity> sheet = source.querySheet(AppSuiteCaseEntity.class, flipper, bean);
        for(AppSuiteCaseEntity record : sheet.list()) {
            record.setCase_name(appTestCaseService.queryCaseNameById(record.getCase_id()));
        }
        return sheet;
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppSuiteCaseEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppSuiteCaseEntity bean){
        source.delete(bean);
    }
}
