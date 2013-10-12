import java.rmi.RemoteException;

import server.ISubjectDiscussion;
import server.SubjectDiscussion;
import client.DisplayClient;


public class Main {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		ISubjectDiscussion s = new SubjectDiscussion("voui");
		DisplayClient dc = new DisplayClient("Cinema",s);

	}

}
