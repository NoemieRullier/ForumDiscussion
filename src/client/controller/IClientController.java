package client.controller;

import java.rmi.RemoteException;

import server.ISubjectDiscussion;
import client.model.IClient;

public interface IClientController{
	
	public void pleaseSubscribe( ISubjectDiscussion subject, IClient client ) throws RemoteException; 
	
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException;

	public void pleaseUnsubscribe(ISubjectDiscussion subject, IClient client) throws RemoteException;

	public void pleaseSendMessage(ISubjectDiscussion subject, String msg, IClient client) throws RemoteException;

}
