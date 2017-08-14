/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.service;

import com.reo.automation.qaoss.app.entity.AppTestJobEntity;
import com.reo.automation.qaoss.app.filter.AppTestJobFilter;
import com.reo.automation.qaoss.base.service.BasedAppService;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class AppTestJobService   extends BasedAppService{
    @Resource
    private AppTestSuiteService appTestSuiteService;
    
    /**
     * 通过id查询JobName
     * @param id
     * @return 
     */
    public String queryJobNameById(int id) {
        AppTestJobFilter bean = new AppTestJobFilter();
        bean.setId(id);
        return source.find(AppTestJobEntity.class, bean).getJob_name();
    }
    
    /**
     * 通过id查询Jenkins
     * @param id
     * @return 
     */
    public String queryJenkinsById(int id) {
        AppTestJobFilter bean = new AppTestJobFilter();
        bean.setId(id);
        return source.find(AppTestJobEntity.class, bean).getJenkins();
    }
    
    /**
     * 通过id查询Jenkins
     * @param id
     * @return 
     */
    public int querySuiteById(int id) {
        AppTestJobFilter bean = new AppTestJobFilter();
        bean.setId(id);
        return source.find(AppTestJobEntity.class, bean).getSuite_id();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, AppTestJobFilter bean) {
        Sheet<AppTestJobEntity> sheet = source.querySheet(AppTestJobEntity.class, flipper, bean);
        for(AppTestJobEntity record : sheet.list()) {
            record.setSuite_name(appTestSuiteService.queryNameById(record.getSuite_id()));
        }
        return sheet;
    }
    
    /**
     * 更新
     * @param bean 
     */
    public void update(AppTestJobEntity bean) {
        source.updateColumns(bean, "job_name", "job_description", "suite_id", "jenkins", "creator", "ctime");
    }
    
    /**
     * 插入
     * @param bean 
     */
    public void insert(AppTestJobEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除
     * @param bean 
     */
    public void delete(AppTestJobEntity bean){
        source.delete(bean);
    }
}
