/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppExecuteEntity;
import com.reo.automation.qaoss.app.filter.AppExecuteFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppExecuteService   extends BasedAppService{
    @Resource
    private AppMobileService appMobileService;
    
    @Resource
    private AppTestJobService appTestJobService;
    
    @Resource
    private ApplicationService applicationService;
    
    @Resource
    private AppTestSuiteService appTestSuiteService;
    
    /**
     * 通过id查询test report
     * @param ctime
     * @return 
     */
    public Integer queryIdByCtime(long ctime) {
        AppExecuteFilter bean = new AppExecuteFilter();
        bean.setCtime(ctime);
        return source.find(AppExecuteEntity.class, bean).getId();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppExecuteFilter bean) {
        Sheet<AppExecuteEntity> sheet = source.querySheet(AppExecuteEntity.class, flipper, bean);
        for(AppExecuteEntity record : sheet.list()) {
            record.setMobile_name(appMobileService.queryMobileNameById(record.getMobile_id()));
            record.setJob_name(appTestJobService.queryJobNameById(record.getJob_id()));
            int suite_id = appTestJobService.querySuiteById(record.getJob_id());
            int platform_id = appTestSuiteService.queryPlatformById(suite_id);
            record.setPlatform_name(applicationService.queryNameById(platform_id));
        }
        return sheet;
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppExecuteEntity bean) {
        source.updateColumns(bean, "name", "platform_id", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppExecuteEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppExecuteEntity bean){
        source.delete(bean);
    }
}
