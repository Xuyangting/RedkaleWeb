/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.notification;

import com.reo.automation.qaoss.base.chart.LineChartService;
import com.reo.automation.qaoss.base.service.Constant;
import com.reo.automation.qaoss.base.utils.EwsService;
import com.reo.automation.qaoss.base.utils.Utils;
import com.reo.automation.qaoss.monitor.entity.BuildRecordDetail;
import com.reo.automation.qaoss.monitor.filter.BuildRecordDetailBean;
import com.reo.automation.qaoss.monitor.filter.ModuleRequestMapBean;
import com.reo.automation.qaoss.monitor.service.BuildRecordDetailService;
import com.reo.automation.qaoss.monitor.service.ModuleRequestMapService;
import com.reo.automation.qaoss.monitor.service.ModuleService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.oss.base.BaseService;
import org.redkale.source.Range.LongRange;
import org.redkale.util.AnyValue;

/**
 *
 * @author jerry.ouyang
 */
public class BuildJobNitificationService extends BaseService {

    @Resource
    private BuildRecordDetailService brdService;

    @Resource
    private EwsService mail;

    @Resource
    private ModuleService moduleService;

    @Resource
    ModuleRequestMapService moduleRequestMapService;

    private static final String tablestyle = "MARGIN-RIGHT: auto; MARGIN-LEFT: auto;	font-family: verdana,arial,sans-serif;	font-size:12px;	color:#333333;	border-width: 1px;	border-color: #666666;	border-collapse: collapse;";
    private static final String thstyle = "border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;	background-color: #dedede;";
    private static final String tdstyle = "border-width: 1px;	padding: 8px;	border-style: solid;	border-color: #666666;	background-color: #ffffff;";

    private LinkedHashMap<String, Number> map100 = new LinkedHashMap<String, Number>();
    private LinkedHashMap<String, Number> map200 = new LinkedHashMap<String, Number>();
    private LinkedHashMap<String, Number> map500 = new LinkedHashMap<String, Number>();
    private LinkedHashMap<String, Number> map1000 = new LinkedHashMap<String, Number>();
    private LinkedHashMap<String, Number> successratemap = new LinkedHashMap<String, Number>();
    private String trendPicFileName;
    private String ratePicFileName;

    private List<String> picFileNames = new ArrayList<String>();

    private String startDate;
    private String endDate;

    @Override
    public void init(AnyValue conf) {
        ScheduledExecutorService es = Executors.newScheduledThreadPool(1, (r) -> {
            Thread t = new Thread(r);
            t.setName("BuildJobNitificationService-" + t.getName());
            t.setDaemon(true);
            return t;
        });

        long delay = calcDelay();
        logger.info(new Date() + " BuildJobNitificationService 定时任务: 距离" + Thread.currentThread().getName() + "开始还有" + delay / 1000 + "秒");
        es.scheduleAtFixedRate(() -> {
            logger.info(new Date() + " BuildJobNitificationService 定时任务: " + Thread.currentThread().getName() + "开始执行");
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String title = "质量周报 as of " + df.format(new Date(System.currentTimeMillis() - Constant.ONE_DAY_MILLISECONDS));
            String maillist = "Yanzheng.Wang@yff.com;qa-team@yff.com";

            try {
//                mailReport(title, maillist);
                mailModuleReport(title, maillist);
            } catch (ParseException ex) {
                Logger.getLogger(BuildJobNitificationService.class.getName()).log(Level.SEVERE, null, ex);
            }
            logger.info(new Date() + " BuildJobNitificationService 定时任务: " + Thread.currentThread().getName() + "结束执行");
        }, delay, Constant.ONE_DAY_MILLISECONDS * 7, TimeUnit.MILLISECONDS);
    }

