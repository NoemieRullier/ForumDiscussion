package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.model.IClient;

public interface ISubjectDiscussion extends Remote {

	/**
	 * Register client c to this subject discussion
	 * @param c
	 * @return true if client was register, false otherwise
	 * @throws RemoteException
	 */
	public boolean registration (IClient c) throws RemoteException;

	/**
	 * Broadcast the message msg to all client register
	 * @param msg
	 * @throws RemoteException
	 */
	public void broadcast(String msg) throws RemoteException;

	/**
	 * Get the title of this subject discussion
	 * @return title
	 * @throws RemoteException
	 */
	public String getTitle() throws RemoteException;

	/**
	 * Unsubscribe the client c
	 * @param c
	 * @return true if the client was unsubscribe, false otherwise
	 * @throws RemoteException
	 */
	public boolean unsubscribe(IClient c) throws RemoteException;


}
