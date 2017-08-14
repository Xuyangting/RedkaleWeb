/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.interfaces.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;
import org.redkale.source.FilterGroup;

/**
 *
 * @author timen.xu
 */
public class WechatFilter implements FilterBean {
    @FilterColumn(express=LIKE)
    @FilterGroup("[OR]")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
