    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.TestJobEntity;
import com.reo.automation.qaoss.interfaces.filter.TestJobFilter;
import javax.annotation.Resource;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class TestJobService extends BasedTestService{
    @Resource
    private TestSuiteService testSuiteService;
    
    /**
     * 通过测试任务id查询测试任务信息
     * @param job_id
     * @return 
     */
    public String queryByJobId(int job_id) {
        if(source.find(TestJobEntity.class, job_id) == null){
            return "0";
        }
        else{
            return source.find(TestJobEntity.class, job_id).getName();
        }              
    }
    
    /**
     * 通过测试任务id查询测试任务信息
     * @param job_id
     * @return 
     */
    public String queryJenkinsByJobId(int job_id) {
        if(source.find(TestJobEntity.class, job_id) == null){
            return "0";
        }
        else{
            return source.find(TestJobEntity.class, job_id).getJenkins();
        }              
    }
    
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, TestJobFilter bean) {
        return source.querySheet(TestJobEntity.class, flipper, bean);
    }
    
    /**
     * 更新单个
     * @param bean 
     */
    public void update(TestJobEntity bean) {
        source.updateColumns(bean, "name", "chinesename", "define", "env", "testpackage", "retryCount",
                "warningThreshold", "warningType", "emailAlert", "wechatAlert", "jenkins","creator", "ctime");
    }
    
    /**
     * 插入单个
     * @param bean 
     */
    public void insert(TestJobEntity bean) {
        source.insert(bean);
    }
    
    
    /**
     * 删除单个
     * @param bean 
     */
    public void delete(TestJobEntity bean){
        source.delete(bean);
    }
}
