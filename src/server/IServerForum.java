package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerForum extends Remote {

	/**
	 * Get the subject associate at the title
	 * @param title
	 * @return The SubjectDiscussion corresponding to title or null if subject doesn't exist
	 * @throws RemoteException
	 */
	public ISubjectDiscussion getSubject(String title) throws RemoteException;

	/**
	 * Add a new subject on the sever
	 * @param title
	 * @return true if the subject doesn't exist and is add, false otherwise
	 * @throws RemoteException
	 */
	public boolean addSubjectDiscussion(String title) throws RemoteException; 

	/**
	 * Remove a subject from the server-side list of available subjects or fails by doing nothing 
	 * @param String title, title of the subject to remove from the list 
	 * @return true if the removal was successful, false if nothing changed (safe) 
	 * @throws RemoteException
	 */
	public boolean removeSubjectDiscussion( String title ) throws RemoteException;

	/**
	 * Get number of subjects save on server 
	 * @return Number of subjects
	 * @throws RemoteException
	 */
	public int nbSujets() throws RemoteException;

	/**
	 * Get the subject associate at the position pos
	 * @param pos
	 * @return The SubjectDiscussion corresponding to the pos in the list of subjects
	 * @throws RemoteException
	 */
	public ISubjectDiscussion sendSubject( int pos ) throws RemoteException;

	/**
	 * Define if the pseudo is available
	 * @param pseudo
	 * @return true if available, false otherwise
	 * @throws RemoteException
	 */
	public boolean pseudoAvailable(String pseudo) throws RemoteException;

	/**
	 * Delete the pseudo in list
	 * @param login
	 * @throws RemoteException
	 */
	public void removeLogin(String login) throws RemoteException;

}
