package server;
import java.rmi.RemoteException;
import java.util.List;

import client.*;

public class SubjectDiscussion implements ISubjectDiscussion {
	
	private List<IDisplayClient> listClient;
	private String title;
	

	public SubjectDiscussion(String title) {
		this.title = title;
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
		}
	}

	@Override
	public void broadcast(String msg) throws RemoteException {
		for (IDisplayClient dc : listClient){
			dc.display(msg);
		}
	}

}
