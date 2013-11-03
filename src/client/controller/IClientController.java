package client.controller;

import java.rmi.RemoteException;

import server.IServerForum;
import server.ISubjectDiscussion;
import client.model.IClient;

public interface IClientController{

	/**
	 * Send a request to server to subscribe client to the subject
	 * @param subject
	 * @param client
	 * @throws RemoteException
	 */
	public void pleaseSubscribe( ISubjectDiscussion subject, IClient client ) throws RemoteException; 

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
	public void pleaseUnsubscribe(ISubjectDiscussion subject, IClient client) throws RemoteException;

	/**
	 * Send a request to client to send the message msg on the subject subject
	 * @param subject
	 * @param msg
	 * @param client
	 * @throws RemoteException
	 */
	public void pleaseSendMessage(ISubjectDiscussion subject, String msg, IClient client) throws RemoteException;

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
	 * Send a request to server to remove a subject
	 * @param ISubjectDiscussion subject is the subject to remove
	 * @param IClient client is the one asking for subject removal (TODO implement RBAC, default: everyone has all rights) 
	 * @param IServerForum server is the host of the subject 
	 * @throws RemoteException
	 */
	public void pleaseRemoveSubject( ISubjectDiscussion subject, IClient client, IServerForum server ) throws RemoteException;

}
