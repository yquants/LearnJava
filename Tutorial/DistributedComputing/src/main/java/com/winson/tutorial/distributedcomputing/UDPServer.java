package com.winson.tutorial.distributedcomputing;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.*;

/**
 * Created by Administrator on 16-6-25.
 */
public class UDPServer {

    private static final Logger logger = Logger.getLogger(UDPServer.class);

    private byte[] buffer = new byte[1024];

    private int port;

    private DatagramSocket server;
    private DatagramPacket dp;
    private SocketAddress client;

    private boolean isStopRequested = false;

    public UDPServer(int port){
        this.port = port;

        try{
            server = new DatagramSocket(port);
        }catch(SocketException se){
            logger.fatal(se);
        }
    }

    public void start(){
        assert port > 1024?true:false;

        logger.info("Listening on port " + this.port);
        while(!isStopRequested){
            receive();
            response();
        }

        logger.info("Stopping Server!");
        server.close();
    }

    public synchronized void stop(){
          this.isStopRequested = true;
    }

    public void send(String msg, String host, int port){
        InetAddress ia = null;
        try{
             ia = InetAddress.getByName(host);
        }catch (UnknownHostException uhe){
            logger.fatal(uhe);
        }
        DatagramPacket responseDp = new DatagramPacket(msg.getBytes(), msg.length(), ia, port);

        try{
            server.send(responseDp);
        }catch (IOException ioe){
            logger.fatal(ioe);
        }
    }

    private void receive(){
        dp = new DatagramPacket(buffer, buffer.length);
        try{
            server.receive(dp);
            logger.info("Message Received:" + new String(buffer));
        }catch (IOException ioe){
            logger.fatal(ioe);
        }

        client = dp.getSocketAddress();
    }

    private void response(){
        DatagramPacket responseDp = new DatagramPacket("received".getBytes(), "received".length());
        responseDp.setSocketAddress(client);
        try{
            server.send(responseDp);
        }catch (IOException ioe){
            logger.fatal(ioe);
        }

        client = dp.getSocketAddress();
    }


}
