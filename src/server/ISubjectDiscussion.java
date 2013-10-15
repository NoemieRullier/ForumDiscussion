package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.model.IClient;

public interface ISubjectDiscussion extends Remote {

	/*
	 * Register DisplayClient to this subject discussion
	 */
	public boolean registration (IClient c) throws RemoteException;
	
	
	/*
	 * Broadcast the message msg to all DisplayClient register
	 */
	public void broadcast(String msg) throws RemoteException;

	public String getTitle() throws RemoteException;

	public boolean unsubscribe(IClient c) throws RemoteException;
	
	
}
