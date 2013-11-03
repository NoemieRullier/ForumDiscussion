package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

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
				System.out.println( 
					this.getClass().getName() + ": " + 
					c.getLogin() + " subscribed to " + this.title 
				);
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
				System.out.println( 
					this.getClass().getName() + ": " + 
					c.getLogin() + " unsubscribed to " + this.title 
				);
			}
		}
		return bFree;
	}
	
	@Override
	public boolean unsubscribeAll() throws RemoteException {
		synchronized (listClientMonitor) {
			this.listClient = new ArrayList<IClient>(); 
		}
		return this.listClient.isEmpty(); 
	}

	@Override
	public void broadcast(String msg) throws RemoteException {
		System.out.println( this.getClass().getName() + ": New message broadcast... " );
		synchronized (listClientMonitor) {
			for (IClient dc : listClient){
				dc.displayMessage( this, msg);
				System.out.println( 
					this.getClass().getName() + 
					": Hey " + dc.getLogin() + "! You have a new message here at " + this.title + ": \"" + msg + "\"" 
				);
			}
		}
	}

}
