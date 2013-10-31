/**
 * 
 */
package client.controller;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import provider.ISubjectProvider;
import server.IServerForum;
import server.ISubjectDiscussion;
import server.SubjectDiscussion;
import client.model.IClient;
import client.view.MainWindowClient;
import client.view.SubjectView;


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
	public void pleaseSubscribe( ISubjectProvider subjectProvider, IClient client ) throws RemoteException {
		// TODO Add JProgress bar en attendant que connexion soit OK ?
		if ( client.pleaseSubscribe( subjectProvider ) ) {
			mapsv.put( /*subject*/ subjectProvider.getSubject(), new SubjectView( subjectProvider, this, client ) ); 
		}
	}

	@Override
	public void pleaseUnsubscribe( ISubjectProvider subjectProvider, IClient client ) throws RemoteException { 
		if ( client.pleaseUnsubscribe(subjectProvider)) {
			mapsv.remove( subjectProvider ); 
		}
		mainWindowClient.activeButton(subjectProvider.getSubject().getTitle());
	}

	@Override
	public void pleaseSendMessage( ISubjectProvider subjectProvider, String msg, IClient client ) throws RemoteException {
		client.pleaseSendMessage(subjectProvider, msg);
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
