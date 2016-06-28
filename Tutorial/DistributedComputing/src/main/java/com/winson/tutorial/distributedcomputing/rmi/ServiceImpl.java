package com.winson.tutorial.distributedcomputing.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Administrator on 16-6-26.
 */
public class ServiceImpl extends UnicastRemoteObject implements IService {

    private String name;

    public ServiceImpl(String name) throws RemoteException{
        this.name = name;
    }

    public String service(String content) throws RemoteException{
        return "Server >> " + content;
    }
}
