/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.filter;

import java.util.List;
import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import org.redkale.source.FilterExpress;

/**
 *
 * @author jerry.ouyang
 */
public class NodeassociationBean implements FilterBean{
    private long source_node_id;
    
    @FilterColumn(name="source_node_id", express=FilterExpress.IN)
    private List source_node_ids;
    
    private String association_type = "IssueComponent";

    public long getSource_node_id() {
        return source_node_id;
    }

    public void setSource_node_id(long source_node_id) {
        this.source_node_id = source_node_id;
    }

    public String getAssociation_type() {
        return association_type;
    }

    public void setAssociation_type(String association_type) {
        this.association_type = association_type;
    }

    public List getSource_node_ids() {
        return source_node_ids;
    }

    public void setSource_node_ids(List source_node_ids) {
        this.source_node_ids = source_node_ids;
    }

}
