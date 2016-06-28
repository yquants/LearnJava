package com.winson.tutorial.distributedcomputing.rmi;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 16-6-26.
 */
public class TestRmiClient {

    private static final Logger logger = Logger.getLogger(TestRmiClient.class);

    @Test
    public void testRmiCallInClient(){
        final String RMI_URI = "rmi://localhost:8888/service02";

        RmiClient client = new RmiClient();
        Assert.assertNotNull(client);

        client.rmiCall(RMI_URI);
        Assert.assertEquals(1,1);
    }
}
