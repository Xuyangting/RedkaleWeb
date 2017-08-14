/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.jira.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redkale.oss.base.BaseEntity;

/**
 *
 * @author jerry.ouyang
 */
@Entity
@Table(name = "nodeassociation")
public class Nodeassociation extends BaseEntity {
    @Id
    private long source_node_id;
    private long sink_node_id;

    public long getSource_node_id() {
        return source_node_id;
    }

    public void setSource_node_id(long source_node_id) {
        this.source_node_id = source_node_id;
    }

    public long getSink_node_id() {
        return sink_node_id;
    }

    public void setSink_node_id(long sink_node_id) {
        this.sink_node_id = sink_node_id;
    }
    
    
}
