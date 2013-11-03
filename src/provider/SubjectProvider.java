package provider;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import server.IServerForum;
import server.ISubjectDiscussion;
import server.SubjectDiscussion;
import client.model.IClient;

public class SubjectProvider extends UnicastRemoteObject implements ISubjectProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3742097203624368753L;
	private ISubjectDiscussion subject;
	private IServerForum server;

	public SubjectProvider(String title) throws RemoteException{
		try {
			String serverAddress = "//" + InetAddress.getLocalHost().getHostAddress() + ":1099/ServerForum";
			//String serverAddress = "//192.168.137.1:1099/ServerForum";
			server = (IServerForum) Naming.lookup(serverAddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		subject = new SubjectDiscussion(title);
	}

	public ISubjectDiscussion getSubject() throws RemoteException{
		return subject;
	}

	public void setSubject(ISubjectDiscussion subject) {
		this.subject = subject;
	}

	@Override
	public boolean subscribe(IClient client) throws RemoteException {
		return this.subject.registration(client);
	}

	@Override
	public boolean unsubscribe(IClient client) throws RemoteException {
		return this.subject.unsubscribe(client);
	}

	@Override
	public boolean broadcast(String msg) throws RemoteException {
		this.subject.broadcast(msg);
		return true; 
	}

	@Override
	public boolean prepareDeletion() throws RemoteException { 
		boolean bDeletionReady = false; 
		
		// warn every participant about the situation 
		if ( 
			this.broadcast( "[" + new SimpleDateFormat("HH:mm:ss", Locale.FRANCE).format(new Date()) + "] - Tous les participants de " + 
				this.subject.getTitle() + " vont être désinscrits. Vos prochains messages dans ce sujet seront ignorés. \n" 
			) 
		) {
			// unsubscribe everyone before deletion 
			if ( this.subject.unsubscribeAll() ) {
				bDeletionReady = true; // no more steps before deletion 
			}
		}
		
		return bDeletionReady; 
	}

}