    public long calcDelay() {
        Calendar cal = Calendar.getInstance();
        int delayDay = 2;
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if (weekDay <= 2) {
            delayDay -= weekDay;
        } else {
            delayDay = delayDay + 7 - weekDay;
        }

        long delay = delayDay * Constant.ONE_DAY_MILLISECONDS + 8 * 60 * 60 * 1000;
        long now = System.currentTimeMillis();
        long begin = Utils.getBeginMils(now);

        if (delayDay == 0) {
            if (now - begin >= 8 * 60 * 60 * 1000 && now - begin <= 10 * 60 * 60 * 1000) {
                delay = 0;
            } else if (now - begin <= 8 * 60 * 60 * 1000) {
                delay = 8 * 60 * 60 * 1000 - (now - begin);
            } else {
                delay = Constant.ONE_DAY_MILLISECONDS * 7 - (now - begin) + 8 * 60 * 60 * 1000;
            }
        } else {
            delay = delay - (now - begin);
        }

        return delay;
    }

    public void getDetailsForOneMonth(long buildjobid) {
        //先清理原来的统计结果
        if (!map100.isEmpty()) {
            map100.clear();
        }
        if (!map200.isEmpty()) {
            map200.clear();
        }
        if (!map500.isEmpty()) {
            map500.clear();
        }
        if (!map1000.isEmpty()) {
            map1000.clear();
        }
        if (!successratemap.isEmpty()) {
            successratemap.clear();
        }

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        long now = System.currentTimeMillis();
        for (int i = 27; i >= 0; i--) {
            BuildRecordDetailBean bean = new BuildRecordDetailBean();
            long end = Utils.getBeginMils(now) - 1 - i * Constant.ONE_DAY_MILLISECONDS;
            long start = Utils.getBeginMils(now) - (i + 1) * Constant.ONE_DAY_MILLISECONDS;
            LongRange range = new LongRange(start, end);
            logger.log(Level.INFO, new Date() + " " + Thread.currentThread().getName() + " 查询数据的时间段为: " + range.toString());

            bean.setBuildjobid(buildjobid);
            bean.setBuildtimestamp(range);
            List<BuildRecordDetail> details = brdService.queryList(bean);

            String calcDate = df.format(new Date(end));
            if (i == 6) {
                startDate = calcDate;
            }
            if (i == 0) {
                endDate = calcDate;
            }
            // 记录下所有的记录数量，包括成功的与失败的所有记录数量
            long total = details.size();
            logger.log(Level.INFO, new Date() + " " + Thread.currentThread().getName() + " 共查询到" + total + "条记录.");
            // 查找状态为成功的记录
            details = details.stream().filter(e -> (e.getResult().equals("PASSED") || e.getResult().equals("FIXED"))).collect(Collectors.toList());

            if (total != 0) {
                successratemap.put(calcDate, Utils.roundUpFloat(details.size() * 100f / total, 2));
            }

            //如果没有记录，则继续下一查询
            if (details.size() == 0) {
                continue;
            }
            logger.log(Level.FINEST, new Date() + " 其中成功状态的记录有" + details.size() + "条.");

            // 分别查找出请求响应时间小于100毫秒，小于200毫秒，小于500毫秒，小于1000毫秒的请求数量
            long onehundred = details.stream().filter(e -> e.getDuration() <= 100).count();
            long twohundreds = details.stream().filter(e -> e.getDuration() <= 200).count();
            long fivehundreds = details.stream().filter(e -> e.getDuration() <= 500).count();
            long onethousand = details.stream().filter(e -> e.getDuration() <= 1000).count();

            map100.put(calcDate, Utils.roundUpFloat(onehundred * 100f / details.size(), 2));
            map200.put(calcDate, Utils.roundUpFloat(twohundreds * 100f / details.size(), 2));
            map500.put(calcDate, Utils.roundUpFloat(fivehundreds * 100f / details.size(), 2));
            map1000.put(calcDate, Utils.roundUpFloat(onethousand * 100f / details.size(), 2));
        }
    }

