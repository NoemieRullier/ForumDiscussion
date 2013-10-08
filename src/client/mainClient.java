package client;

import java.net.InetAddress;
import java.rmi.Naming;
import java.util.List;

import server.IServerForum;
import server.ISubjectDiscussion;
import server.SubjectDiscussion;

public class mainClient {
	
	private static List<ISubjectDiscussion> listSubject;

	public static void main(String[] args) {
		try {
			String serverAddress = "//" + InetAddress.getLocalHost().getHostAddress() + "/ServerForum";
			IServerForum chatServer = (IServerForum) Naming.lookup(serverAddress);
			listSubject = chatServer.getListSubject();
			MainWindow window = new MainWindow(listSubject);
		}
		catch (Exception e){
			System.out.println("Ca marche pas");
		}
	}

}
