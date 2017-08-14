/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;
import org.redkale.source.FilterGroup;

/**
 *
 * @author timen.xu
 */
public class ExecuteFilter implements FilterBean{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    private int job_id;
    
    private long execute_time;

    public long getExecute_time() {
        return execute_time;
    }

    public void setExecute_time(long execute_time) {
        this.execute_time = execute_time;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String job_name;
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String job_chinese_name;
    
    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_chinese_name() {
        return job_chinese_name;
    }

    public void setJob_chinese_name(String job_chinese_name) {
        this.job_chinese_name = job_chinese_name;
    }    
    
}
