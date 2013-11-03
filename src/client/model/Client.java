/**
 * 
 */
package client.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import provider.ISubjectProvider;
import server.ISubjectDiscussion;
import client.controller.IClientController;


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


	@Override
	public boolean pleaseSubscribe( ISubjectProvider subjectProvider ) throws RemoteException {
		return subjectProvider.subscribe(this); 
	}
	
	@Override
	public boolean pleaseUnsubscribe( ISubjectProvider subjectProvider ) throws RemoteException {
		return subjectProvider.unsubscribe(this); 
	}
	
	@Override
	public void pleaseSendMessage( ISubjectProvider subjectProvider, String msg ) throws RemoteException {
		 subjectProvider.broadcast(msg);
	}
	
	@Override
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException {
		controller.displayMessage( subject, msg ); 
	}
	
	@Override
	public boolean equals(IClient client) throws RemoteException{
		return (this.login.equals(client.getLogin()));
	}


	@Override
	public void recuperateSubjects() throws RemoteException {
		controller.recuperateSubjects();
	}
	
}
