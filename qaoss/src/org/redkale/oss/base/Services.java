/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.redkale.oss.base;

import java.lang.annotation.*;

/**
 *
 * @author zhangjx
 */
public class Services {

    private Services() {
        throw new Error();
    }

    //----------------------------------------------操作ID-----------------------------------------------------------
    @ActionName("查询")
    public static final int ACTION_QUERY = 1001;

    @ActionName("新增")
    public static final int ACTION_CREATE = 1002;

    //修改、预览和同步文件
    @ActionName("修改")
    public static final int ACTION_UPDATE = 1003;

    @ActionName("删除")
    public static final int ACTION_DELETE = 1004;

    @ActionName("登录")
    public static final int ACTION_LOGIN = 1010;
    
    @ActionName("同步")
    public static final int ACTION_SYNC = 1020;

    //----------------------------------------------模块ID  注意: @ModuleName名称尽量控制在5个汉字以内-----------------------------------------------------------
    @ModuleName("用户管理")
    public static final int MODULE_USER = 101;

    @ModuleName("权限管理")
    public static final int MODULE_AUTH = 102;

    @ModuleName("角色管理")
    public static final int MODULE_ROLE = 103;
    
    //用例统计
    @ModuleName("用例统计")
    public static final int TESTCASE_STAT = 301;
    
    //缺陷统计
    @ModuleName("缺陷统计")
    public static final int BUG_STAT = 401;
    
    @ModuleName("缺陷原因")
    public static final int BUG_REASON = 402;
    
    @ModuleName("项目模块")
    public static final int BUG_MODULE = 403;
    
    @ModuleName("项目时间")
    public static final int BUG_PROJECTTIME = 404;
    
    //告警模块
    @ModuleName("告警统计")
    public static final int ONLINE_STATIC = 501;
    
    @ModuleName("告警趋势")
    public static final int ONLINE_ALERTRECORDTREND = 507;
    
    @ModuleName("告警模块")
    public static final int ONLINE_MODULE = 502;
    
    @ModuleName("告警类型")
    public static final int ONLINE_ALERTTYPE = 503;

    @ModuleName("监控任务")
    public static final int ONLINE_BUILDJOB = 504;
    
    @ModuleName("构建任务")
    public static final int ONLINE_JENKINSBUILD = 505;
    
    @ModuleName("请求时间")
    public static final int ONLINE_REQUESTTIME = 506;
    
    @ModuleName("行情持仓")
    public static final int ONLINE_MARKETPOSTIONPRICE = 507;
	
    //调度控制
    @ModuleName("间隔任务")
    public static final int SCHEDULER_INTERVAL = 601;
    
    @ModuleName("定时任务")
    public static final int SCHEDULER_CRONTAB = 602;
    
    @ModuleName("邮箱配置")
    public static final int SCHEDULER_EMAIL = 603;
    
    @ModuleName("微信配置")
    public static final int SCHEDULER_WEIXIN = 604;
    
    @ModuleName("测试账号")
    public static final int SCHEDULER_ACCOUNT = 605;
    
    //接口测试
    @ModuleName("环境管理")
    public static final int INTERFACE_HOST = 701;
    
    @ModuleName("模块管理")
    public static final int INTERFACE_MODULE = 702;
    
    @ModuleName("接口管理")
    public static final int INTERFACE_API = 703;
    
    @ModuleName("用例管理")
    public static final int INTERFACE_TESTCASE = 704;
    
    @ModuleName("集合管理")
    public static final int INTERFACE_TESTSUITE = 705;
    
    @ModuleName("用例管理")
    public static final int INTERFACE_SUITECASE = 706;
    
    @ModuleName("任务管理")
    public static final int INTERFACE_TESTJOB = 707;
    
    @ModuleName("执行任务")
    public static final int INTERFACE_EXECUTE = 708;
    
    @ModuleName("接口统计")
    public static final int INTERFACE_STATS = 709;
    
    //APP测试
    @ModuleName("应用管理")
    public static final int APP_APPLICATION = 801;
    
    @ModuleName("模块管理")
    public static final int APP_APPLICATION_MODULE = 802;
    
    @ModuleName("页面元素")
    public static final int APP_ELEMENT = 803;
    
    @ModuleName("用例管理")
    public static final int APP_TESTCASE = 804;
    
    @ModuleName("集合管理")
    public static final int APP_TESTSUITE = 805;
    
    @ModuleName("任务管理")
    public static final int APP_TESTJOB = 806;
    
    @ModuleName("执行任务")
    public static final int APP_EXECUTE_APPLICATION = 807;
    
    @ModuleName("手机管理")
    public static final int APP_MOBILE = 808;
    
    @ModuleName("测试方法")
    public static final int APP_TESTMETHOD = 809;
    
    //竞品对比
    @ModuleName("股票管理")
    public static final int COMPARE_STOCK = 901;
    
    @ModuleName("执行对比")
    public static final int COMPARE_EXECUTE = 902;
    
    @ModuleName("有鱼自测")
    public static final int COMPARE_MYSELF = 903;
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * 将模块ID与操作ID合并成一个int值
     *
     * @param moduleid
     * @param actionid
     * @return
     */
    public static int optionid(int moduleid, int actionid) {
        return moduleid * 10000 + actionid;
    }

    @Target(value = {ElementType.FIELD})
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface ModuleName {

        String value();
    }

    @Target(value = {ElementType.FIELD})
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface ActionName {

        String value();
    }

    @Target(value = {ElementType.FIELD})
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface TypeName {

        String value();
    }
}
