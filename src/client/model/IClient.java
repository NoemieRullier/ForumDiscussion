package client.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.ISubjectDiscussion;

public interface IClient  extends Remote {
	
	public String getLogin() throws RemoteException;
	
	public void setLogin(String login) throws RemoteException;
	
	public boolean pleaseSubscribe( ISubjectDiscussion subject ) throws RemoteException;
	
	public boolean pleaseUnsubscribe( ISubjectDiscussion subject ) throws RemoteException;
	
	public void pleaseSendMessage( ISubjectDiscussion subject, String msg ) throws RemoteException;
	
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException;
	
	public boolean equals( IClient client ) throws RemoteException;
	
}
