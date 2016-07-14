package com.winson.tutorial.drools.service;

import com.winson.tutorial.drools.bean.PointDomain;

/**
 * Created by Administrator on 16-7-14.
 */
public interface PointRuleEngine {

    /**
     * 初始化规则引擎
     */
    void initEngine();

    /**
     * 刷新规则引擎中的规则
     */
    void refreshEngineRule();

    /**
     * 执行规则引擎
     *
     * @param pointDomain 积分Fact
     */
    void executeRuleEngine(final PointDomain pointDomain);
}
