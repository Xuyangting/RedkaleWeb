/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.monitor.service;

import com.reo.automation.qaoss.base.entity.ComboboxValue1;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedMonitorService;
import com.reo.automation.qaoss.monitor.entity.BuildRecordDetail;
import com.reo.automation.qaoss.monitor.entity.RequestName;
import com.reo.automation.qaoss.monitor.filter.BuildRecordBean;
import com.reo.automation.qaoss.monitor.filter.BuildRecordDetailBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.source.FilterExpress;
import org.redkale.source.FilterNode;

/**
 *
 * @author jerry.ouyang
 */
public class BuildRecordDetailService extends BasedMonitorService{
    @Resource
    private BuildRecordService brService;
    
    public List<ComboboxValue1> queryAllRequestName() {
        List<ComboboxValue1> list = new ArrayList<ComboboxValue1>();
//        HashSet<String> set = new HashSet<String>(source.queryColumnList("requestname", BuildRecordDetail.class, new BasedFilterBean()));
//        List<String> temp = set.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
//        temp.forEach( e -> {
//            ComboboxValue1 cv = new ComboboxValue1(e, e);
//            list.add(cv);
//        });
        
        List<String> temp = source.queryColumnList("requestname", RequestName.class, new BasedFilterBean());
        temp.forEach( e -> {
            ComboboxValue1 cv = new ComboboxValue1(e, e);
            list.add(cv);
        });
        
        return list;
    }
    
    public List<String> queryRequestNames(BuildRecordDetailBean bean) {
        HashSet<String> set = new HashSet<String>(source.queryColumnList("requestname", BuildRecordDetail.class, bean));
        return set.stream().collect(Collectors.toList());
    }
    
    public List<BuildRecordDetail> queryList(BuildRecordDetailBean bean) {
        return source.queryList(BuildRecordDetail.class, bean);
    }
    
    /**
     * 根据request name查询出 Map<requestname, buildjobid>
     * 但是如果存在request name 相同但build job id不同的情况，则后者会覆盖前者
     * @param bean
     * @return 
     */
    public Map<String, Long> queryRnameJobIdMap(BuildRecordDetailBean bean) {
        Map<String, Long> map = new HashMap<String, Long>();
        queryList(bean).forEach( e -> map.put(e.getRequestname(), e.getBuildjobid()));
        return map;
    }
    
    public void updateBuildTimeStamp(long buildjobid, int buildno, long timestamp) {
        source.updateColumn(BuildRecordDetail.class, "buildtimestamp", timestamp, FilterNode.create("buildjobid", FilterExpress.EQUAL, buildjobid).and("buildno", buildno));
    }
    
    public static void main(String[] args) throws Exception {
        BuildRecordDetailService brdService = Application.singleton(BuildRecordDetailService.class);
        BuildRecordService brService = Application.singleton(BuildRecordService.class);
        
        BuildRecordBean bean = new BuildRecordBean();
        bean.setBuildjobid(10009001);
        brService.queryAllList(bean).forEach( e -> {
//            brdService.updateBuildTimeStamp(e.getBuildjobid(), e.getBuildno(), e.getBuildtimestamp());
        });
    }
}
