/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.monitor.entity.AlertType;
import com.reo.automation.qaoss.monitor.filter.AlertTypeBean;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedMonitorService;
import java.util.HashMap;
import java.util.Map;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/**
 *
 * @author jerry.ouyang
 */
public class AlertTypeService extends BasedMonitorService{
    public String queryNameById(int alerttypeid) {
        AlertTypeBean bean = new AlertTypeBean();
        bean.setAlerttypeid(alerttypeid);
        return source.find(AlertType.class, bean).getAlerttypename();
    }
    
    /**
     * 返回map<alerttypename, alerttypeid>
     * @return 
     */
    public Map<String, Integer> queryAll() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        source.queryList(AlertType.class, new BasedFilterBean()).forEach(e -> map.put(e.getAlerttypename(), e.getAlerttypeid()));
        return map;
    }
    
    /**
     * 返回map<alerttypeid, alerttypename>
     * @return 
     */
    public Map<Integer, String> queryAll2() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        source.queryList(AlertType.class, new BasedFilterBean()).forEach(e -> map.put(e.getAlerttypeid(), e.getAlerttypename()));
        return map;
    }
    
    public AlertType findAlertType(AlertTypeBean bean) {
        return source.find(AlertType.class, bean);
    }
    
    public void insert(AlertType bean) {
        source.insert(bean);
    }
    
    public void update(AlertType bean) {
        source.updateColumns(bean, "alerttypename", "alerttypedesc", "updatetime", "updater", "status");
    }

    public Sheet querySheet(Flipper flipper, AlertTypeBean bean) {
        return source.querySheet(AlertType.class, flipper, bean);
    }
}
