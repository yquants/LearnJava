package com.winson.tutorial.drools;

import com.winson.tutorial.drools.bean.PointDomain;
import org.apache.log4j.Logger;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 16-7-15.
 */
public class SpringDrools {

    private final static Logger logger = Logger.getLogger(SpringDrools.class);

    public void runRuleEngine() {

        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        final PointDomain pDomain = (PointDomain) context.getBean("pointDomain");

        StatefulKnowledgeSession ksession = (StatefulKnowledgeSession) context.getBean("ksession");

        ksession.insert(pDomain);

        ksession.fireAllRules();

        ksession.dispose();

        logger.info("用户" + pDomain.getUserName() + "执行规则引擎后的积分：" + pDomain.getPoint());


    }
}
