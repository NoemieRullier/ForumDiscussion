/**
 * 
 */
package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private IServerForum chatServer; 
	private static List<ISubjectDiscussion> listSubject = new ArrayList<ISubjectDiscussion>();

	private JButton buttonSubject;
	private JButton buttonSubjectRemove;
	private ImageIcon iconRemoveItem = new ImageIcon("img/remove_item_001.png"); 
	private List<JButton> listButtonSubject = new ArrayList<JButton>();
	private JPanel panel = new JPanel();
	private ImageIcon iconWindow = new ImageIcon("img/speech-bubble_32.png");
	private String pseudo;

	public MainWindowClient(IServerForum chatServer) throws RemoteException {
		
		this.chatServer = chatServer; 

		this.controller = new ClientController(this, chatServer);

		new LoginView(this, true);

		this.client = new Client(controller, this.pseudo);

		System.out.println( this.getClass().getName() + ": Subject list from remote " + chatServer.getClass().getName() + ": " );
		
		int nbSujets = chatServer.nbSujets();
		for ( int i = 0 ; i < nbSujets ; i++ ) {
			ISubjectDiscussion sujet = chatServer.sendSubject( i ); 
			System.out.println( this.getClass().getName() + ": \t - " + sujet.getTitle() ); 
			listSubject.add( sujet );
			buttonSubject = new JButton( sujet.getTitle() );
			buttonSubjectRemove = new JButton( iconRemoveItem ); 
			buttonSubject.addActionListener( new ButtonSubscribeListener( sujet ) ); 
			buttonSubjectRemove.addActionListener( new ButtonSubjectRemoveListener( sujet ) ); 
			panel.add(buttonSubject);
			panel.add(buttonSubjectRemove);
			listButtonSubject.add(buttonSubject);
			listButtonSubject.add(buttonSubjectRemove);
		}

		this.setTitle( "Bienvenue " + pseudo);
		this.setContentPane(panel);
		this.setIconImage(iconWindow.getImage());
		this.setVisible(true);
		this.setSize(600, 70);
		this.addWindowListener( new MainWindowAdapter() );
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
				e1.printStackTrace();
				displayError("Impossible d'acceder au server pour vous connecter au sujet. \nVeuillez recommencer ulterieurement");
			} 
		}
	}
	
	private class ButtonSubjectRemoveListener implements ActionListener {

		private ISubjectDiscussion subject; 

		public ButtonSubjectRemoveListener( ISubjectDiscussion subject ) {
			this.subject = subject; 
		}

		@Override
		public void actionPerformed( ActionEvent e ) {
			try {
				controller.pleaseRemoveSubject( subject, client, chatServer );
				((AbstractButton) e.getSource()).setEnabled(false);
			} catch( RemoteException e1 ) {
				e1.printStackTrace();
				displayError("Impossible d'acceder au server pour supprimer le sujet. \nVeuillez recommencer ulterieurement");
			} 
		}
	}

	private class MainWindowAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getSource();
			int result = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Quit application", JOptionPane.YES_NO_OPTION );
			if (result == JOptionPane.YES_OPTION) {
				try {
					controller.pleaseRemoveLogin(client.getLogin());
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (RemoteException e1) {
					e1.printStackTrace();
					displayError("Impossible d'acceder au server pour vous deconnecter du sujet. \nVeuillez recommencer ulterieurement");
				}
			} 
		}
	}
	
	/**
	 * Display a JDialog with the error
	 * @param error
	 */
	public void displayError(String error){
		JOptionPane.showMessageDialog(null, error, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

}
