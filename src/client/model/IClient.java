package client.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

import provider.ISubjectProvider;
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
	 * @return true if is subscribe, false otherwise
	 * @throws RemoteException
	 */
	public boolean pleaseSubscribe( ISubjectProvider subject ) throws RemoteException;
	
	/**
	 * Send a request to unsubscribe the client
	 * @param subject
	 * @return true if is subscribe, false otherwise
	 * @throws RemoteException
	 */
	public boolean pleaseUnsubscribe( ISubjectProvider subjectProvider ) throws RemoteException;
	
	/**
	 * Ask the server to send the message
	 * @param subject
	 * @param msg
	 * @throws RemoteException
	 */
	public void pleaseSendMessage( ISubjectProvider subjectProvider, String msg ) throws RemoteException;
	
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
	 * Refresh subject list 
	 * @throws RemoteException
	 */
	public void refreshSubjects() throws RemoteException;
	
}
