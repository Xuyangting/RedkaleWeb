/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.StatsAPIEntity;
import com.reo.automation.qaoss.interfaces.filter.StatsAPIFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class StatsAPIService extends BasedTestService{
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, StatsAPIFilter bean) {
        return source.querySheet(StatsAPIEntity.class, flipper, bean);
    }
}
