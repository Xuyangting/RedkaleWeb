/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor;

import com.reo.automation.qaoss.base.utils.Utils;
import com.reo.automation.qaoss.monitor.entity.AlertRecord;
import com.reo.automation.qaoss.monitor.filter.AlertRecordBean;
import com.reo.automation.qaoss.monitor.filter.AlertRecordStatiBean;
import com.reo.automation.qaoss.monitor.service.AlertRecordService;
import com.reo.automation.qaoss.monitor.service.AlertRecordStatiService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import static org.redkale.oss.base.Services.ACTION_QUERY;
import org.redkale.service.RetResult;
import org.redkale.source.Flipper;
import org.redkale.source.Range;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/alertrecord/*")
public class AlertRecordServlet extends BaseServlet{
    @Resource
    private AlertRecordService alertRecordService;
    
    @Resource
    private AlertRecordStatiService arService;
    
    @WebAction(url = "/alertrecord/querysheet", actionid = ACTION_QUERY)
    public void querySheet(HttpRequest req, HttpResponse resp) {
        AlertRecordBean bean = req.getJsonParameter(AlertRecordBean.class, "bean");
        Flipper flipper = req.getFlipper();
        flipper.setSort("alerttime desc");
        resp.finishJson(alertRecordService.querySheet(flipper, bean));
    }
    
    @WebAction(url = "/alertrecord/querybymodule", actionid = ACTION_QUERY)
    public void queryByModule(HttpRequest req, HttpResponse resp) {
        AlertRecordBean bean = req.getJsonParameter(AlertRecordBean.class, "bean");
        resp.finishJson(alertRecordService.queryByModule(bean));
    }
    
    @WebAction(url = "/alertrecord/querybyalerttype", actionid = ACTION_QUERY)
    public void queryByAlertType(HttpRequest req, HttpResponse resp) {
        AlertRecordBean bean = req.getJsonParameter(AlertRecordBean.class, "bean");
        resp.finishJson(alertRecordService.queryByAlertType(bean));
    }
    
    @WebAction(url = "/alertrecord/querybyalerttime", actionid = ACTION_QUERY)
    public void queryByAlertTime(HttpRequest req, HttpResponse resp) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        AlertRecordStatiBean bean = req.getJsonParameter(AlertRecordStatiBean.class, "bean");
        
        // 开始规整longrange信息
        Range.LongRange range = bean.getAlerttimestamp();
        
        long min = range.getMin();
        long max = range.getMax();
        
        if(min > max || Utils.getBeginMils(min) > Utils.getBeginMils(max)) {
            resp.finishJson(new RetResult(OssRetCodes.RET_PARAMS_ILLEGAL, "开始时间大于结束时间"));
            return;
        }
        
        range.setMin(Utils.getBeginMils(min));
        
        if(max > Utils.currentBeginMils()) {
            range.setMax(Utils.currentPreviousMils());
        } else {
            range.setMax(Utils.getBeginMils(max));
        }
        
        bean.setAlerttimestamp(range);
        // 结束规整longrange信息
        
        arService.queryAllList(bean).stream().filter(e -> e.getCount()>0).sorted((r1, r2) -> {
            try {
                return df.parse(r1.getAlertdate()).compareTo(df.parse(r2.getAlertdate()));
            } catch (ParseException ex) {
                Logger.getLogger(BuildRecordServlet.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }).forEach(r -> map.put(r.getAlertdate(), r.getCount()));
        resp.finishJson(map);
    }
    
    /**
     * 插入不能使用鉴权信息，否则普通外部接口调用时会失败
     * @param req
     * @param resp 
     */
    @AuthIgnore
    @WebAction(url="/alertrecord/insert")
    public void insert(HttpRequest req, HttpResponse resp) {
        String content = req.getBodyUTF8();
        
        AlertRecord record = new AlertRecord();
        String[] contents = content.split("\\|");
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
        alertRecordService.insert(record);
        resp.finishJson(new RetResult(0));
    }
}
