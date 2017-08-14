/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.utils;

import com.reo.automation.qaoss.base.service.Constant;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class Utils {
    public static float roundUpFloat(float f, int newScale) {
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(newScale, RoundingMode.HALF_UP);        
        return bd.floatValue();
    }
    
    public static List<Long> splitRange(LongRange range) {
        List<Long> list = new ArrayList<Long>();
        long starttime = range.getMin();
        long endtime = range.getMax();
        
        if(starttime > endtime) return null;
        
        long temp = starttime;        
        while(temp <= endtime) {
            Long l = new Long(temp);
            list.add(l);
            temp = temp + Constant.ONE_DAY_MILLISECONDS;
        }
        
        return list;
    }
    
    /**
     * 根据给定的时间，转换为给定时间的开始毫秒时间
     * @param timestamp
     * @return 
     */
    public static long getBeginMils(long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }
    
    /**
     * 取当前时间的开始毫秒时间
     * @return 
     */
    public static long currentBeginMils() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }
    
    /**
     * 取当前时间前一天的开始毫秒时间
     * @return 
     */
    public static long currentPreviousMils() {
        return currentBeginMils() - Constant.ONE_DAY_MILLISECONDS;
    }
}
