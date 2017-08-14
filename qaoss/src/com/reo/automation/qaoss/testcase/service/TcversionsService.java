/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.service;

import com.reo.automation.qaoss.testcase.entity.Tcversions;
import com.reo.automation.qaoss.base.service.BasedTestLinkService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.redkale.boot.Application;
import org.redkale.source.FilterNode;

/**
 *
 * @author jerry.ouyang
 */
public class TcversionsService extends BasedTestLinkService {

    @Resource
    private TestLinkUserService userService;

    /**
     * 查询表tcversions中所有的记录
     * @return 
     */
    public List<Tcversions> queryTcversionsList() {
        List<Tcversions> cases = source.queryList(Tcversions.class, FilterNode.create("version", 1));
        Map<Integer, String> map = userService.queryTestLinkUserMap();
        cases.forEach(e -> ((Tcversions) e).setAuthorname(map.get(e.getAuthor_id())));
        return cases;
    }

    public Map<String, Integer> countTcversionsByUser() {
        //排除了automator, admin， jerry.ouyang, lihong.liu和julia.wang帐号的统计
        Integer[] excludeIds = {1, 3, 4, 8, 12, 27};
        Map<String, Integer> result = new HashMap<String, Integer>();
        List<Tcversions> cases = source.queryList(Tcversions.class, FilterNode.create("version", 1));
        Map<Integer, String> map = userService.queryTestLinkUserMap();
        for (Tcversions tc : cases) {
            if(Arrays.asList(excludeIds).contains(tc.getAuthor_id()))
                continue;
            String username = map.get(tc.getAuthor_id());
            
            if (!result.containsKey(username)) {
                result.put(username, 1);
            } else {
                int value = result.get(username) + 1;
                result.put(username, value);
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        TcversionsService service = Application.singleton(TcversionsService.class);

//        service.queryTcversionsList().forEach(e -> System.out.println(e.getAuthorname() + "---" + e.getCreation_ts()));
        
        service.countTcversionsByUser().forEach((k, v) -> System.out.println(k + "---" + v));
        
    }
}
