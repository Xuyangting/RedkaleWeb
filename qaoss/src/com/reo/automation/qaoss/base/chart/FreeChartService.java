/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.chart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import sun.misc.BASE64Encoder;

/**
 *
 * @author jerry.ouyang
 */
public abstract class FreeChartService{
    
    protected JFreeChart chart;
    
    public abstract void setFont();

    public String getChartAsBase64() {
        String base64 = "";

        BASE64Encoder encoder = new BASE64Encoder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        setFont();
        try {
            ChartUtilities.writeChartAsJPEG(baos, 1.0f, chart, 500, 440);
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        try {
            byte[] byteArray = new byte[is.available()];
            is.read(byteArray);
            is.close();
            base64 = encoder.encode(byteArray);
        } catch (IOException ex) {
            Logger.getLogger(FreeChartService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return base64;
    }
    
    public String saveChartAsJPEG(String filename, int width, int height) {
        setFont();
        String rootpath = System.getProperty("user.dir");
        if(rootpath.contains("bin"))
            rootpath = rootpath.substring(0, rootpath.indexOf("bin")-1);
        String filepath = rootpath + "/pic/";
        File file = new File(filepath);
        if(! file.exists()) {
            file.mkdirs();
        }
        file = new File(file, filename);
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return file.getAbsolutePath();
    }
}
