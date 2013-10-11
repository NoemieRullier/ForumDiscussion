package client;

import java.net.InetAddress;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import server.IServerForum;
import server.ISubjectDiscussion;

public class mainClient {
	
	private static List<ISubjectDiscussion> listSubject = new ArrayList<ISubjectDiscussion>();

	public static void main(String[] args) {
		try {
			String serverAddress = "//" + InetAddress.getLocalHost().getHostAddress() + ":1099/ServerForum";
			//String serverAddress = "//192.168.137.1:1099/ServerForum";
			IServerForum chatServer = (IServerForum) Naming.lookup(serverAddress);
			System.out.println("Connexion OK");
			ISubjectDiscussion sujet; 
			int nbSujets = chatServer.nbSujets(); 
			System.out.println( nbSujets );
			int i; 
			for ( i = 0 ; i < nbSujets ; i++ ) {
				sujet = chatServer.sendSubject( i ); 
				System.out.println( sujet.getTitle() ); 
				listSubject.add( sujet ); 
			}
			System.out.println( "Liste locale : nbElem " + listSubject.size() );
			MainWindow window = new MainWindow(listSubject);
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Ca marche pas");
		}
	}

}
