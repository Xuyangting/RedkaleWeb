/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.TestCaseEntity;
import com.reo.automation.qaoss.interfaces.filter.TestCaseFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class TestCaseService extends BasedTestService{
    /**
     * 通过id查询测试用例中文名称
     * @param id
     * @return 
     */
    public String queryNameById(int id) {
        if(source.find(TestCaseEntity.class, id) == null){
            return "0";
        }
        else{
            return source.find(TestCaseEntity.class, id).getName();
        }              
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, TestCaseFilter bean) {
        return source.querySheet(TestCaseEntity.class, flipper, bean);
    }
    
    /**
     * 更新单个
     * @param bean 
     */
    public void update(TestCaseEntity bean) {
        source.updateColumns(bean, "ename", "name", "api", "inputparameters", "expect", "disable");
    }
    
    /**
     * 插入单个
     * @param bean 
     */
    public void insert(TestCaseEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个
     * @param bean 
     */
    public void delete(TestCaseEntity bean){
        source.delete(bean);
    }
}
