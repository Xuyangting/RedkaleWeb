/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.monitor.entity.ModuleRequestMap;
import com.reo.automation.qaoss.monitor.filter.ModuleRequestMapBean;
import java.util.List;

/**
 *
 * @author jerry.ouyang
 */
public class ModuleRequestMapService extends BasedMonitorService{
    public List<String> getRequestNames(ModuleRequestMapBean bean) {
        return source.queryColumnList("requestname", ModuleRequestMap.class, bean);
    }
}
