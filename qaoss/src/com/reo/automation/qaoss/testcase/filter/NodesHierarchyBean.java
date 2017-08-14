/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.filter;

import org.redkale.source.FilterBean;
import org.redkale.source.FilterColumn;
import org.redkale.source.FilterExpress;

/**
 *
 * @author jerry.ouyang
 */
public class NodesHierarchyBean implements FilterBean{
    private int id;
    
    @FilterColumn(name = "parent_id", express = FilterExpress.IN)
    private int[] parent_ids;
        
    private int node_type_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(int[] parent_ids) {
        this.parent_ids = parent_ids;
    }

    public int getNode_type_id() {
        return node_type_id;
    }

    public void setNode_type_id(int node_type_id) {
        this.node_type_id = node_type_id;
    }
}
