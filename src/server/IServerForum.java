package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import provider.ISubjectProvider;

public interface IServerForum extends Remote {

	/**
	 * Get the subjectProvider associate at the title
	 * @param title
	 * @return The SubjectProvider corresponding to title or null if subject doesn't exist
	 * @throws RemoteException
	 */
	public ISubjectProvider getSubject(String title) throws RemoteException;

	/**
	 * Add a new subject on the sever
	 * @param title
	 * @return true if the subject doesn't exist and is add, false otherwise
	 * @throws RemoteException
	 */
	public boolean addSubjectDiscussion(String title) throws RemoteException; 


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

	/**
	 * Send the list of subject's title
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<String> sendSubjects() throws RemoteException;

}
