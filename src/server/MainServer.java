package server;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
		}
		catch (Exception e){
			System.out.println("Registry");
		}
		try {
			ServerForum server = new ServerForum();
			String serverAddress = "//" + InetAddress.getLocalHost().getHostAddress() + "/ServerForum";
			Naming.rebind(serverAddress, server);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
	}

}
