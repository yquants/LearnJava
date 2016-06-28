package com.winson.tutorial.distributedcomputing.rmi;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


/**
 * Created by Administrator on 16-6-26.
 */
public class RmiServer {

    private static final Logger logger = Logger.getLogger(RmiServer.class);

    private final String RMI_URL_PREFIX = "rmi://localhost";

    public void bind(String serviceName, int port) {
        try {
            IService service02 = new ServiceImpl(serviceName);

            LocateRegistry.createRegistry(port);

            Naming.bind(getRmiUrl(serviceName, port), service02);

        } catch (RemoteException re) {
            logger.fatal(re);
        } catch (AlreadyBoundException abe) {
            logger.fatal(abe);
        } catch (MalformedURLException murle) {
            logger.fatal(murle);
        }
    }

    private String getRmiUrl(String serviceName, int port) {

        assert port > 1000 && serviceName != null && serviceName.length() > 0;

        return new StringBuilder(RMI_URL_PREFIX).append(":").append(port).append("/").append(serviceName).toString();
    }
}
