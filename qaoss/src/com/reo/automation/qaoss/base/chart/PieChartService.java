/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.chart;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author jerry.ouyang
 */
public class PieChartService extends FreeChartService {

    private DefaultPieDataset dataSet;

    public PieChartService(String title) {
        dataSet = new DefaultPieDataset();
        chart = ChartFactory.createPieChart(title, dataSet, true, true, false);
    }

    public void setData(String name, Map<String, Number> map) {
        for (Map.Entry<String, Number> entry : map.entrySet()) {
            dataSet.setValue(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void setFont() {
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("宋体", Font.BOLD, 16));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setNoDataMessage("No data available");
        plot.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        plot.setLabelGenerator(
                new StandardPieSectionLabelGenerator(("{0} = {1}"),
                        NumberFormat.getNumberInstance(),
                        new DecimalFormat("0.00%"))
        );
    }
    
    public static void main(String[] args) {
        Map map1 = new LinkedHashMap<String, Number> ();
        map1.put("04/14/2016", 433.44f);
        map1.put("04/15/2016", 888l);
        map1.put("04/16/2016", 555);
        
        PieChartService cs = new PieChartService("测试");
        cs.setData("100毫秒", map1);
        System.out.println(cs.getChartAsBase64());
    }

}
