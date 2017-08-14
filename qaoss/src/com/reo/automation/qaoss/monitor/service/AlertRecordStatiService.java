/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.base.service.Constant;
import com.reo.automation.qaoss.base.utils.Utils;
import com.reo.automation.qaoss.monitor.entity.AlertRecord;
import com.reo.automation.qaoss.monitor.entity.AlertRecordStatistics;
import com.reo.automation.qaoss.monitor.filter.AlertRecordBean;
import com.reo.automation.qaoss.monitor.filter.AlertRecordStatiBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
public class AlertRecordStatiService extends BasedMonitorService {

    @Resource
    private AlertRecordService arService;

    @Override
    public void init(AnyValue conf) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1, (r) -> {
            Thread t = new Thread(r);
            t.setName("AlertRecordStatiService-" + t.getName());
            t.setDaemon(true);
            return t;
        });

        long delay = calcDelay();
        logger.info(new Date() + " AlertRecordStatiService 定时任务: 距离" + Thread.currentThread().getName() + "开始还有" + delay / 1000 + "秒");
        es.scheduleAtFixedRate(() -> {
            logger.info(new Date() + " AlertRecordStatiService 定时任务: " + Thread.currentThread().getName() + "开始执行");
            AlertRecordStatiBean bean = new AlertRecordStatiBean();
            //结束时间为当前时间的前1天
            long end = Utils.getBeginMils(System.currentTimeMillis() - Constant.ONE_DAY_MILLISECONDS);
            //开始时间为当前时间的前30天
            long start = Utils.getBeginMils(System.currentTimeMillis() - 30 * Constant.ONE_DAY_MILLISECONDS);
            /**
             * 设置定时任务在每天1:00统计前1天到前30天的数据
             */
            LongRange range = new LongRange(start, end);
            bean.setEnv("L");
            queryAllList(bean);

            bean.setEnv("S");
            queryAllList(bean);
            
            bean.setEnv("Q");
            queryAllList(bean);
            
            logger.info(new Date() + " AlertRecordStatiService 定时任务: " + Thread.currentThread().getName() + "结束执行");
        }, delay, Constant.ONE_DAY_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

    //计算当前时间与当天凌晨1点相差的毫秒数
    private long calcDelay() {
        long delay = 0l;
        long now = System.currentTimeMillis();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 10);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        delay = now >= cal.getTimeInMillis() ? cal.getTimeInMillis() + Constant.ONE_DAY_MILLISECONDS - now : cal.getTimeInMillis() - now;
        return delay;
    }

    public void insert(String env, long timestamp) {
        long start = timestamp;
        long end = timestamp + Constant.ONE_DAY_MILLISECONDS - 1;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        AlertRecordBean bean = new AlertRecordBean();
        bean.setAlerttime(new LongRange(start, end));
        bean.setEnv(env);
        List<AlertRecord> list = arService.queryList(bean);

        AlertRecordStatistics ars = new AlertRecordStatistics();
        ars.setAlertdate(sdf.format(new Date(timestamp)));
        ars.setAlerttimestamp(timestamp);
        ars.setCount(list.size() == 0 ? -1 : list.size());
        ars.setEnv(env);
        ars.setInserttime(System.currentTimeMillis());
        source.insert(ars);
    }

    public List<AlertRecordStatistics> queryAllList(AlertRecordStatiBean bean) {
        // 1. 分拆获取期望的buildtimestamp值列表
        List<Long> etimes = Utils.splitRange(bean.getAlerttimestamp());
        List<Long> atimes = new ArrayList<Long>();
        // 2. 查询所有满足条件的统计记录        
        List<AlertRecordStatistics> list = source.queryList(AlertRecordStatistics.class, bean);
        // 3. 获取查询结果中所有的buildtimestamp列表值
        list.forEach(e -> atimes.add(e.getAlerttimestamp()));
        // 4. 用期望的buildtimestamp列表值减去已经存在的buildtimestamp列表值
        etimes.removeAll(atimes);
        // 5. 如果两个列表值相同，可直接返回查询结果
        if (etimes.size() == 0) {
            return list;
        }
        // 5.2 两个list不相同，需要增加相应的记录
        for (long timestamp : etimes) {
            logger.finest("需要增加 Alert Record Statistics 记录, alerttimestamp = " + timestamp);
            this.insert(bean.getEnv(), timestamp);
        }
        // 5.3 返回完整的统计记录列表        
        return source.queryList(AlertRecordStatistics.class, bean);
    }

    public static void main(String[] args) throws Exception {
        AlertRecordStatiService service = Application.singleton(AlertRecordStatiService.class);

        AlertRecordStatiBean bean = new AlertRecordStatiBean();
        bean.setAlerttimestamp(new LongRange(1480521600000l, 1481500800000l));
        bean.setEnv("L");
        service.queryAllList(bean).forEach(e -> System.out.println(e.toString()));
    }
}
