package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import provider.ISubjectProvider;
import provider.SubjectProvider;
import client.model.IClient;

public class ServerForum extends UnicastRemoteObject implements IServerForum {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6843509051426878264L;

	private HashMap<String,ISubjectProvider> listProvider;
	private HashMap<String, IClient> pseudosUsed;
	private final Object listPseudoMonitor = new Object(); // Protects "pseudosUsed"
	private final Object listProviderMonitor = new Object(); // Protects "listProvider"

	public ServerForum() throws RemoteException {
		listProvider = new HashMap<String, ISubjectProvider>();
		pseudosUsed = new HashMap<String, IClient>();
	}
	
	public void initializeSubjects() throws RemoteException{
		listProvider.put("Cinema", new SubjectProvider("Cinema"));
		listProvider.put("Gymnastique", new SubjectProvider("Gymnastique"));
		listProvider.put("Radio", new SubjectProvider("Radio"));
	}

	@Override
	public ArrayList<String> sendSubjects() throws RemoteException {
		ArrayList<String> result = new ArrayList<String>();
		result.addAll(listProvider.keySet());
		return result;
	}

	@Override
	public int nbSujets() throws RemoteException {
		return listProvider.size(); 
	}

	@Override
	public ISubjectProvider getSubject( String title ) throws RemoteException {
		ISubjectProvider subjectProvider = listProvider.get(title);
		return subjectProvider;
	}

	@Override
	public boolean addSubjectDiscussion(String title) throws RemoteException {
		boolean bFree = true; 
		if (this.listProvider.containsKey(title)){
			bFree = false;
		}
		if ( bFree ) {
			ISubjectProvider subj = new SubjectProvider(title);
			this.listProvider.put(title, subj);
			for (IClient client : pseudosUsed.values()){
				client.recuperateSubjects();
			}
		}
		return bFree;
	}

	@Override
	public boolean pseudoAvailable(String pseudo) throws RemoteException {
		boolean bFree = true;
		synchronized (listPseudoMonitor) {
			for (String p : pseudosUsed.keySet()){
				if (p.equals(pseudo)){
					bFree = false;
				}
			}
			if ( bFree ) {
				this.pseudosUsed.put(pseudo, null);
			}
		}
		return bFree;
	}

	@Override
	public void removeLogin(String login) throws RemoteException {
		boolean bFree = false; 
		synchronized (listPseudoMonitor) {
			for (String p : pseudosUsed.keySet()){
				if (p.equals(login)){
					bFree = true;
				}
			}
			if ( bFree ) {
				this.pseudosUsed.remove(login);
				System.out.println(login + " was unsubscribe ");
			}
		}
	}

	@Override
	public boolean verifyAvailableTitle(String title) throws RemoteException {
		synchronized (listProviderMonitor) {
			return ! this.listProvider.containsKey(title);
		}
	}

	@Override
	public void addClient(String pseudo, IClient client) throws RemoteException {
		pseudosUsed.put(pseudo, client);
	}

}
