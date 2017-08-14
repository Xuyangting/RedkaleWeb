/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.tsdb.service;

import com.reo.automation.qaoss.base.service.BasedTsdbService;
import com.reo.automation.qaoss.tsdb.entity.ApiDuration;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.opentsdb.client.builder.MetricBuilder;
import org.opentsdb.client.response.SimpleHttpResponse;

/**
 *
 * @author jerry.ouyang
 */
public class ApiDurationService extends BasedTsdbService {
    private final String METRIC_NAME = "auto.api.duration";
        
    public SimpleHttpResponse insert(ApiDuration apiDuration) {
//        PoolingHttpClient client = new PoolingHttpClient();
        try {
            MetricBuilder builder = MetricBuilder.getInstance();

            builder.addMetric(this.METRIC_NAME)
                    .setDataPoint(apiDuration.getTimestamp(), apiDuration.getDuration())
                    .addTag("domain", apiDuration.getDomain())
                    .addTag("env", apiDuration.getEnv())
                    .addTag("host", apiDuration.getHost())
                    .addTag("module", apiDuration.getModule())
                    .addTag("name", apiDuration.getName())
                    .addTag("product", apiDuration.getProduct())
                    .addTag("result", apiDuration.getResult())
                    .addTag("type", apiDuration.getType());

            logger.fine(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS").format(new Date()) + " FINE " + builder.build());
            SimpleHttpResponse response = client.doPost(tsdbUrl + "/api/put/?details", builder.build());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
