/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.tsdb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.reo.automation.qaoss.base.service.BasedTsdbService;
import com.reo.automation.qaoss.tsdb.entity.AppDuration;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opentsdb.client.ExpectResponse;
import org.opentsdb.client.builder.MetricBuilder;
import org.opentsdb.client.request.QueryBuilder;
import org.opentsdb.client.request.SubQueries;
import org.opentsdb.client.response.SimpleHttpResponse;
import org.opentsdb.client.util.Aggregator;
import org.redkale.source.Range.LongRange;

/**
 *
 * @author jerry.ouyang
 */
public class AppDurationService extends BasedTsdbService {

    private final String METRIC_NAME = "auto.app.duration";

    public SimpleHttpResponse insert(AppDuration appDuration) {
        try {
            MetricBuilder builder = MetricBuilder.getInstance();

            builder.addMetric(this.METRIC_NAME)
                    .setDataPoint(appDuration.getTimestamp(), appDuration.getDuration())
                    .addTag("env", appDuration.getEnv())
                    .addTag("module", appDuration.getModule())
                    .addTag("product", appDuration.getProduct())
                    .addTag("result", appDuration.getResult())
                    .addTag("type", appDuration.getType());

            logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + builder.build());
            SimpleHttpResponse response = client.doPost(tsdbUrl + "/api/put/?details", builder.build());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Long, Integer> queryByNameForDay(LongRange range) {
        Map<Long, Integer> result = new LinkedHashMap<Long, Integer>();
        QueryBuilder builder = QueryBuilder.getInstance();
        SubQueries subQueries = new SubQueries();

        subQueries.addMetric(this.METRIC_NAME).addAggregator(Aggregator.avg.toString()).addDownsample("1d-" + Aggregator.avg.toString());
        builder.getQuery().addStart(range.getMin()).addEnd(range.getMax()).addSubQuery(subQueries);

        try {
            logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + builder.build());
        } catch (IOException ex) {
            Logger.getLogger(AppDurationService.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            SimpleHttpResponse response = httpClient.pushQueries(builder, ExpectResponse.STATUS_CODE);
            
            if (response.getStatusCode() >= 200 && response.getStatusCode() <= 300) {
                String content = response.getContent();
                JSONArray jsonArray = com.alibaba.fastjson.JSON.parseArray(content);
                for (Object object : jsonArray) {
                    JSONObject json = (JSONObject) com.alibaba.fastjson.JSON.toJSON(object);
                    String dps = json.getString("dps");
                    Map<String, String> map = com.alibaba.fastjson.JSON.parseObject(dps, Map.class);

                    for (Map.Entry entry : map.entrySet()) {
                        result.put(Long.parseLong(entry.getKey().toString()) * 1000, (int) Double.parseDouble(entry.getValue().toString()));
                    }
                }
            } else {
                logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + response.getStatusCode() + " - " + response.getContent());
                return null;
            }
        } catch (IOException ex) {
            Logger.getLogger(AppDurationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
