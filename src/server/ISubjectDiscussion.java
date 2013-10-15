package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.model.IClient;

public interface ISubjectDiscussion extends Remote {

	/*
	 * Register client to this subject discussion
	 */
	public boolean registration (IClient c) throws RemoteException;
	
	
	/*
	 * Broadcast the message msg to all client register
	 */
	public void broadcast(String msg) throws RemoteException;

	/*
	 * Return the title of the subject discussion
	 */
	public String getTitle() throws RemoteException;

	public boolean unsubscribe(IClient c) throws RemoteException;
	
	
}
