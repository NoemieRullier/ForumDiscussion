package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import provider.ISubjectProvider;
import provider.SubjectProvider;

public class ServerForum extends UnicastRemoteObject implements IServerForum {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6843509051426878264L;

//	private List<ISubjectDiscussion> listSubject;
	private HashMap<String,ISubjectProvider> listProvider;
	private List<String> pseudosUsed;
	private final Object listPseudoMonitor = new Object(); // Protects "pseudosUsed"

	public ServerForum() throws RemoteException {
		listProvider = new HashMap<String, ISubjectProvider>();
//		listSubject = new ArrayList<ISubjectDiscussion>();
		pseudosUsed = new ArrayList<String>();
		
		//addSubjectDiscussion("Cinema");
		//addSubjectDiscussion("Gymnastique");
		//addSubjectDiscussion("Radio");
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
		}
		return bFree;
	}

	@Override
	public boolean pseudoAvailable(String pseudo) throws RemoteException {
		boolean bFree = true;
		synchronized (listPseudoMonitor) {
			for (String p : pseudosUsed){
				if (p.equals(pseudo)){
					bFree = false;
				}
			}
			if ( bFree ) {
				this.pseudosUsed.add(pseudo);
			}
		}
		return bFree;
	}

	@Override
	public void removeLogin(String login) throws RemoteException {
		boolean bFree = false; 
		synchronized (listPseudoMonitor) {
			for (String p : pseudosUsed){
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

}
