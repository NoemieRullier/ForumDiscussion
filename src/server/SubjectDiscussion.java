package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import client.model.IClient;

public class SubjectDiscussion extends UnicastRemoteObject implements ISubjectDiscussion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2403065407962436933L;

	private List<IClient> listClient;
	private final Object listClientMonitor = new Object(); // Protects "listClient"
	private String title;


	public SubjectDiscussion(String title) throws RemoteException {
		this.title = title;
		listClient = new ArrayList<IClient>(); 
	}

	public List<IClient> getListClient() {
		return listClient;
	}

	public void setListClient(List<IClient> listClient) {
		this.listClient = listClient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean registration( IClient c) throws RemoteException {
		boolean bFree = true;
		synchronized (listClientMonitor) {
			for (IClient dc : listClient){
				if (dc.equals(c)){
					bFree = false;
				}
			}
			if ( bFree ) {
				this.listClient.add(c);
			}
		}
		return bFree; 
	}

	@Override
	public boolean unsubscribe(IClient c) throws RemoteException {
		boolean bFree = false; 
		synchronized (listClientMonitor) {
			for (IClient dc : listClient){
				if (dc.equals(c)){
					bFree = true;
				}
			}
			if ( bFree ) {
				this.listClient.remove(c);
				System.out.println( this.getClass().getName() + ": " + c.getLogin() + " unsubscribed to " + this.getTitle() );
			}
		}
		return bFree;
	}
	
	@Override
	public int unsubscribeEveryone() throws RemoteException {
		int cptUnsubscribed = 0, i = 0; 
		List<IClient> tempListAllClients = new ArrayList<IClient>( this.listClient );
		
		this.broadcast( "[" + new SimpleDateFormat("HH:mm:ss", Locale.FRANCE).format(new Date()) + "] - Tous les participants de " + 
			this.getTitle() + " vont être désinscrits. Vos prochains messages dans ce sujet seront ignorés. \n" 
		); 
		
		for ( i = 0 ; i < tempListAllClients.size() ; i++ ) {
			IClient dc = tempListAllClients.get( i ); 
			if ( this.unsubscribe( dc ) ) {
				cptUnsubscribed++; 
			}
		}
		
		return cptUnsubscribed;
	}

	@Override
	public void broadcast(String msg) throws RemoteException {
		System.out.println( this.getClass().getName() + ": Broadcast the message to the subscribers of " + this.getTitle() );
		synchronized (listClientMonitor) {
			for (IClient dc : listClient){
				dc.displayMessage( this, msg);
				System.out.println( this.getClass().getName() + ": Hey " + dc.getLogin() + "! New message received: \"" + msg + "\"");
			}
		}
	}
	
	

}
