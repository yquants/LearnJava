package com.winson.tutorial.drools;

import org.junit.Test;

/**
 * Created by Administrator on 16-7-15.
 */
public class SpringDroolsTester {

    @Test
    public void should_fire(){
        new SpringDrools().runRuleEngine();
    }
}
