package com.winson.tutorial.distributedcomputing.rmi;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 16-6-26.
 */
public class TestRmiServer {

    private static final Logger logger = Logger.getLogger(TestRmiServer.class);

    @Test
    public void testServerBind(){
        int port = 8888;

        String serviceName = "service02";

        new RmiServer().bind(serviceName, port);
        Assert.assertEquals(1,1);

        try {
            TimeUnit.SECONDS.sleep(300);
        } catch (InterruptedException e) {
            logger.fatal(e);
            Assert.assertEquals(1,2);
        }

        logger.debug("Server Ended!");
    }
}
