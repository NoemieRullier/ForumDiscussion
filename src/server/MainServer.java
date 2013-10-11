package server;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainServer {
	
	public static boolean debug = false;

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
		}
		catch (Exception e){
			System.out.println("Port already use");
		}
		try {
			ServerForum server = new ServerForum();
//			String serverAddress = "//192.168.137.1:1099/ServerForum"; 
			String serverAddress = "//"+ InetAddress.getLocalHost().getHostAddress() +":1099/ServerForum";
			Naming.rebind(serverAddress, server);
			System.out.println("ok !");
		}
		catch (Exception e) {
			if (debug) {
				e.printStackTrace();
			}
			System.out.println("Error");
		}
	}

}
