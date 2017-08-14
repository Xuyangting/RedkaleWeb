/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.base.service.Constant;
import com.reo.automation.qaoss.base.utils.Utils;
import com.reo.automation.qaoss.monitor.entity.BuildRecord;
import com.reo.automation.qaoss.monitor.entity.BuildRecordStatistics;
import com.reo.automation.qaoss.monitor.filter.BuildRecordBean;
import com.reo.automation.qaoss.monitor.filter.BuildRecordStatiBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class BuildRecordStatiService extends BasedMonitorService{    
    
    @Resource
    private BuildRecordService recordService;
    
    /**
     * 根据时间（指定天0时0分0秒对应的毫秒数，如 2016-12-07 可表示为 1481040000000）
     * @param buildjobid
     * @param timestamp 
     */
    public void insert(long buildjobid, long timestamp) {
        long starttime = timestamp;
        long endtime = timestamp + Constant.ONE_DAY_MILLISECONDS - 1;        
        LongRange range = new LongRange(starttime, endtime);
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
        
        BuildRecordBean bean = new BuildRecordBean();
        bean.setBuildjobid(buildjobid);
        bean.setBuildtimestamp(range);
        
        List<BuildRecord> list = recordService.queryResultList(bean);
        
        long total = (list == null || list.size() == 0) ? 0 : list.size();
        long success = (list == null || list.size() == 0) ? 0 : list.stream().filter( e -> e.getResult().equals("SUCCESS")).count();
        float successrate = (list == null || list.size() == 0) ? 10000 : Utils.roundUpFloat( success * 100f / total, 2);
        logger.finest("List<BuildRecord> ---> total = " + total + ", success = " + success + ", failed = " + (total - success));
        
        BuildRecordStatistics brs = new BuildRecordStatistics();
        brs.setBuildtimestamp(timestamp);
        brs.setBuildjobid(buildjobid);
        brs.setSuccess(success);
        brs.setFailed(total - success);
        brs.setSuccessrate(successrate);
        brs.setBuildrecorddate(sdf.format(new Date(timestamp)));
        brs.setInserttime(System.currentTimeMillis());        
        logger.finest(brs.toString());
        
        source.insert(brs);
    }
    
    /**
     * 根据查询条件查询统计记录，如果统计记录不存在，则会进行统计新增
     * @param bean
     * @return 
     */
    public List<BuildRecordStatistics> queryList(BuildRecordStatiBean bean) {
        // 1. 分拆获取期望的buildtimestamp值列表
        List<Long> etimes = Utils.splitRange(bean.getTimestamprange());
        List<Long> atimes = new ArrayList<Long> ();
        // 2. 查询所有满足条件的统计记录
        List<BuildRecordStatistics> temp = source.queryList(BuildRecordStatistics.class, bean);
        // 3. 获取查询结果中所有的buildtimestamp列表值
        temp.forEach(e -> atimes.add(e.getBuildtimestamp()));
        // 4. 用期望的buildtimestamp列表值减去已经存在的buildtimestamp列表值
        etimes.removeAll(atimes);
        // 5. 如果两个列表值相同，可直接返回查询结果
        if(etimes.size() == 0) return temp;
        // 5.2 两个会不相同，需要增加相应的记录
        for(long timestamp : etimes) {
            logger.finest("需要增加 BuildRecordStatistics 记录, buildjobid = " + bean.getBuildjobid() + ", buildtimestamp = " + timestamp);
            this.insert(bean.getBuildjobid(), timestamp);
        }
        // 5.3 返回完整的统计记录列表
        return source.queryList(BuildRecordStatistics.class, bean);
    }
    
    public static void main(String[] args) throws Exception {
        BuildRecordStatiService brsService = Application.singleton(BuildRecordStatiService.class);
        
        LongRange range = new LongRange();
        range.setMin(1480521600000l);
        range.setMax(1481040000000l);
        BuildRecordStatiBean bean = new BuildRecordStatiBean();
        bean.setTimestamprange(range);
        bean.setBuildjobid(10008001l);
        
        brsService.queryList(bean).stream().sorted((brs1, brs2) -> new Long(brs1.getBuildtimestamp()).compareTo(new Long(brs2.getBuildtimestamp()))).forEach( e -> System.out.println(e.getBuildrecorddate() + "---" + e.getSuccessrate()));
        
        System.out.println(Utils.currentBeginMils());
        System.out.println(Utils.getBeginMils(1481040050000l));
    }
}


