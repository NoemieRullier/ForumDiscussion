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
			System.out.println( new Object(){}.getClass().getEnclosingClass().getName() + ": <OK> Connection with " + serverAddress );
			new MainWindowClient(chatServer);
		}
		catch (UnknownHostException e){
			e.printStackTrace();
		}
		catch(NotBoundException e){
			e.printStackTrace();
		}
		catch (MalformedURLException e){
			e.printStackTrace();
		}
		catch (RemoteException e){
			e.printStackTrace();
		}
	}

}
