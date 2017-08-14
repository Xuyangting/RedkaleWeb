/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name="marketpostionprice")
public class MarketPostionPrice extends BaseEntity{
    @Id
    @GeneratedValue
    private long marketpostionpriceid;
    
    private String ticker;
    private long timestamp;
    private float marketprice;
    private float postionprice;

    public long getMarketpostionpriceid() {
        return marketpostionpriceid;
    }

    public void setMarketpostionpriceid(long marketpostionpriceid) {
        this.marketpostionpriceid = marketpostionpriceid;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(float marketprice) {
        this.marketprice = marketprice;
    }

    public float getPostionprice() {
        return postionprice;
    }

    public void setPostionprice(float postionprice) {
        this.postionprice = postionprice;
    }
    
    
}
