package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerForum extends UnicastRemoteObject implements IServerForum{

	private static final long serialVersionUID = 1L;

	public ServerForum() throws RemoteException {
		//super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ISubjectDiscussion getSubject(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
