/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.monitor.entity.AlertRecord;
import com.reo.automation.qaoss.monitor.filter.AlertRecordBean;
import com.reo.automation.qaoss.base.service.BasedMonitorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.source.FilterBean;
import org.redkale.source.Flipper;
import org.redkale.source.Range.LongRange;
import org.redkale.util.Sheet;

/**
 *
 * @author jerry.ouyang
 */
public class AlertRecordService extends BasedMonitorService {
    @Resource
    private ModuleService moduleService;
    
    @Resource
    private AlertTypeService alertTypeService;
    
    public Sheet querySheet(Flipper flipper, FilterBean bean) {
        Map<Integer, String> moduleMap = moduleService.queryAll2();
        Map<Integer, String> alertTypeMap = alertTypeService.queryAll2();
        Sheet<AlertRecord> sheet = source.querySheet(AlertRecord.class, flipper, bean);
        for(AlertRecord record : sheet.list()) {
            record.setModulename(moduleMap.get(record.getModuleid()));
            record.setAlerttypename(alertTypeMap.get(record.getAlerttypeid()));
        }
        return sheet;
    }
    
    /**
     * 按條件查詢告警記錄
     * @param bean
     * @return 
     */
    public List<AlertRecord> queryList(AlertRecordBean bean) {
        Map<Integer, String> moduleMap = moduleService.queryAll2();
        Map<Integer, String> alerttypeMap = alertTypeService.queryAll2();
        
        List<AlertRecord> records = source.queryList(AlertRecord.class, bean);
        for(AlertRecord record : records) {
            record.setModulename(moduleMap.get(record.getModuleid()));
            record.setAlerttypename(alerttypeMap.get(record.getAlerttypeid()));
        }
        return records;
    }
    
    /**
     * 按模塊統計告警記錄
     * @return 
     */
    public Map<String, Integer> queryByModule(AlertRecordBean bean) {
        Map<String, Integer> map = new HashMap<String, Integer> ();
        List<AlertRecord> alertRecords = queryList(bean);
        
        for(AlertRecord record : alertRecords) {
            if(!map.containsKey(record.getModulename())) {
                map.put(record.getModulename(), 1);
            } else {
                map.replace(record.getModulename(), map.get(record.getModulename()) + 1);
            }
        }
        return map;
    }
    
    /**
     * 按告警信息类型统计告警记录
     * @param bean
     * @return 
     */
    public Map<String, Integer> queryByAlertType(AlertRecordBean bean) {
        Map<String, Integer> map = new HashMap<String, Integer> ();
        List<AlertRecord> alertRecords = queryList(bean);
        
        for(AlertRecord record : alertRecords) {
            if(!map.containsKey(record.getAlerttypename())) {
                map.put(record.getAlerttypename(), 1);
            } else {
                map.replace(record.getAlerttypename(), map.get(record.getAlerttypename()) + 1);
            }
        }
        return map;
    }
    
    public void insert(AlertRecord record) {
        source.insert(record);
    }
    
    public static void main(String[] args) throws Exception {
        AlertRecordService service = Application.singleton(AlertRecordService.class);
//        AlertRecordBean bean = new AlertRecordBean();
//        LongRange lr = new LongRange();
//        bean.setEnv("L");
//        lr.setMax(System.currentTimeMillis());
//        lr.setMin(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
//        bean.setAlerttime(lr);
//        service.queryByModule(bean).forEach((k, v) -> System.out.println(k + ": " + v));
//        service.queryByAlertType(bean).forEach((k, v) -> System.out.println(k + ": " + v));
        
        AlertRecord record = new AlertRecord();
        String content = "14399888327|1001|2001|【有鱼】个股hk00700 成交量数据不一致，差额超过0.9999999776482582%。有鱼成交量为: 242.56万股，新浪成交量为: 249.9286万股，富途成交量为: 249.93万股.|1|D|hk00700 成交量|||909||";
        String[] contents = content.split("\\|");
        System.out.println("长度 = " + contents.length);
        for(String c : contents) {
            System.out.println(c);
        }
        
        record.setAlerttime(Long.parseLong(contents[0]));
        record.setModuleid(Integer.parseInt(contents[1]));
        record.setAlerttypeid(Integer.parseInt(contents[2]));
        record.setAlertdescription(contents[3]);
        record.setAlertlevel(contents[4]);
        record.setEnv(contents[5]);
        record.setCasename(contents[6]);
        if(contents.length > 7)
            record.setExpectedresponse(contents[7]);
        if(contents.length > 8)
            record.setActualresponse(contents[8]);
        if(contents.length > 9)
            record.setRequestid(contents[9]);
        if(contents.length > 10)
            record.setRemark(contents[10]);
        service.insert(record);
    }
}
