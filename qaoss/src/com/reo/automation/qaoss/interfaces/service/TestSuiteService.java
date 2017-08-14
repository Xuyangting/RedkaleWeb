/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.TestSuiteEntity;
import com.reo.automation.qaoss.interfaces.filter.TestSuiteFilter;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class TestSuiteService extends BasedTestService{
    @Resource
    private TestSuiteCaseService testSuiteCaseService;
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, TestSuiteFilter bean) { 
        Sheet<TestSuiteEntity> sheet = source.querySheet(TestSuiteEntity.class, flipper, bean);
        for(TestSuiteEntity record : sheet.list()) {
            record.setCase_num((int)testSuiteCaseService.queryByPackageId(flipper, record.getId()).getTotal());
        }
        return sheet;
    }
    
    /**
     * 更新单个
     * @param bean 
     */
    public void update(TestSuiteEntity bean) {
        source.updateColumns(bean, "name", "description", "ctime");
    }
    
    /**
     * 插入单个
     * @param bean 
     */
    public void insert(TestSuiteEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个
     * @param bean 
     */
    public void delete(TestSuiteEntity bean){
        source.delete(bean);
    }
    
    /**
     * 通过id查询集合的名称
     * @param id
     * @return 
     */
    public String queryNameById(int id) {
        if(source.find(TestSuiteEntity.class, id) == null){
            return "0";
        }
        else{
            return source.find(TestSuiteEntity.class, id).getName();
        } 
    }
}
