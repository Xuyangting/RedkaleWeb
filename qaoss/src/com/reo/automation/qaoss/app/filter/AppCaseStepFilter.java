/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.app.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import static org.redkale.source.FilterExpress.LIKE;
import org.redkale.source.FilterGroup;

/**
 *
 * @author timen.xu
 */
public class AppCaseStepFilter  implements FilterBean{
    private int case_id;

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }
    
    @FilterColumn(express=LIKE)
    @FilterGroup("[AND]")
    private String step_type;

    public String getStep_type() {
        return step_type;
    }

    public void setStep_type(String step_type) {
        this.step_type = step_type;
    }
}
