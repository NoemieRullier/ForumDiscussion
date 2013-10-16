package client;

import java.net.InetAddress;
import java.rmi.Naming;

import server.IServerForum;
import client.view.MainWindowClient;

public class MainClient {
	
	public static void main(String[] args) {
		try {
			String serverAddress = "//" + InetAddress.getLocalHost().getHostAddress() + ":1099/ServerForum";
			//String serverAddress = "//192.168.137.1:1099/ServerForum";
			IServerForum chatServer = (IServerForum) Naming.lookup(serverAddress);
			System.out.println("Connexion OK");
			new MainWindowClient(chatServer);
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Ca marche pas");
		}
	}

}
