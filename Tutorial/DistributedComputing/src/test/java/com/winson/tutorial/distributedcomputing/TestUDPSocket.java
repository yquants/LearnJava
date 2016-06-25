package com.winson.tutorial.distributedcomputing;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 16-6-25.
 */

public class TestUDPSocket {

    private static final Logger logger = Logger.getLogger(TestUDPSocket.class);
    /**
     * TODO List:
     *
     * 1. Run server up
     *
     */
    @Test
    public void testSocket(){

        Thread serverThread, clientThread;

        serverThread = new Thread(){
            public void run(){
                UDPServer server = new UDPServer(2000);
                Assert.assertNotNull(server);
                server.start();
            }
        };

        clientThread = new Thread(){
            public void run(){
                UDPServer client = new UDPServer(2020);
                Assert.assertNotNull(client);
                client.send("abc", "127.0.0.1", 2000);
                client.start();
            }
        };

        serverThread.setDaemon(true);
        serverThread.start();

        clientThread.setDaemon(true);
        clientThread.start();

        logger.info("Start to close");

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException ie){
            Assert.assertNotNull(ie);
        }

        logger.info("Exit");
    }
}
