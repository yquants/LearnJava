package com.winson.tutorial.distributedcomputing.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 16-6-26.
 */
public interface IService extends Remote {

    String service(String content) throws RemoteException;
}
