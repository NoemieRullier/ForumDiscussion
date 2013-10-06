package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.IDisplayClient;

public interface ISubjectDiscussion extends Remote {

	/*
	 * Register DisplayClient to this subject discussion
	 */
	public void registration (IDisplayClient c) throws RemoteException;
	
	/*
	 * ??? DisplayClient to this  
	 */
	// TODO Change name in english this subject discussion
	public void desinscription (IDisplayClient c) throws RemoteException;
	
	
	/*
	 * Broadcast the message msg to all DisplayClient register
	 */
	public void broadcast(String msg) throws RemoteException;
	
	
}
