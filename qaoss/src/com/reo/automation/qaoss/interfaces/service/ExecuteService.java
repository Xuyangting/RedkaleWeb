/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.ExecuteEntity;
import com.reo.automation.qaoss.interfaces.filter.ExecuteFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class ExecuteService extends BasedTestService{
    /**
     * 通过execute_time查询job_id
     * @param execute_time
     * @return 
     */
    public int queryIDByExecuteTime(long execute_time) {
        ExecuteFilter bean = new ExecuteFilter();
        bean.setExecute_time(execute_time);
        return source.find(ExecuteEntity.class, bean).getId();
    }
    
    /**
     * 通过id查询test report
     * @param id
     * @return 
     */
    public String queryTestReportById(int id) {
        ExecuteFilter bean = new ExecuteFilter();
        bean.setId(id);
        return source.find(ExecuteEntity.class, bean).getTest_report();
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, ExecuteFilter bean) {
        return source.querySheet(ExecuteEntity.class, flipper, bean);
    }
    
    /**
     * 更新单个
     * @param bean 
     */
    public void update(ExecuteEntity bean) {
        source.updateColumns(bean, "job_id", "job_name", "job_chinese_name", "test_result", "test_report", "execute_by_author", "execute_time");
    }
    
    /**
     * 插入单个
     * @param bean 
     */
    public void insert(ExecuteEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个
     * @param bean 
     */
    public void delete(ExecuteEntity bean){
        source.delete(bean);
    }
    
}
