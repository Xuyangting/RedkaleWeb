/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jerry.ouyang
 */
public class Constant {
    //bug对应的issue type值
    public static final String BugType = "10004";
    
    //jira中jira tester组对应的groupid
    public static final int JiraTestGroupId = 10510;
    
    //自定义字段defect cause对应的customfield;
    public static final long CustomFieldDefectCause = 10403l;
    
    //Rejected状态
    public static final String IssueRejectedStatus = "10110";
    
    //Test Version在jiradb.customfield中的id值
    public static final long TestVersionId = 10402l;
    
    public static final Map<String, String> Priority = new HashMap<String, String>();
    
    static {
        Priority.put("1", "Highest");
        Priority.put("2", "High");
        Priority.put("3", "Medium");
        Priority.put("4", "Low");
        Priority.put("5", "Lowest");
    }
    
    //一天的毫秒数
    public static final long ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000;
}
