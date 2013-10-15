/**
 * 
 */
package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.ISubjectDiscussion;
import client.controller.ClientController;
import client.controller.IClientController;
import client.model.Client;
import client.model.IClient;


/**
 * @author CLEm
 *
 */
public class MainWindowClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8596715838932975027L;

	
	private IClientController controller;
	private IClient client; 
	
	private JButton buttonSubject;
	private List<JButton> listButtonSubject = new ArrayList<JButton>();
	private JPanel panel = new JPanel(); 

	public MainWindowClient( List<ISubjectDiscussion> listSubject) throws RemoteException {
		
		this.controller = new ClientController(this);
		this.client = new Client(controller, "Nomyx");
		
		for ( ISubjectDiscussion s : listSubject ){
			buttonSubject = new JButton( s.getTitle() );
			buttonSubject.addActionListener( new ButtonSubscribeListener( s ) ); 
			panel.add(buttonSubject);
			listButtonSubject.add(buttonSubject);
		}
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(600, 70);
	}
	
	public void activeButton(String title){
		for (JButton button : listButtonSubject){
			if (button.getText().equals(title)){
				button.setEnabled(true);
			}
		}
		// Récupérer les boutons et leur sujets associé
	}
	
	private class ButtonSubscribeListener implements ActionListener {
		
		private ISubjectDiscussion subject; 
		
		public ButtonSubscribeListener( ISubjectDiscussion subject ) {
			this.subject = subject; 
		}
		
		
		@Override
		public void actionPerformed( ActionEvent e ) {
			try {
				controller.pleaseSubscribe( subject, client );
				((AbstractButton) e.getSource()).setEnabled(false);
			} catch( RemoteException e1 ) {
				System.out.println( "le main window client marche paaaaas T_T" );
				e1.printStackTrace();
			} 
		}
	}
	
}
