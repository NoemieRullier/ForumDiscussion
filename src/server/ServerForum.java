package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class ServerForum extends UnicastRemoteObject implements IServerForum{

	private static final long serialVersionUID = 1L;
	
	private List<ISubjectDiscussion> listSubject;

	public ServerForum() throws RemoteException {
		listSubject = new LinkedList<ISubjectDiscussion>();
		addSubjectDiscussion("Cinéma");
		addSubjectDiscussion("Gymnastique");
		addSubjectDiscussion("Radio");
	}
	
	public List<ISubjectDiscussion> getListSubject() throws RemoteException {
		return listSubject;
	}

	@Override
	public ISubjectDiscussion getSubject(String title) throws RemoteException {
		ISubjectDiscussion subject = null;
		for (ISubjectDiscussion s : listSubject){
			if (s.getTitle().equals(title)){
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
			SubjectDiscussion subj = new SubjectDiscussion(title);
			this.listSubject.add(subj);
		}
		return bFree;
	}

}
