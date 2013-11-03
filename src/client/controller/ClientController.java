/**
 * 
 */
package client.controller;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import client.model.IClient;
import client.view.MainWindowClient;
import client.view.SubjectView;
import server.IServerForum;
import server.ISubjectDiscussion;


/**
 * @author CLEm
 *
 */
public class ClientController implements IClientController {

	private IServerForum chatServer;
	private Map< ISubjectDiscussion, SubjectView > mapsv;
	private MainWindowClient mainWindowClient;

	public ClientController(MainWindowClient mainWindowClient, IServerForum chatServer) {
		this.chatServer = chatServer;
		this.mainWindowClient = mainWindowClient;
		mapsv = new HashMap< ISubjectDiscussion, SubjectView >(); 
	}

	@Override
	public void pleaseSubscribe( ISubjectDiscussion subject, IClient client ) throws RemoteException {
		// TODO > Add JProgress bar en attendant que connexion soit OK ? 
		// 	In an async context, we can't have an accurate ETA, 
		// 	a loading animation loop could be better than a progress bar ;) 
		if ( client.pleaseSubscribe( subject ) ) {
			mapsv.put( subject, new SubjectView( subject, this, client ) ); 
		}
	}

	@Override
	public void pleaseUnsubscribe( ISubjectDiscussion subject, IClient client ) throws RemoteException { 
		if ( client.pleaseUnsubscribe(subject) ) {
			mapsv.remove( subject ); 
		}
		mainWindowClient.activeButton(subject.getTitle());
	}
	
	@Override
	public void pleaseRemoveSubject( ISubjectDiscussion subject, IClient client, IServerForum server ) throws RemoteException { 
		client.pleaseRemoveSubject( server, subject.getTitle() );
	}

	@Override
	public void pleaseSendMessage( ISubjectDiscussion subject, String msg, IClient client ) throws RemoteException {
		client.pleaseSendMessage(subject, msg);
	}

	@Override
	public void displayMessage( ISubjectDiscussion subject, String msg ) throws RemoteException {
		mapsv.get( subject ).displayMessage( msg ); 
	}

	@Override
	public boolean verifyAvailableLogin(String login) throws RemoteException {
		return chatServer.pseudoAvailable(login);
	}

	@Override
	public void pleaseRemoveLogin(String login) throws RemoteException {
		chatServer.removeLogin(login);
	}

}
