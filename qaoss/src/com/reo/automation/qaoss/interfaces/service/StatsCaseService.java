/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.service;

import com.reo.automation.qaoss.base.service.BasedTestService;
import com.reo.automation.qaoss.interfaces.entity.StatsCaseEntity;
import com.reo.automation.qaoss.interfaces.filter.StatsCaseFilter;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author timen.xu
 */
public class StatsCaseService extends BasedTestService {
    /**
     * 查询所有
     * @param flipper
     * @param bean
     * @return 
     */
    public Sheet querySheet(Flipper flipper, StatsCaseFilter bean) {
        return source.querySheet(StatsCaseEntity.class, flipper, bean);
    }
}
