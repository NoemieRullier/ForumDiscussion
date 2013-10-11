package server.remote;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class RemoteList<T> extends UnicastRemoteObject implements IRemoteList<T>{
	
	private static final long serialVersionUID = -2089731920348238926L;
	
	List<T> l; 
	
	public RemoteList() throws RemoteException {
		this.l = new LinkedList<T>(); 
	}
	
	/* (non-Javadoc)
	 * @see server.remote.IRemoteList#add(T)
	 */
	@Override
	public void add( T e ) {
		this.l.add( e ); 
	}
	
	/* (non-Javadoc)
	 * @see server.remote.IRemoteList#size()
	 */
	@Override
	public int size() {
		return this.l.size(); 
	}
	
	/* (non-Javadoc)
	 * @see server.remote.IRemoteList#copie(java.util.List)
	 */
	@Override
	public void copie( List< T > l ) {
		for ( T elem : l  ) {
			this.l.add( elem ); 
		}
	}
}
