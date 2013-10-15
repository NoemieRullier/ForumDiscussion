package client;

import java.net.InetAddress;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import client.controller.ClientController;
import client.controller.IClientController;
import client.model.Client;
import client.model.IClient;
import client.view.MainWindowClient;
import server.IServerForum;
import server.ISubjectDiscussion;

public class MainClient {
	
	private static List<ISubjectDiscussion> listSubject = new ArrayList<ISubjectDiscussion>();
	
	private static IClient client; 
	private static IClientController controller; 
	

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
			 
			MainWindowClient window = new MainWindowClient(listSubject, "Nomyx");
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Ca marche pas");
		}
	}

}
