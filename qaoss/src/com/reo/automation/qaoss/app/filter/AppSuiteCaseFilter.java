/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.filter;

import org.redkale.source.FilterBean;

/**
 *
 * @author timen.xu
 */
public class AppSuiteCaseFilter  implements FilterBean{
    private int suite_id;

    public int getSuite_id() {
        return suite_id;
    }

    public void setSuite_id(int suite_id) {
        this.suite_id = suite_id;
    }
    
    
}
