package server.remote;

import java.rmi.server.RemoteObject;
import java.util.LinkedList;
import java.util.List;

public class RemoteList<T> extends RemoteObject {
	
	private static final long serialVersionUID = -2089731920348238926L;
	
	List<T> l; 
	
	public RemoteList() {
		this.l = new LinkedList<T>(); 
	}
	
	public void add( T e ) {
		this.l.add( e ); 
	}
	
	public int size() {
		return this.l.size(); 
	}
	
	public void copie( List< T > l ) {
		for ( T elem : l  ) {
			this.l.add( elem ); 
		}
	}
}
