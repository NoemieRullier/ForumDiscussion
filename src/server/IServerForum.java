package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerForum extends Remote {

	/*
	 * TODO A revoir comment on fait la doc (je me souviens plus) ^^
	 * @result The SubjectDiscussion corresponding to title or null if subject doesn't exist
	 */
	public ISubjectDiscussion getSubject(String title) throws RemoteException;
	
	public boolean addSubjectDiscussion(String title) throws RemoteException; 
	
//	public List<ISubjectDiscussion> remoteListSubject() throws RemoteException; 
	
	public int nbSujets() throws RemoteException;

	ISubjectDiscussion sendSubject( int pos ) throws RemoteException; 
	
}
