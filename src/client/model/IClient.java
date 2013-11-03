package client.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.IServerForum;
import server.ISubjectDiscussion;

public interface IClient  extends Remote {
	
	/**
	 * Get the login of the client
	 * @return
	 * @throws RemoteException
	 */
	public String getLogin() throws RemoteException;
	
	/**
	 * Set the login of the client
	 * @param login
	 * @throws RemoteException
	 */
	public void setLogin(String login) throws RemoteException;
	
	/**
	 * Send a request to subscribe the client
	 * @param subject
	 * @return true if subscribed, false otherwise
	 * @throws RemoteException
	 */
	public boolean pleaseSubscribe( ISubjectDiscussion subject ) throws RemoteException;
	
	/**
	 * Send a request to unsubscribe the client
	 * @param subject
	 * @return true if unsubscribed, false otherwise
	 * @throws RemoteException
	 */
	public boolean pleaseUnsubscribe( ISubjectDiscussion subject ) throws RemoteException;
	
	/**
	 * Ask the server to send the message
	 * @param subject
	 * @param msg
	 * @throws RemoteException
	 */
	public void pleaseSendMessage( ISubjectDiscussion subject, String msg ) throws RemoteException;
	
	/**
	 * Display the message on Client's view
	 * @param subject
	 * @param msg
	 * @throws RemoteException
	 */
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException;
	
	/**
	 * Know if two clients are equals
	 * @param client
	 * @return true if this client is equals to client
	 * @throws RemoteException
	 */
	public boolean equals( IClient client ) throws RemoteException;

	/**
	 * Know if two clients are equals
	 * @param IServerForum server the host of the subject to remove 
	 * @param String title the title of the subject to remove 
	 * @return true if this subject is 
	 * @throws RemoteException
	 */
	public boolean pleaseRemoveSubject( IServerForum server, String title ) throws RemoteException; 
	
}
