package provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.ISubjectDiscussion;
import client.model.IClient;

public interface ISubjectProvider extends Remote{
	
	/**
	 * Subscribe a client to this subject
	 * @param client
	 * @throws RemoteException
	 */
	public boolean subscribe(IClient client) throws RemoteException;
	
	/**
	 * Unsubscribe a client to this subject
	 * @param client
	 * @return
	 * @throws RemoteException
	 */
	public boolean unsubscribe(IClient client) throws RemoteException;

	/**
	 * Get the subject of ISubjectProvider
	 * @return
	 * @throws RemoteException
	 */
	public ISubjectDiscussion getSubject() throws RemoteException;
	
	/**
	 * Broadcast the message msg to all client registered in this subject
	 * @param msg
	 * @return true when the broadcast is complete 
	 * @throws RemoteException
	 */
	public boolean broadcast(String msg) throws RemoteException;

	/**
	 * The server wants to remove this provider. 
	 * @return true when the provider is ready to be destroyed, 
	 * 	\-> false if nothing should be deleted here ! 
	 * @throws RemoteException
	 */
	public boolean prepareDeletion() throws RemoteException; 

}