package client;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import server.IServerForum;
import client.view.MainWindowClient;

public class MainClient {
	
	public static void main(String[] args) {
		try {
			String serverAddress = "//" + InetAddress.getLocalHost().getHostAddress() + ":1099/ServerForum";
			//String serverAddress = "//192.168.137.1:1099/ServerForum";
			IServerForum chatServer = (IServerForum) Naming.lookup(serverAddress);
			System.out.println(new Object(){}.getClass().getEnclosingClass().getName() + ": <OK> Connexion establshed with " + serverAddress);
			new MainWindowClient(chatServer);
		}
		catch (UnknownHostException e){
			System.out.println(new Object(){}.getClass().getEnclosingClass().getName() + ": <ERROR>[4] UnknownHostException"); 
			e.printStackTrace();
		}
		catch(NotBoundException e){
			System.out.println(new Object(){}.getClass().getEnclosingClass().getName() + ": <ERROR>[5] NotBoundException"); 
			e.printStackTrace();
		}
		catch (MalformedURLException e){
			System.out.println(new Object(){}.getClass().getEnclosingClass().getName() + ": <ERROR>[2] MalformedURLException"); 
			e.printStackTrace();
		}
		catch (RemoteException e){
			System.out.println(new Object(){}.getClass().getEnclosingClass().getName() + ": <ERROR>[3] RemoteException"); 
			e.printStackTrace();
		}
	}

}
