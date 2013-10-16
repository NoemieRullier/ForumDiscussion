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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.IServerForum;
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
	private static List<ISubjectDiscussion> listSubject = new ArrayList<ISubjectDiscussion>();
	
	private JButton buttonSubject;
	private List<JButton> listButtonSubject = new ArrayList<JButton>();
	private JPanel panel = new JPanel();
	private ImageIcon iconWindow = new ImageIcon("img/speech-bubble_32.png");
	private String pseudo;
	
	public MainWindowClient(IServerForum chatServer) throws RemoteException {
		
		this.controller = new ClientController(this, chatServer);
		
		new LoginView(this, true);
		
		this.client = new Client(controller, this.pseudo);
		
		int nbSujets = chatServer.nbSujets();
		for ( int i = 0 ; i < nbSujets ; i++ ) {
			ISubjectDiscussion sujet = chatServer.sendSubject( i ); 
			System.out.println( sujet.getTitle() ); 
			listSubject.add( sujet );
			buttonSubject = new JButton( sujet.getTitle() );
			buttonSubject.addActionListener( new ButtonSubscribeListener( sujet ) ); 
			panel.add(buttonSubject);
			listButtonSubject.add(buttonSubject);
		}
		
		this.setTitle( "Bienvenue " + pseudo);
		this.setContentPane(panel);
		this.setIconImage(iconWindow.getImage());
		this.setVisible(true);
		this.setSize(600, 70);
	}
	
	
	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public IClientController getController() {
		return controller;
	}



	public void setController(IClientController controller) {
		this.controller = controller;
	}



	public void activeButton(String title){
		for (JButton button : listButtonSubject){
			if (button.getText().equals(title)){
				button.setEnabled(true);
			}
		}
		// RŽcupŽrer les boutons et leur sujets associŽ
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
