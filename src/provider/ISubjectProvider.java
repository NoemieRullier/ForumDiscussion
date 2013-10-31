package provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.ISubjectDiscussion;
import client.model.IClient;

public interface ISubjectProvider extends Remote{
	
	/**
	 * Subscribe a client to his subject
	 * @param client
	 * @throws RemoteException
	 */
	public boolean subscribe(IClient client) throws RemoteException;
	
	/**
	 * Unsubscribe a client to his subject
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
	 * Broadcast the message msg to all client subscibe to his subject
	 * @param msg
	 * @throws RemoteException
	 */
	public void broadcast(String msg) throws RemoteException;

}