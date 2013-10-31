package client.controller;

import java.rmi.RemoteException;

import provider.ISubjectProvider;
import server.ISubjectDiscussion;
import client.model.IClient;

public interface IClientController{

	/**
	 * Send a request to server to subscribe client to the subject
	 * @param subject
	 * @param client
	 * @throws RemoteException
	 */
	public void pleaseSubscribe( ISubjectProvider subject, IClient client ) throws RemoteException; 

	/**
	 * Display the message msg on the subject
	 * @param subject
	 * @param msg
	 * @throws RemoteException
	 */
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException;

	/**
	 * Send a request to server to unsubscribe the client client to the subject subject
	 * @param subject
	 * @param client
	 * @throws RemoteException
	 */
	public void pleaseUnsubscribe(ISubjectProvider subjectProvider, IClient client) throws RemoteException;

	/**
	 * Send a request to client to send the message msg on the subject subject
	 * @param subject
	 * @param msg
	 * @param client
	 * @throws RemoteException
	 */
	public void pleaseSendMessage(ISubjectProvider subject, String msg, IClient client) throws RemoteException;

	/**
	 * Send a request to server to know if the login is available
	 * @param login
	 * @return true if login available, false otherwise
	 * @throws RemoteException
	 */
	public boolean verifyAvailableLogin(String login) throws RemoteException;

	/**
	 * Send a request to server to remove a login
	 * @param login
	 * @throws RemoteException
	 */
	public void pleaseRemoveLogin(String login) throws RemoteException;

	/**
	 * Send a request to server to know if the title is available
	 * @param title
	 * @throws RemoteException
	 */
	public boolean verifyAvailableTitle(String title) throws RemoteException;
	
	/**
	 * Create new Subject
	 * @param title
	 * @throws RemoteException
	 */
	public void addSubjectDiscussion(String title) throws RemoteException;
	
	/**
	 * Recuperate all new subjects
	 * @throws RemoteException
	 */
	public void recuperateSubjects() throws RemoteException;
}