    /**
     * 根据moduleid查询
     *
     * @param moduleid
     */
    public void getDetailsForOneMonth(int moduleid) {
        //先清理原来的统计结果
        if (!map100.isEmpty()) {
            map100.clear();
        }
        if (!map200.isEmpty()) {
            map200.clear();
        }
        if (!map500.isEmpty()) {
            map500.clear();
        }
        if (!map1000.isEmpty()) {
            map1000.clear();
        }
        if (!successratemap.isEmpty()) {
            successratemap.clear();
        }

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        long now = System.currentTimeMillis();
        for (int i = 27; i >= 0; i--) {
            BuildRecordDetailBean bean = new BuildRecordDetailBean();
            long end = Utils.getBeginMils(now) - 1 - i * Constant.ONE_DAY_MILLISECONDS;
            long start = Utils.getBeginMils(now) - (i + 1) * Constant.ONE_DAY_MILLISECONDS;
            LongRange range = new LongRange(start, end);
            logger.log(Level.INFO, new Date() + " " + Thread.currentThread().getName() + " 查询数据的时间段为: " + range.toString());

            ModuleRequestMapBean mrmbean = new ModuleRequestMapBean();
            mrmbean.setModuleid(moduleid);
            List<String> requestnames = moduleRequestMapService.getRequestNames(mrmbean);

            bean.setRequestnames(requestnames.toArray(new String[requestnames.size()]));
            bean.setBuildtimestamp(range);
            List<BuildRecordDetail> details = brdService.queryList(bean);

            String calcDate = df.format(new Date(end));
            if (i == 6) {
                startDate = calcDate;
            }
            if (i == 0) {
                endDate = calcDate;
            }
            // 记录下所有的记录数量，包括成功的与失败的所有记录数量
            long total = details.size();
            logger.log(Level.INFO, new Date() + " " + Thread.currentThread().getName() + " 共查询到" + total + "条记录.");
            // 查找状态为成功的记录
            details = details.stream().filter(e -> (e.getResult().equals("PASSED") || e.getResult().equals("FIXED"))).collect(Collectors.toList());

            if (total != 0) {
                successratemap.put(calcDate, Utils.roundUpFloat(details.size() * 100f / total, 2));
            }

            //如果没有记录，则继续下一查询
            if (details.size() == 0) {
                continue;
            }
            logger.log(Level.FINEST, new Date() + " 其中成功状态的记录有" + details.size() + "条.");

            // 分别查找出请求响应时间小于100毫秒，小于200毫秒，小于500毫秒，小于1000毫秒的请求数量
            long onehundred = details.stream().filter(e -> e.getDuration() <= 100).count();
            long twohundreds = details.stream().filter(e -> e.getDuration() <= 200).count();
            long fivehundreds = details.stream().filter(e -> e.getDuration() <= 500).count();
            long onethousand = details.stream().filter(e -> e.getDuration() <= 1000).count();

            map100.put(calcDate, Utils.roundUpFloat(onehundred * 100f / details.size(), 2));
            map200.put(calcDate, Utils.roundUpFloat(twohundreds * 100f / details.size(), 2));
            map500.put(calcDate, Utils.roundUpFloat(fivehundreds * 100f / details.size(), 2));
            map1000.put(calcDate, Utils.roundUpFloat(onethousand * 100f / details.size(), 2));
        }
    }

