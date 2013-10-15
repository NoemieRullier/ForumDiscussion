/**
 * 
 */
package client.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.IClientController;
import server.ISubjectDiscussion;


/**
 * @author CLEm
 *
 */
public class Client extends UnicastRemoteObject implements IClient {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4449348011980703942L;
	
	private String login;
	private IClientController controller; 
	
	public Client( IClientController controller, String login ) throws RemoteException {
		this.controller = controller;
		this.login = login;
	}
	
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	/* (non-Javadoc)
	 * @see client.model.IClient#pleaseSubscribe(server.ISubjectDiscussion)
	 */
	@Override
	public boolean pleaseSubscribe( ISubjectDiscussion subject ) throws RemoteException {
		return subject.registration( this ); 
	}
	/* (non-Javadoc)
	 * @see client.model.IClient#pleaseUnsubscribe(server.ISubjectDiscussion)
	 */
	@Override
	public boolean pleaseUnsubscribe( ISubjectDiscussion subject ) throws RemoteException {
		System.out.println("appel in client");
		return subject.unsubscribe(this); 
	}
	
	/* (non-Javadoc)
	 * @see client.model.IClient#pleaseSendMessage(server.ISubjectDiscussion, java.lang.String)
	 */
	@Override
	public void pleaseSendMessage( ISubjectDiscussion subject, String msg ) throws RemoteException {
		 subject.broadcast(msg);
	}
	
	/* (non-Javadoc)
	 * @see client.model.IClient#displayMessage(server.ISubjectDiscussion, java.lang.String)
	 */
	@Override
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException {
		controller.displayMessage( subject, msg ); 
	}
	
	public boolean equals(IClient client) throws RemoteException{
		return (this.login.equals(client.getLogin()));
	}
	
}
