package server;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import client.*;

public class SubjectDiscussion extends UnicastRemoteObject implements ISubjectDiscussion {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2403065407962436933L;
	private List<IDisplayClient> listClient;
	private String title;
	

	public SubjectDiscussion(String title) throws RemoteException {
		this.title = title;
		listClient = new ArrayList<IDisplayClient>(); 
	}
	
	public List<IDisplayClient> getListClient() {
		return listClient;
	}

	public void setListClient(List<IDisplayClient> listClient) {
		this.listClient = listClient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void registration(IDisplayClient c) throws RemoteException {
		boolean bFree = true; 
		for (IDisplayClient dc : listClient){
			if (dc.equals(c)){
				bFree = false;
			}
		}
		if ( bFree ) {
			this.listClient.add(c);
		}
	}

	@Override
	public void desinscription(IDisplayClient c) throws RemoteException {
		boolean bFree = false; 
		for (IDisplayClient dc : listClient){
			if (dc.equals(c)){
				bFree = true;
			}
		}
		if ( bFree ) {
			this.listClient.remove(c);
			System.out.println("removed ");
		}
	}

	@Override
	public void broadcast(String msg) throws RemoteException {
		for (IDisplayClient dc : listClient){
			dc.display(msg);
		}
	}

}