    public void getDetailsForOneWeek(long buildjobid) {
        //先清理原来的统计结果
        if (!map100.isEmpty()) {
            map100.clear();
        }
        if (!map200.isEmpty()) {
            map200.clear();
        }
        if (!map500.isEmpty()) {
            map500.clear();
        }
        if (!map1000.isEmpty()) {
            map1000.clear();
        }
        if (!successratemap.isEmpty()) {
            successratemap.clear();
        }

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        long now = System.currentTimeMillis();
        for (int i = 6; i >= 0; i--) {
            BuildRecordDetailBean bean = new BuildRecordDetailBean();
            long end = Utils.getBeginMils(now) - 1 - i * Constant.ONE_DAY_MILLISECONDS;
            long start = Utils.getBeginMils(now) - (i + 1) * Constant.ONE_DAY_MILLISECONDS;
            LongRange range = new LongRange(start, end);
            logger.log(Level.INFO, new Date() + " " + Thread.currentThread().getName() + " 查询数据的时间段为: " + range.toString());

            bean.setBuildjobid(buildjobid);
            bean.setBuildtimestamp(range);
            List<BuildRecordDetail> details = brdService.queryList(bean);

            String calcDate = df.format(new Date(end));
            if (i == 6) {
                startDate = calcDate;
            }
            if (i == 0) {
                endDate = calcDate;
            }
            // 记录下所有的记录数量，包括成功的与失败的所有记录数量
            long total = details.size();
            logger.log(Level.INFO, new Date() + " " + Thread.currentThread().getName() + " 共查询到" + total + "条记录.");
            // 查找状态为成功的记录
            details = details.stream().filter(e -> (e.getResult().equals("PASSED") || e.getResult().equals("FIXED"))).collect(Collectors.toList());

            if (total != 0) {
                successratemap.put(calcDate, Utils.roundUpFloat(details.size() * 100f / total, 2));
            }

            //如果没有记录，则继续下一查询
            if (details.size() == 0) {
                continue;
            }
            logger.log(Level.FINEST, new Date() + " 其中成功状态的记录有" + details.size() + "条.");

            // 分别查找出请求响应时间小于100毫秒，小于200毫秒，小于500毫秒，小于1000毫秒的请求数量
            long onehundred = details.stream().filter(e -> e.getDuration() <= 100).count();
            long twohundreds = details.stream().filter(e -> e.getDuration() <= 200).count();
            long fivehundreds = details.stream().filter(e -> e.getDuration() <= 500).count();
            long onethousand = details.stream().filter(e -> e.getDuration() <= 1000).count();

            map100.put(calcDate, Utils.roundUpFloat(onehundred * 100f / details.size(), 2));
            map200.put(calcDate, Utils.roundUpFloat(twohundreds * 100f / details.size(), 2));
            map500.put(calcDate, Utils.roundUpFloat(fivehundreds * 100f / details.size(), 2));
            map1000.put(calcDate, Utils.roundUpFloat(onethousand * 100f / details.size(), 2));
        }
    }

    public String drawDetailTrend(String title) {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        LineChartService lineChartService = new LineChartService(title + "请求平均响应时间", "", "%");
        lineChartService.setData("100毫秒", map100);
        lineChartService.setData("200毫秒", map200);
        lineChartService.setData("500毫秒", map500);
        lineChartService.setData("1000毫秒", map1000);
        return lineChartService.saveChartAsJPEG(df.format(new Date()) + "-" + title + "-trend.jpeg", 1200, 450);
    }

    public String drawDetailSuccessRate(String title) {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        LineChartService lineChartService = new LineChartService(title + "请求成功率", "", "%");
        lineChartService.setData("成功率", successratemap);
        return lineChartService.saveChartAsJPEG(df.format(new Date()) + "-" + title + "-rate.jpeg", 1200, 450);
    }

