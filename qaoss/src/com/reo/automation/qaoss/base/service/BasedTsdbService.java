/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.opentsdb.client.HttpClientImpl;
import org.opentsdb.client.PoolingHttpClient;
import org.redkale.oss.base.BaseService;
import org.redkale.util.AnyValue;

/**
 *
 * @author jerry.ouyang
 */
public class BasedTsdbService extends BaseService{
    @Resource(name="APP_HOME")
    protected String apphome;
    
    protected PoolingHttpClient client = new PoolingHttpClient();
    
    protected HttpClientImpl httpClient;
    
    protected String tsdbUrl;

    @Override
    public void init(AnyValue conf) {
        InputStream is = null;
        try {
            Properties prop = new Properties();
            is = new FileInputStream(apphome + "/conf/tsdb.properties");
            prop.load(is);
            tsdbUrl = prop.getProperty("url");
            httpClient = new HttpClientImpl(tsdbUrl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BasedTsdbService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BasedTsdbService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(BasedTsdbService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
