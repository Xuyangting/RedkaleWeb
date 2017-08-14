/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name = "nodes_hierarchy")
public class NodesHierarchy extends BaseEntity {
    @Id
    private int id;
    
    private String name;
    
    private int parent_id;
    
    private int node_type_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getNode_type_id() {
        return node_type_id;
    }

    public void setNode_type_id(int node_type_id) {
        this.node_type_id = node_type_id;
    }
}
