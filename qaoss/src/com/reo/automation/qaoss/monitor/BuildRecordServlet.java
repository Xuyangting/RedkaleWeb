/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor;

import com.reo.automation.qaoss.base.utils.Utils;
import com.reo.automation.qaoss.monitor.filter.BuildRecordBean;
import com.reo.automation.qaoss.monitor.filter.BuildRecordDetailStatiBean;
import com.reo.automation.qaoss.monitor.filter.BuildRecordStatiBean;
import com.reo.automation.qaoss.monitor.service.BuildRecordDetailService;
import com.reo.automation.qaoss.monitor.service.BuildRecordDetailStatiService;
import com.reo.automation.qaoss.monitor.service.BuildRecordService;
import com.reo.automation.qaoss.monitor.service.BuildRecordStatiService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import org.redkale.service.RetResult;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/buildrecord/*")
public class BuildRecordServlet extends BaseServlet {

    @Resource
    private BuildRecordService buildRecordService;

    @Resource
    private BuildRecordStatiService buildRecordStatiService;

    @Resource
    private BuildRecordDetailService buildRecordDetailService;

    @Resource
    private BuildRecordDetailStatiService brdsService;

    @WebAction(url = "/buildrecord/querysheet", actionid = ACTION_QUERY)
    public void querySheet(HttpRequest req, HttpResponse resp) {
        BuildRecordBean bean = req.getJsonParameter(BuildRecordBean.class, "bean");
        resp.finishJson(buildRecordService.querySheet(req.getFlipper(), bean));
    }

    /**
     * 查询并返回构建记录的成功率
     *
     * @param req
     * @param resp
     */
    @WebAction(url = "/buildrecord/querystatilist", actionid = ACTION_QUERY)
    public void queryStatiList(HttpRequest req, HttpResponse resp) {
        // 确保按插入顺序进行存储
        Map<String, Float> map = new LinkedHashMap<String, Float>();
        BuildRecordStatiBean bean = req.getJsonParameter(BuildRecordStatiBean.class, "bean");

        // 开始规整longrange信息
        LongRange range = bean.getTimestamprange();

        long min = range.getMin();
        long max = range.getMax();

        if (min > max || Utils.getBeginMils(min) > Utils.getBeginMils(max)) {
            resp.finishJson(new RetResult(OssRetCodes.RET_PARAMS_ILLEGAL, "开始时间大于结束时间"));
            return;
        }

        range.setMin(Utils.getBeginMils(min));

        if (max > Utils.currentBeginMils()) {
            range.setMax(Utils.currentPreviousMils());
        } else {
            range.setMax(Utils.getBeginMils(max));
        }

        bean.setTimestamprange(range);
        // 结束规整longrange信息

        buildRecordStatiService.queryList(bean).stream().filter(brs -> brs.getSuccessrate() <= 100).sorted((brs1, brs2) -> new Long(brs1.getBuildtimestamp()).compareTo(new Long(brs2.getBuildtimestamp()))).forEach(e -> map.put(e.getBuildrecorddate(), e.getSuccessrate()));
        resp.finishJson(map);
    }

    /**
     * 查询所有的requestname
     *
     * @param req
     * @param resp
     */
    @WebAction(url = "/buildrecord/js/queryrequestname", actionid = ACTION_QUERY)
    public void queryRequestName(HttpRequest req, HttpResponse resp) {
        resp.finishJsResult("allrequestname", buildRecordDetailService.queryAllRequestName());
    }

    /**
     * 查询并返回指定请求的响应时间分布信息内容
     *
     * @param req
     * @param resp
     */
    @WebAction(url = "/buildrecord/querydetailstatilist", actionid = ACTION_QUERY)
    public void queryDetailStatiList(HttpRequest req, HttpResponse resp) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        // 确保按插入顺序进行存储
        BuildRecordDetailStatiBean bean = req.getJsonParameter(BuildRecordDetailStatiBean.class, "bean");

        // 开始规整longrange信息
        LongRange range = bean.getBuildtimestamp();

        long min = range.getMin();
        long max = range.getMax();

        if (min > max || Utils.getBeginMils(min) > Utils.getBeginMils(max)) {
            resp.finishJson(new RetResult(OssRetCodes.RET_PARAMS_ILLEGAL, "开始时间大于结束时间"));
            return;
        }

        range.setMin(Utils.getBeginMils(min));

        if (max > Utils.currentBeginMils()) {
            range.setMax(Utils.currentPreviousMils());
        } else {
            range.setMax(Utils.getBeginMils(max));
        }

        bean.setBuildtimestamp(range);
        resp.finishJson(brdsService.queryList(bean).stream().filter(e -> e.getOnehundred() <= 100).sorted((brd1, brd2) ->  {
            try {
                return df.parse(brd1.getBrdetaildate()).compareTo(df.parse(brd2.getBrdetaildate()));
            } catch (ParseException ex) {
                Logger.getLogger(BuildRecordServlet.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }).collect(Collectors.toList()));
    }

    /**
     * 查询并返回指定请求的成功率map
     *
     * @param req
     * @param resp
     */
    @WebAction(url = "/buildrecord/querydetailstatilist2", actionid = ACTION_QUERY)
    public void queryDetailStatiList2(HttpRequest req, HttpResponse resp) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        // 确保按插入顺序进行存储
        Map<String, Float> map = new LinkedHashMap<String, Float>();
        BuildRecordDetailStatiBean bean = req.getJsonParameter(BuildRecordDetailStatiBean.class, "bean");

        // 开始规整longrange信息
        LongRange range = bean.getBuildtimestamp();

        long min = range.getMin();
        long max = range.getMax();

        if (min > max || Utils.getBeginMils(min) > Utils.getBeginMils(max)) {
            resp.finishJson(new RetResult(OssRetCodes.RET_PARAMS_ILLEGAL, "开始时间大于结束时间"));
            return;
        }

        range.setMin(Utils.getBeginMils(min));

        if (max > Utils.currentBeginMils()) {
            range.setMax(Utils.currentPreviousMils());
        } else {
            range.setMax(Utils.getBeginMils(max));
        }

        bean.setBuildtimestamp(range);
        brdsService.queryList(bean).stream().filter(e -> e.getSuccessrate() <= 100).sorted((brd1, brd2) -> {
            try {
                return df.parse(brd1.getBrdetaildate()).compareTo(df.parse(brd2.getBrdetaildate()));
            } catch (ParseException ex) {
                Logger.getLogger(BuildRecordServlet.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }).forEach(e -> map.put(e.getBrdetaildate(), e.getSuccessrate()));
        resp.finishJson(map);
    }
}
