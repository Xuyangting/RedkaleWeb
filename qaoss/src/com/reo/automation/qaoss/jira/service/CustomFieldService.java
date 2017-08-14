/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.service;

import com.reo.automation.qaoss.jira.entity.CustomFieldOption;
import com.reo.automation.qaoss.jira.entity.CustomFieldValue;
import com.reo.automation.qaoss.jira.filter.CustomFieldValueBean;
import com.reo.automation.qaoss.base.service.BasedJiraService;
import com.reo.automation.qaoss.base.service.Constant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.source.FilterExpress;
import org.redkale.source.FilterNode;

/**
 *
 * @author jerry.ouyang
 */
public class CustomFieldService extends BasedJiraService{
    @Resource
    private JiraIssueService jiraIssueService;
    
    /**
     * 查询customfieldoption中的指定内容
     * @return 
     */
    public Map<Long, String> queryCustomFieldOption(long[] customfields) {
        Map<Long, String> map = new HashMap<Long, String>();
        List<CustomFieldOption> list = source.queryList(CustomFieldOption.class, FilterNode.create("customfield", FilterExpress.IN, customfields));
        list.forEach(e -> map.put(e.getId(), e.getCustomvalue()));
        return map;
    }
    
    /**
     * 查询defect cause字段的bug
     * @param bean 
     */
    public Map<String, Integer> countEffectiveBugByDefectCause() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<Long, String> optionMap = queryCustomFieldOption(new long[]{Constant.CustomFieldDefectCause});
        List<Long> bugids = jiraIssueService.queryEffectiveBugIds();
        
        CustomFieldValueBean bean = new CustomFieldValueBean();
        bean.setIssues(bugids);
        bean.setCustomfield(Constant.CustomFieldDefectCause);
        List<CustomFieldValue> list = source.queryList(CustomFieldValue.class, bean);
        list.forEach(e -> {
            long val = Long.parseLong(e.getStringvalue());
            if(!map.containsKey(optionMap.get(val))) {
                map.put(optionMap.get(val), 1);
            } else {
                int value = map.get(optionMap.get(val)) + 1;
                map.put(optionMap.get(val), value);
            }
        });
        
        return map;
    }
    
    
    public static void main(String[] args) throws Exception {
        CustomFieldService service = Application.singleton(CustomFieldService.class);
        
        long begin = System.currentTimeMillis();
        service.countEffectiveBugByDefectCause().forEach((k, v) -> System.out.println(k + "---" + v));
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
