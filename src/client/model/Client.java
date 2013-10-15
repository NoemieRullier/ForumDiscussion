/**
 * 
 */
package client.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.ClientController;
import client.controller.IClientController;
import server.ISubjectDiscussion;


/**
 * @author CLEm
 *
 */
public class Client extends UnicastRemoteObject implements IClient {
	
	private IClientController controller; 
	
	public Client( IClientController controller ) throws RemoteException {
		this.controller = controller; 
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
		return false; 
	}
	
	/* (non-Javadoc)
	 * @see client.model.IClient#pleaseSendMessage(server.ISubjectDiscussion, java.lang.String)
	 */
	@Override
	public void pleaseSendMessage( ISubjectDiscussion subject, String msg ) throws RemoteException {
		
	}
	
	/* (non-Javadoc)
	 * @see client.model.IClient#displayMessage(server.ISubjectDiscussion, java.lang.String)
	 */
	@Override
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException {
		controller.displayMessage( subject, msg ); 
	}
	
}
