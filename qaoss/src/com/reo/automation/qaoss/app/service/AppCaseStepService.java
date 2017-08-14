/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppCaseStepEntity;
import com.reo.automation.qaoss.app.filter.AppCaseStepFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppCaseStepService  extends BasedAppService{
    /**
     * 通过id查询test report
     * @param id
     * @return 
     */
    public Sheet queryStepNumByCaseId(Flipper flipper, int id, String type_step) {
        AppCaseStepFilter bean = new AppCaseStepFilter();
        bean.setCase_id(id);
        bean.setStep_type(type_step);
        return source.querySheet(AppCaseStepEntity.class, flipper, bean);
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppCaseStepFilter bean) {
        return source.querySheet(AppCaseStepEntity.class, flipper, bean);
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppCaseStepEntity bean) {
        source.updateColumns(bean, "case_id", "step_type", "step_run_id", "step_method", "parameter_1", "parameter_2", "parameter_3");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppCaseStepEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppCaseStepEntity bean){
        source.delete(bean);
    }
}
