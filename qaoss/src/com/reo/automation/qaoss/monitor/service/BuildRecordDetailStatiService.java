/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.base.service.Constant;
import com.reo.automation.qaoss.base.utils.Utils;
import com.reo.automation.qaoss.monitor.entity.BuildRecordDetail;
import com.reo.automation.qaoss.monitor.entity.BuildRecordDetailStati;
import com.reo.automation.qaoss.monitor.filter.BuildRecordDetailBean;
import com.reo.automation.qaoss.monitor.filter.BuildRecordDetailStatiBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.source.Range.LongRange;
import org.redkale.util.AnyValue;

/**
 *
 * @author jerry.ouyang
 */
public class BuildRecordDetailStatiService extends BasedMonitorService {

    @Resource
    private BuildRecordDetailService brdService;
    
    

    @Override
    public void init(AnyValue conf) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1, (r) -> {
            Thread t = new Thread(r);
            t.setName("BuildRecordDetailStatiService-" + t.getName());
            t.setDaemon(true);
            return t;
        });
        
        long delay = calcDelay();
        logger.info(new Date() + " BuildRecordDetailStatiService 定时任务: 距离" + Thread.currentThread().getName() + "开始还有" + delay / 1000 + "秒");
        es.scheduleAtFixedRate(() -> {
            logger.info(new Date() + " BuildRecordDetailStatiService 定时任务: " + Thread.currentThread().getName() + "开始执行");
            BuildRecordDetailStatiBean bean = new BuildRecordDetailStatiBean();
            //结束时间为当前时间的前1天
            long end = Utils.getBeginMils(System.currentTimeMillis() - Constant.ONE_DAY_MILLISECONDS);
            //开始时间为当前时间的前30天
            long start = Utils.getBeginMils(System.currentTimeMillis() - 30 * Constant.ONE_DAY_MILLISECONDS);
            /**
             * 设置定时任务在每天1:00统计前1天到前30天的数据
             */
            LongRange range = new LongRange(start, end);
            bean.setBuildtimestamp(range);
            
            BuildRecordDetailBean brdbean = new BuildRecordDetailBean();
            List<String> requestNames = brdService.queryRequestNames(brdbean);
            for(String requestName : requestNames) {
                bean.setRequestname(requestName);
                queryList(bean);
            }
            logger.info(new Date() + " BuildRecordDetailStatiService 定时任务: " + Thread.currentThread().getName() + "结束执行");
        }, delay, Constant.ONE_DAY_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

    //计算当前时间与当天凌晨1点相差的毫秒数
    private long calcDelay() {
        long delay = 0l;
        long now = System.currentTimeMillis();
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        delay = now >= cal.getTimeInMillis() ? cal.getTimeInMillis() + Constant.ONE_DAY_MILLISECONDS - now : cal.getTimeInMillis() - now;
        return delay;
    }

    /**
     * 根据时间（指定天0时0分0秒对应的毫秒数，如 2016-12-07 可表示为 1481040000000）
     *
     * @param buildjobid
     * @param timestamp
     */
    public void insert(String requestname, long timestamp) {
        long starttime = Utils.getBeginMils(timestamp);
        long endtime = Utils.getBeginMils(timestamp) + Constant.ONE_DAY_MILLISECONDS - 1;
        LongRange range = new LongRange(starttime, endtime);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        BuildRecordDetailBean bean = new BuildRecordDetailBean();
        bean.setRequestname(requestname);
        bean.setBuildtimestamp(range);

        List<BuildRecordDetail> list = brdService.queryList(bean);

        BuildRecordDetailBean bean2 = new BuildRecordDetailBean();
        bean2.setRequestname(requestname);
        Map<String, Long> nameMap = brdService.queryRnameJobIdMap(bean2);

        logger.finest(new Date() + " " + "总共找到 " + list.size() + " 条待统计build record detail记录");

        //如果没有相应的build record detail内容，则添加一条新的记录，但onehundred等这些统计数据值均为10000
        if (list.size() == 0) {
            BuildRecordDetailStati brds = new BuildRecordDetailStati();
            brds.setBuildjobid(nameMap.get(requestname));
            brds.setBuildtimestamp(timestamp);
            brds.setRequestname(requestname);
            brds.setOnehundred(10000);
            brds.setTwohundreds(10000);
            brds.setFivehundreds(10000);
            brds.setOnethousand(10000);
            brds.setBrdetaildate(sdf.format(new Date(timestamp)));
            brds.setSuccess(0l);
            brds.setFailed(0l);
            brds.setSuccessrate(10000f);
            brds.setInserttime(System.currentTimeMillis());
            source.insert(brds);

            return;
        }

        long onehundred = 0l;
        long twohundreds = 0l;
        long fivehundreds = 0l;
        long onethousand = 0l;

        //1. 统计buildrecorddetail记录的响应时间分布情况
        for (BuildRecordDetail brd : list) {
            if (brd.getDuration() <= 1000 && brd.isSuccessful()) {
                onethousand += 1;
            }
            if (brd.getDuration() <= 500 && brd.isSuccessful()) {
                fivehundreds += 1;
            }
            if (brd.getDuration() <= 200 && brd.isSuccessful()) {
                twohundreds += 1;
            }
            if (brd.getDuration() <= 100 && brd.isSuccessful()) {
                onehundred += 1;
            }
        }

        //2. 统计buildrecorddetail记录的成功率
        long success = list.stream().filter(e -> e.isSuccessful()).count();
        long failed = list.size() - success;
        float successrate = Utils.roundUpFloat(success * 100f / list.size(), 2);

        BuildRecordDetailStati brds = new BuildRecordDetailStati();
        brds.setBuildjobid(nameMap.get(requestname));
        brds.setBuildtimestamp(Utils.getBeginMils(timestamp));
        brds.setRequestname(requestname);
        brds.setBrdetaildate(sdf.format(new Date(timestamp)));

        brds.setOnehundred(success == 0 ? 0 : Utils.roundUpFloat(onehundred * 100f / success, 2));
        brds.setTwohundreds(success == 0 ? 0 : Utils.roundUpFloat(twohundreds * 100f / success, 2));
        brds.setFivehundreds(success == 0 ? 0 : Utils.roundUpFloat(fivehundreds * 100f / success, 2));
        brds.setOnethousand(success == 0 ? 0 : Utils.roundUpFloat(onethousand * 100f / success, 2));
        brds.setSuccess(success);
        brds.setFailed(failed);
        brds.setSuccessrate(successrate);
        brds.setInserttime(System.currentTimeMillis());
        source.insert(brds);
    }

    /**
     * 根据查询条件查询统计记录，如果统计记录不存在，则会进行统计新增
     *
     * @param bean
     * @return
     */
    public List<BuildRecordDetailStati> queryList(BuildRecordDetailStatiBean bean) {
        // 1. 分拆获取期望的buildtimestamp值列表
        List<Long> etimes = Utils.splitRange(bean.getBuildtimestamp());
        List<Long> atimes = new ArrayList<Long>();
        // 2. 查询所有满足条件的统计记录
        List<BuildRecordDetailStati> temp = source.queryList(BuildRecordDetailStati.class, bean);
        // 3. 获取查询结果中所有的buildtimestamp列表值
        temp.forEach(e -> atimes.add(e.getBuildtimestamp()));
        // 4. 用期望的buildtimestamp列表值减去已经存在的buildtimestamp列表值
        etimes.removeAll(atimes);
        // 5. 如果两个列表值相同，可直接返回查询结果
        if (etimes.size() == 0) {
            return temp;
        }
        // 5.2 两个会不相同，需要增加相应的记录
        for (long timestamp : etimes) {
            logger.finest("需要增加 BuildRecordStatistics 记录, requestname = " + bean.getRequestname() + ", buildtimestamp = " + timestamp);
            this.insert(bean.getRequestname(), timestamp);
        }
        // 5.3 返回完整的统计记录列表
        return source.queryList(BuildRecordDetailStati.class, bean);
    }

    public static void main(String[] args) throws Exception {
        BuildRecordDetailStatiService brdsService = Application.singleton(BuildRecordDetailStatiService.class);
//        LongRange range = new LongRange();
//        range.setMin(1481040000000l);
//        range.setMax(1481587200000l);
//
//        BuildRecordDetailStatiBean bean = new BuildRecordDetailStatiBean();
//        bean.setBuildtimestamp(range);
//        bean.setRequestname("userLogin");
//        brdsService.queryList(bean).forEach(e -> System.out.println(e.toString()));
        System.out.println(brdsService.calcDelay());
    }
}
