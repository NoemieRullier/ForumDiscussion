package server;
import java.rmi.RemoteException;
import java.util.LinkedList;

import client.*;

public class SubjectDiscussion implements ISubjectDiscussion {
	
	LinkedList<DisplayClient> listClient;

	@Override
	public void registration(IDisplayClient c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desinscription(IDisplayClient c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcast(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
