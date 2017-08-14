/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.testcase.service;

import com.reo.automation.qaoss.testcase.entity.TestLinkUser;
import com.reo.automation.qaoss.base.filter.BasedFilterBean;
import com.reo.automation.qaoss.base.service.BasedTestLinkService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.redkale.source.FilterNode;

/**
 *
 * @author jerry.ouyang
 */
public class TestLinkUserService extends BasedTestLinkService {

    /**
     * 查询testlink库中表users中的所有记录
     * @return 
     */
    public List<TestLinkUser> queryTestLinkUserList() {
        List<TestLinkUser> users = source.queryList(TestLinkUser.class, new BasedFilterBean());
        users.forEach(e -> ((TestLinkUser) e).setUsername(e.getLast() + e.getFirst()));
        return users;
    }

    /**
     * 根据userid查询user记录信息
     * @param id
     * @return 
     */
    public String findUsernameById(int id) {
        TestLinkUser user = source.find(TestLinkUser.class, FilterNode.create("id", id));
        user.setUsername(user.getLast() + user.getFirst());
        return user.getUsername();
    }
    
    /**
     * 返回testlink库中表users所有的id与name信息
     * @return 
     */
    public Map<Integer, String> queryTestLinkUserMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<TestLinkUser> users = queryTestLinkUserList();
        users.forEach(e -> map.put(e.getId(), e.getUsername()));
        return map;
    }
}
