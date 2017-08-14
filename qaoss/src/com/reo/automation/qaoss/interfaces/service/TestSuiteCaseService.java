/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.TestSuiteCaseEntity;
import com.reo.automation.qaoss.interfaces.filter.TestSuiteCaseFilter;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class TestSuiteCaseService extends BasedTestService{
    @Resource
    private TestCaseService testCaseService;
    
    /**
     * 通过packageid进行查询
     * @param flipper
     * @param packageid
     * @return 
     */
    public Sheet queryByPackageId(Flipper flipper, int packageid) {
        TestSuiteCaseFilter bean = new TestSuiteCaseFilter();
        flipper.setSort("");
        bean.setPackageid(packageid);
        return source.querySheet(TestSuiteCaseEntity.class, flipper, bean);
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, TestSuiteCaseFilter bean) {
        Sheet<TestSuiteCaseEntity> sheet = source.querySheet(TestSuiteCaseEntity.class, flipper, bean);
        for(TestSuiteCaseEntity record : sheet.list()) {
            record.setCase_name(testCaseService.queryNameById(record.getCaseid()));
        }
        return sheet;
    }
    
    /**
     * 更新单个
     * @param bean 
     */
    public void update(TestSuiteCaseEntity bean) {
        source.updateColumns(bean, "stepid", "packageid", "caseid", "sno", "comment", "cache", "transferParams", "depend", "dependtype", "ishttp", "sleepTime", "loopNum", "disable", "isStep", "retry");
    }
    
    /**
     * 插入单个
     * @param bean 
     */
    public void insert(TestSuiteCaseEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个
     * @param bean 
     */
    public void delete(TestSuiteCaseEntity bean){
        source.delete(bean);
    }
}
