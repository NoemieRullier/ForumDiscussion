/**
 * 
 */
package client.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import provider.ISubjectProvider;
import server.IServerForum;
//import server.ISubjectDiscussion;
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
	private ImageIcon iconWindow = new ImageIcon("img/speech-bubble_32.png");
	private String pseudo;
	private IServerForum chatServer;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JButton addSubjectbutton = new JButton("Nouveau sujet");
	private JPanel listButton = new JPanel();

	public MainWindowClient(IServerForum chatServer) throws RemoteException {
		this.chatServer = chatServer;
		this.controller = new ClientController(this, chatServer);

		new LoginView(this, true);

		this.client = new Client(controller, this.pseudo);

		panel.setLayout(new GridBagLayout());
		/* Menu */
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		addSubjectbutton.addActionListener(new NewSubjectListener(this));
		panel.add(addSubjectbutton,gbc);
		
		/* Line */
		JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		panel.add(line,gbc);
		
		/* List of subjects */
		ArrayList<String> subjects = this.chatServer.sendSubjects();
		int nbSubjects = subjects.size();
		int nbSubjectsByLine = 5;
		listButton.setLayout(new GridLayout(nbSubjects/nbSubjectsByLine+1,nbSubjectsByLine));
//		listButton.setBackground(new Color(240, 109, 158));
		for (String s : subjects){
			buttonSubject = new JButton(s);
			buttonSubject.addActionListener(new ButtonSubscribeListener(s)); 
			listButton.add(buttonSubject);
			listButtonSubject.add(buttonSubject);
		}
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		panel.add(listButton,gbc);

		this.setTitle( "Bienvenue " + pseudo);
		this.setContentPane(panel);
		this.setIconImage(iconWindow.getImage());
		this.setVisible(true);
		this.setSize(900, 800);
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

		private String title;

		public ButtonSubscribeListener(String title) {
			this.title = title;
		}

		@Override
		public void actionPerformed( ActionEvent e ) {
			try {
				ISubjectProvider subjectProvider = chatServer.getSubject(title);
				controller.pleaseSubscribe( subjectProvider, client );
				((AbstractButton) e.getSource()).setEnabled(false);
			} catch( RemoteException e1 ) {
				e1.printStackTrace();
				displayError("Impossible d'acceder au server pour vous connecter au sujet. \nVeuillez recommencer ulterieurement");
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
	
	private class NewSubjectListener implements ActionListener{
		
		private MainWindowClient parent;
		
		public NewSubjectListener(MainWindowClient parent){
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new NewSubjectView(parent, true);
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
