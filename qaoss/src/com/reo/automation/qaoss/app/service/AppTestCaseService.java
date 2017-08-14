/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppTestCaseEntity;
import com.reo.automation.qaoss.app.filter.AppTestCaseFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppTestCaseService  extends BasedAppService{
    @Resource
    private ApplicationService applicationService;
    
    @Resource
    private AppModuleService appModuleService;
    
    @Resource
    private AppCaseStepService appCaseStepService;
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppTestCaseFilter bean) {
        Map<Integer, String> appMap = applicationService.queryName();
        Map<Integer, String> moduleMap = appModuleService.queryName();
        
        Sheet<AppTestCaseEntity> sheet = source.querySheet(AppTestCaseEntity.class, flipper, bean);
        for(AppTestCaseEntity record : sheet.list()) {
            record.setPlatform_name(appMap.get(record.getPlatform_id()));
            record.setModule_name(moduleMap.get(record.getModule_id()));
            record.setTest_step_num(
                    String.valueOf(appCaseStepService.queryStepNumByCaseId(flipper, record.getId(), "android").getTotal()) + 
                            "/" + 
                            String.valueOf(appCaseStepService.queryStepNumByCaseId(flipper, record.getId(), "ios").getTotal())
            );
        }
        return sheet;
    }
    
    /**
     * 通过id查询test report
     * @param id
     * @return 
     */
    public String queryCaseNameById(int id) {
        AppTestCaseFilter bean = new AppTestCaseFilter();
        bean.setId(id);
        return source.find(AppTestCaseEntity.class, bean).getEname();
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppTestCaseEntity bean) {
        source.updateColumns(bean, "ename", "platform_id", "module_id", "description", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppTestCaseEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppTestCaseEntity bean){
        source.delete(bean);
    }
}
