/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.tsdb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.reo.automation.qaoss.tsdb.entity.ApiDuration;
import com.reo.automation.qaoss.tsdb.entity.AppDuration;
import com.reo.automation.qaoss.tsdb.service.ApiDurationService;
import com.reo.automation.qaoss.tsdb.service.AppDurationService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.opentsdb.client.response.SimpleHttpResponse;
import org.redkale.convert.json.JsonConvert;
import org.redkale.net.http.HttpRequest;
import org.redkale.net.http.HttpResponse;
import org.redkale.net.http.WebServlet;
import org.redkale.oss.base.BaseServlet;
import org.redkale.oss.base.OssRetCodes;
import org.redkale.service.RetResult;

/**
 *
 * @author jerry.ouyang
 */
@WebServlet("/tsdb/*")
public class TsdbServlet extends BaseServlet {

    @Resource
    private AppDurationService appDurationService;

    @Resource
    private ApiDurationService apiDurationService;

    @AuthIgnore
    @WebAction(url = "/tsdb/insertapp")
    public void insertAppDuration(HttpRequest req, HttpResponse resp) {
        String content = req.getBodyUTF8();
        logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + content);
        AppDuration appDuration = JsonConvert.root().convertFrom(AppDuration.class, content);
        SimpleHttpResponse response = appDurationService.insert(appDuration);

        if (response != null) {
            resp.finishJson(response);
        } else {
            resp.finishJson(new RetResult(OssRetCodes.RET_INTERAL_ERROR));
        }
    }

    @AuthIgnore
    @WebAction(url = "/tsdb/insertapps")
    public void insertAppDurations(HttpRequest req, HttpResponse resp) {
        String content = req.getBodyUTF8();
        logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + content);
        JSONArray jsonArray = com.alibaba.fastjson.JSON.parseArray(content);
        int all = jsonArray.size();
        int failed = 0;
        for (Object object : jsonArray) {
            JSONObject json = (JSONObject) com.alibaba.fastjson.JSON.toJSON(object);
            AppDuration appDuration = JsonConvert.root().convertFrom(AppDuration.class, json.toJSONString());
            SimpleHttpResponse response = appDurationService.insert(appDuration);

            if (response == null || response.getStatusCode() != 200) {
                logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + response == null ? " Insert TSDB Failed!!!" : " Insert TSDB Failed!!! " + response.getContent());
                failed += 1;
            }
        }
        resp.finishJson(new RetResult(0, "all: " + all + ", failed: " + failed));
    }

    @AuthIgnore
    @WebAction(url = "/tsdb/insertapi")
    public void insertApiDuration(HttpRequest req, HttpResponse resp) {
        String content = req.getBodyUTF8();
        logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + content);
        ApiDuration apiDuration = JsonConvert.root().convertFrom(ApiDuration.class, content);
        SimpleHttpResponse response = apiDurationService.insert(apiDuration);

        if (response != null) {
            resp.finishJson(response);
        } else {
            resp.finishJson(new RetResult(OssRetCodes.RET_INTERAL_ERROR));
        }
    }

    @AuthIgnore
    @WebAction(url = "/tsdb/insertapis")
    public void insertApiDurations(HttpRequest req, HttpResponse resp) {
        String content = req.getBodyUTF8();
//        logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + content);
        JSONArray jsonArray = com.alibaba.fastjson.JSON.parseArray(content);
        int all = jsonArray.size();
        int failed = 0;
        for (Object object : jsonArray) {
            JSONObject json = (JSONObject) com.alibaba.fastjson.JSON.toJSON(object);
            ApiDuration apiDuration = JsonConvert.root().convertFrom(ApiDuration.class, json.toJSONString());
            SimpleHttpResponse response = apiDurationService.insert(apiDuration);

            if (response == null || response.getStatusCode() != 200) {
//                logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + response == null ? " Insert TSDB Failed!!!" : " Insert TSDB Failed!!! " + response.getContent());
                failed += 1;
            }
        }

        resp.finishJson(new RetResult(0, "all: " + all + ", failed: " + failed));
    }

}
