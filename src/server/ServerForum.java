package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerForum extends UnicastRemoteObject implements IServerForum {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6843509051426878264L;

	private List<ISubjectDiscussion> listSubject;
	private List<String> pseudosUsed;
	private final Object listPseudoMonitor = new Object(); // Protects "pseudosUsed"

	public ServerForum() throws RemoteException {
		listSubject = new ArrayList<ISubjectDiscussion>();
		pseudosUsed = new ArrayList<String>();
		addSubjectDiscussion("Cinema");
		addSubjectDiscussion("Gymnastique");
		addSubjectDiscussion("Radio");
	}

	@Override
	public ISubjectDiscussion sendSubject( int pos ) throws RemoteException {
		return listSubject.get( pos ); 
	}

	@Override
	public int nbSujets() throws RemoteException {
		return listSubject.size(); 
	}

	@Override
	public ISubjectDiscussion getSubject( String title ) throws RemoteException {
		ISubjectDiscussion subject = null;
		for ( ISubjectDiscussion s : listSubject ){
			if ( s.getTitle().equals( title ) ){
				subject = s;
			}
		}
		return subject;
	}

	@Override
	public boolean addSubjectDiscussion(String title) throws RemoteException {
		boolean bFree = true; 
		for (ISubjectDiscussion s : listSubject){
			if (s.getTitle().equals(title)){
				bFree = false;
			}
		}
		if ( bFree ) {
			ISubjectDiscussion subj = new SubjectDiscussion(title);
			this.listSubject.add(subj);
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