    @Deprecated
    public String report() throws ParseException {
        StringBuilder sb = new StringBuilder();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

//        getDetailsForOneWeek(3l);
        getDetailsForOneMonth(3l);

        sb.append("<html><head><meta charset=\"utf-8\"><style>	body {TEXT-ALIGN: center;font:normal normal 12px/20px verdana,arial,sans-serif;} div{ MARGIN-RIGHT: auto; MARGIN-LEFT: auto; } table {").append(tablestyle).append("} th {").append(thstyle).append(" } td {").append(tdstyle).append("} H1 {font-family: verdana,arial,sans-serif;font-size:17px;} img {width: 660px; height: 400px;}</style></meta></head><body>");
        sb.append("<div>本邮件统计数据内容源自于Jenkins监控任务收集的数据，由后台程序自动生成。</div>");
        sb.append("<div>以下图表数据统计开始时间: ").append(startDate).append(", 结束时间: ").append(endDate).append("").append("<br/><br/>");

        ratePicFileName = drawDetailSuccessRate("UC请求成功率");
        trendPicFileName = drawDetailTrend("UC请求平均响应时间");
        //画请求成功率的图形
        sb.append("<div><img src=\"cid:").append("1").append("\"><br/><br/><br/></div>");
        //画请求成功率的表格
        sb.append("<table><tr><th>日   期</th>");

        //只取开始时间到结束时间段内内容
        successratemap.keySet().forEach(key -> {
            try {
                if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                    sb.append("<td>").append(key).append("</td>");
                }
            } catch (ParseException ex) {
                Logger.getLogger(BuildJobNitificationService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb.append("</tr><tr><th>百分比</th>");

        //只取开始时间到结束时间段内内容
        successratemap.keySet().forEach(key -> {
            try {
                if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                    sb.append("<td>").append(successratemap.get(key)).append("</td>");
                }
            } catch (ParseException ex) {
                Logger.getLogger(BuildJobNitificationService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb.append("</tr></table><br/><br/>");

        //画请求响应时间的图形
        sb.append("<div><img src=\"cid:").append("2").append("\"><br/><br/><br/></div>");
        //画请求响应时间的表格
        sb.append("<table><tr><th>日期</th>");
        //只取开始时间到结束时间段内内容
        for (String key : map100.keySet()) {
            if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                sb.append("<td>").append(key).append("</td>");
            }
        }
        sb.append("</tr><tr><th>100毫秒达标率</th>");
        //只取开始时间到结束时间段内内容
        for (String key : map100.keySet()) {
            if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                sb.append("<td>").append(map100.get(key)).append("</td>");
            }
        }
        sb.append("</tr><tr><th>200毫秒达标率</th>");
        //只取开始时间到结束时间段内内容
        for (String key : map200.keySet()) {
            if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                sb.append("<td>").append(map200.get(key)).append("</td>");
            }
        }
        sb.append("</tr><tr><th>500毫秒达标率</th>");
        //只取开始时间到结束时间段内内容
        for (String key : map500.keySet()) {
            if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                sb.append("<td>").append(map500.get(key)).append("</td>");
            }
        }
        sb.append("</tr><tr><th>1000毫秒达标率</th>");
        //只取开始时间到结束时间段内内容
        for (String key : map1000.keySet()) {
            if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                sb.append("<td>").append(map1000.get(key)).append("</td>");
            }
        }
        sb.append("</tr></table><br/><br/>");

        sb.append("<div>更多详细信息请点击").append("<a href='http://10.9.8.20:9292/index.html'>这里</a>").append("</div>");
        sb.append("<div>用户名: visitor &nbsp;&nbsp 密码: Welcome123</div><br/>");
        sb.append("<div>如需退订、增加或者其它疑问请联系<a href='mailto:qa-auto@reorientgroup.com'>自动化测试组</a></div>");
        sb.append("</body>");
        logger.finest(sb.toString());
        return sb.toString();
    }

    public String reportByModule() throws ParseException {
        StringBuilder sb = new StringBuilder();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        sb.append("<html><head><meta charset=\"utf-8\"><style>	body {TEXT-ALIGN: center;font:normal normal 12px/20px verdana,arial,sans-serif;} div{ MARGIN-RIGHT: auto; MARGIN-LEFT: auto; } table {").append(tablestyle).append("} th {").append(thstyle).append(" } td {").append(tdstyle).append("} H1 {font-family: verdana,arial,sans-serif;font-size:17px;} img {width: 660px; height: 400px;}</style></meta></head><body>");
        sb.append("<div>本邮件统计数据内容源自于Jenkins监控任务收集的数据，由后台程序自动生成。</div>");
        sb.append("<div>以下图表数据统计开始时间: ").append(startDate == null ?  df.format(new Date(new Date().getTime() - Constant.ONE_DAY_MILLISECONDS * 7)) :startDate).append(", 结束时间: ").append(endDate == null ? df.format(new Date(new Date().getTime() - Constant.ONE_DAY_MILLISECONDS * 1)) : endDate).append("").append("<br/><br/>");

        List<Integer> moduleids = new ArrayList<Integer>();
//        List<Integer> moduleids = moduleService.queryNormalIdList();
        moduleids.add(1001);
        moduleids.add(1003);

        for (int i = 0; i < moduleids.size(); i++) {
            getDetailsForOneMonth(moduleids.get(i));
            picFileNames.add(drawDetailSuccessRate(moduleService.queryName(moduleids.get(i))));
            picFileNames.add(drawDetailTrend(moduleService.queryName(moduleids.get(i))));

            //画请求成功率的图形
            sb.append("<div><img src=\"cid:").append(i * 2 + 1).append("\"><br/><br/><br/></div>");
            //画请求成功率的表格
            sb.append("<table><tr><th>日   期</th>");

            //只取开始时间到结束时间段内内容
            successratemap.keySet().forEach(key -> {
                try {
                    if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                        sb.append("<td>").append(key).append("</td>");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BuildJobNitificationService.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sb.append("</tr><tr><th>百分比</th>");

            //只取开始时间到结束时间段内内容
            successratemap.keySet().forEach(key -> {
                try {
                    if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                        sb.append("<td>").append(successratemap.get(key)).append("</td>");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BuildJobNitificationService.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            sb.append("</tr></table><br/><br/>");

            //画请求响应时间的图形
            sb.append("<div><img src=\"cid:").append(i * 2 + 2).append("\"><br/><br/><br/></div>");
            //画请求响应时间的表格
            sb.append("<table><tr><th>日期</th>");
            //只取开始时间到结束时间段内内容
            for (String key : map100.keySet()) {
                if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                    sb.append("<td>").append(key).append("</td>");
                }
            }
            sb.append("</tr><tr><th>100毫秒达标率</th>");
            //只取开始时间到结束时间段内内容
            for (String key : map100.keySet()) {
                if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                    sb.append("<td>").append(map100.get(key)).append("</td>");
                }
            }
            sb.append("</tr><tr><th>200毫秒达标率</th>");
            //只取开始时间到结束时间段内内容
            for (String key : map200.keySet()) {
                if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                    sb.append("<td>").append(map200.get(key)).append("</td>");
                }
            }
            sb.append("</tr><tr><th>500毫秒达标率</th>");
            //只取开始时间到结束时间段内内容
            for (String key : map500.keySet()) {
                if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                    sb.append("<td>").append(map500.get(key)).append("</td>");
                }
            }
            sb.append("</tr><tr><th>1000毫秒达标率</th>");
            //只取开始时间到结束时间段内内容
            for (String key : map1000.keySet()) {
                if (df.parse(key).getTime() >= df.parse(startDate).getTime()) {
                    sb.append("<td>").append(map1000.get(key)).append("</td>");
                }
            }
            sb.append("</tr></table><br/><br/>");
        }

        sb.append("<div>更多详细信息请点击").append("<a href='http://10.9.8.20:9292/index.html'>这里</a>").append("</div>");
        sb.append("<div>用户名: visitor &nbsp;&nbsp 密码: Welcome123</div><br/>");
        sb.append("<div>如需退订、增加或者其它疑问请联系<a href='mailto:qa-auto@reorientgroup.com'>自动化测试组</a></div>");
        sb.append("</body>");
        logger.finest(sb.toString());
        return sb.toString();
    }

    @Deprecated
    public void mailReport(String title, String maillist) throws ParseException {
        mail.init();
        mail.send(title, report().toString(), new String[]{ratePicFileName, trendPicFileName}, maillist);
    }
    
    public void mailModuleReport(String title, String maillist) throws ParseException {
        mail.init();
        mail.send(title, reportByModule().toString(), picFileNames.toArray(new String[picFileNames.size()]), maillist);
    }

    public static void main(String[] args) throws Throwable {
        BuildJobNitificationService service = Application.singleton(BuildJobNitificationService.class);
//        service.mailReport("质量周报 as of 12/19/2016", "Yanzheng.Wang@yff.com;qa-team@yff.com");
//        service.mailReport("质量周报 as of 12/19/2016", "jerry.ouyang@yff.com");
//        System.out.println(service.calcDelay());
//        service.getDetailsForOneWeek(3l);
//        service.getDetailsForOneMonth(3l);
        service.mailModuleReport("质量周报 as of 02/27/2017", "Yanzheng.Wang@yff.com;qa-team@yff.com");
    }
}
