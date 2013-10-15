/**
 * 
 */
package client.controller;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import client.model.IClient;
import client.view.SubjectView;
import server.ISubjectDiscussion;


/**
 * @author CLEm
 *
 */
public class ClientController implements IClientController {
	
	
	private Map< ISubjectDiscussion, SubjectView > mapsv; 
	
	public ClientController() {
		mapsv = new HashMap< ISubjectDiscussion, SubjectView >(); 
	}
	
	/* (non-Javadoc)
	 * @see client.controller.IClientController#pleaseSubscribe(server.ISubjectDiscussion, client.model.IClient)
	 */
	@Override
	public void pleaseSubscribe( ISubjectDiscussion subject, IClient client ) throws RemoteException { 
		if ( client.pleaseSubscribe( subject ) ) {
			mapsv.put( subject, new SubjectView( subject, this, client ) ); 
		}
	}
	
	
	/* (non-Javadoc)
	 * @see client.controller.IClientController#pleaseSubscribe(server.ISubjectDiscussion, client.model.IClient)
	 */
	@Override
	public void pleaseUnsubscribe( ISubjectDiscussion subject, IClient client ) throws RemoteException { 
		if ( client.pleaseUnsubscribe(subject)) {
			System.out.println("Server return true so remove from map");
			mapsv.remove( subject ); 
		}
	}
	
	/* (non-Javadoc)
	 * @see client.controller.IClientController#pleaseSendMessage(server.ISubjectDiscussion, java.lang.String)
	 */
	@Override
	public void pleaseSendMessage( ISubjectDiscussion subject, String msg ) throws RemoteException {
		subject.broadcast( msg ); 
	}
	
	/* (non-Javadoc)
	 * @see client.controller.IClientController#displayMessage(server.ISubjectDiscussion, java.lang.String)
	 */
	@Override
	public void displayMessage( ISubjectDiscussion subject, String msg ) {
		mapsv.get( subject ).displayMessage( msg ); 
	}
	
}
