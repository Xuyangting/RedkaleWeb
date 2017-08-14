/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reo.automation.qaoss.base.service;

import javax.annotation.Resource;
import org.redkale.oss.sys.BaseService;
import org.redkale.source.DataSource;

/**
 *
 * @author timen.xu
 */
public class BasedTestService extends BaseService{
    @Resource(name = "test")
    protected DataSource source;
}
