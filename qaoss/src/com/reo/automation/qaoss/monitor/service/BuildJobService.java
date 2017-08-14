package com.reo.automation.qaoss.monitor.service;


import com.reo.automation.qaoss.monitor.entity.BuildJob;
import com.reo.automation.qaoss.base.entity.ComboboxValue;
import com.reo.automation.qaoss.monitor.filter.BuildJobBean;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedMonitorService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.redkale.source.FilterNode;
import org.redkale.source.Flipper;
import org.redkale.util.Sheet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerry.ouyang
 */
public class BuildJobService extends BasedMonitorService{
    
    public Sheet querySheet(Flipper flipper, BuildJobBean bean) {
        return source.querySheet(BuildJob.class, flipper, bean);
    }
    
    public void create(BuildJob job) {
        source.insert(job);
    }
    
    public void update(BuildJob job) {
        source.update(job);
    }
    
    public BuildJob findBuildJob(long buildjobid) {
        return source.find(BuildJob.class, buildjobid);
    }
    
    public BuildJob findBuildJob(String name) {
        return source.find(BuildJob.class, FilterNode.create("name", name));
    }
    
    public List<Long> queryJobIds() {
        return source.queryColumnList("buildjobid", BuildJob.class, new BasedFilterBean());
    }
    
    public List<ComboboxValue> queryList() {
        List<ComboboxValue> list = new ArrayList<ComboboxValue>();
        
        source.queryList(BuildJob.class, new BasedFilterBean()).forEach(e -> {
            ComboboxValue cv = new ComboboxValue(e.getBuildjobid(), e.getName());
            list.add(cv);
        });
        return list;
    }
    
    public Map<Long, String> queryAll2() {
        Map<Long, String> map = new HashMap<Long, String> ();
        source.queryList(BuildJob.class, new BasedFilterBean()).forEach( e -> map.put(e.getBuildjobid(), e.getName()));
        return map;
    }
}
