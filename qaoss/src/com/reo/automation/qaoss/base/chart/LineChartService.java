/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author jerry.ouyang
 */
public class LineChartService extends FreeChartService {
    private DefaultCategoryDataset dataSet;

    public LineChartService(String title, String xAisxName, String yAisxName) {
        dataSet = new DefaultCategoryDataset();
        chart = ChartFactory.createLineChart(title, xAisxName, yAisxName, dataSet, PlotOrientation.VERTICAL, true, true, false);
    }

    public void setData(String name, Map<String, Number> map) {
        for (Map.Entry<String, Number> entry : map.entrySet()) {
            dataSet.setValue(entry.getValue(), name, entry.getKey());
        }
    }

    @Override
    public void setFont() {
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        categoryplot.setBackgroundPaint(Color.LIGHT_GRAY); // 设置绘图区背景色  
        categoryplot.setRangeGridlinePaint(Color.WHITE); // 设置水平方向背景线颜色  
        categoryplot.setRangeGridlinesVisible(true);// 设置是否显示水平方向背景线,默认值为true  
        categoryplot.setDomainGridlinePaint(Color.WHITE); // 设置垂直方向背景线颜色  
        categoryplot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false 
        
        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
        CategoryAxis domainAxis = categoryplot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
        domainAxis.setCategoryLabelPositions (CategoryLabelPositions.UP_45);
        numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
        numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) categoryplot.getRenderer();  
        BasicStroke realLine = new BasicStroke(1.8f);
        for (int i = 0; i < dataSet.getRowCount(); i++) {
            renderer.setSeriesStroke(i, realLine);
        }
        
        categoryplot.setNoDataMessage("无对应的数据，请重新查询。");  
        categoryplot.setNoDataMessageFont(new Font("sans-serif", Font.PLAIN, 11)); //字体的大小  
        categoryplot.setNoDataMessagePaint(Color.RED); //字体颜色  
    }

    public static void main(String[] args) {
        Map map1 = new LinkedHashMap<String, Number> ();
        map1.put("04/14/2016", 433.44f);
        map1.put("04/15/2016", 888l);
        map1.put("04/16/2016", 555);
        
        Map map2 = new LinkedHashMap<String, Number> ();
        map2.put("04/14/2016", 433.44f);
        map2.put("04/15/2016", 488l);
        map2.put("04/16/2016", 855);
        
        LineChartService cs = new LineChartService("测试", "日期", "百分比");
        cs.setData("100毫秒", map1);
        cs.setData("200毫秒", map2);
//        System.out.println(cs.getChartAsBase64());
        System.out.println(cs.saveChartAsJPEG(System.currentTimeMillis() + ".jpeg", 650, 400));
    }

}
