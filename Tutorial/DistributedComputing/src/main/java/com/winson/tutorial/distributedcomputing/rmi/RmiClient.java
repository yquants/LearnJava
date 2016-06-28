package com.winson.tutorial.distributedcomputing.rmi;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 16-6-26.
 */
public class RmiClient {

    private static final Logger logger = Logger.getLogger(RmiClient.class);

    public void rmiCall(String rmiUrl) {
        try {
            IService service = (IService) Naming.lookup(rmiUrl);
            assert service != null;

            Class stubClass = service.getClass();
            logger.info(service + " is an instance of " + stubClass);

            Class[] interfaces = stubClass.getInterfaces();

            for (Class clazz : interfaces) {
                logger.info(service + " implemented interface " + clazz.getName());
            }

            logger.debug("Start to call the method via RMI");

            logger.info(service.service("hello world"));

        } catch (RemoteException re) {
            logger.fatal(re);
        } catch (NotBoundException nbe) {
            logger.fatal(nbe);
        } catch (MalformedURLException murle) {
            logger.fatal(murle);
        }
    }
}
