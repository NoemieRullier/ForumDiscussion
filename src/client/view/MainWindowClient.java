/**
 * 
 */
package client.view;

//import java.awt.Color;
import java.awt.ComponentOrientation;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import provider.ISubjectProvider;
import server.IServerForum;
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
	private JButton buttonRemoveSubject; 
	private List<JButton> listButtonSubject = new ArrayList<JButton>();
	private JPanel panel = new JPanel();
	private ImageIcon iconWindow = new ImageIcon("img/speech-bubble_32.png");
	private String pseudo;
	private IServerForum chatServer;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JButton addSubjectbutton = new JButton("Nouveau sujet");
	private JPanel listButton = new JPanel(new GridBagLayout());
	private ImageIcon iconTrash = new ImageIcon("img/trash_32.png");

	public MainWindowClient(IServerForum chatServer) throws RemoteException {
		this.chatServer = chatServer;
		this.controller = new ClientController(this, chatServer);

		new LoginView(this, true);

		this.client = new Client(controller, this.pseudo);
		chatServer.addClient(this.pseudo, this.client);
		
		panel.setLayout(new GridBagLayout());
		/* Title Menu */
		JLabel titleMenu = new JLabel("Menu: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		panel.add(titleMenu,gbc);
		
		/* Menu */
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		addSubjectbutton.addActionListener(new NewSubjectListener(this));
		panel.add(addSubjectbutton,gbc);
		
		/* Line */
		JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		panel.add(line,gbc);
		
		/* Title */
		JLabel title = new JLabel("List of subjects: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		panel.add(title,gbc);
		
		/* List of subjects */
		getAllSubjects();

		this.setTitle( "Bienvenue " + pseudo);
		this.setSize(700, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setIconImage(iconWindow.getImage());
		this.addWindowListener( new MainWindowAdapter() );
	}

	

	public JPanel getListButton() {
		return listButton;
	}



	public void setListButton(JPanel listButton) {
		this.listButton = listButton;
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


	public IClient getClient() {
		return client;
	}



	public void setClient(IClient client) {
		this.client = client;
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
	
	private class ButtonRemoveSubjectListener implements ActionListener {

		private String title;

		public ButtonRemoveSubjectListener( String title ) {
			this.title = title;
		}

		@Override
		public void actionPerformed( ActionEvent e ) {
			try {
				controller.pleaseRemoveSubject( client, this.title ); 
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
	
	public void getAllSubjects(){
		ArrayList<String> subjects = new ArrayList<String>();
		try {
			subjects = chatServer.sendSubjects();
			int nbSubjects = subjects.size();
			int nbSubjectsByLine = 5;
			GridLayout gl = new GridLayout(nbSubjects/nbSubjectsByLine+1,nbSubjectsByLine); // row, column
			gl.setHgap(10);
			listButton.setLayout(gl);
			System.out.println( this.getClass().getName() + ": Il y a actuellement " + nbSubjects + " sujets: "); 
			for (String s : subjects){
				System.out.println(  this.getClass().getName() + ": \t - " + s );
				JPanel panelAssociate = new JPanel(new GridBagLayout());
				GridBagConstraints gbc2 = new GridBagConstraints();
				buttonSubject = new JButton(s);
				buttonSubject.addActionListener(new ButtonSubscribeListener(s));
				gbc2.gridx = 0;
				gbc2.gridy = 0;
				gbc2.gridwidth = GridBagConstraints.RELATIVE; // Single component so he is the last
				gbc2.gridheight = 1; // One cell in height
				gbc2.weightx = 1;
				gbc2.weighty = 0;
				gbc2.fill = GridBagConstraints.BOTH;
				gbc2.anchor = GridBagConstraints.CENTER; // Position
				panelAssociate.add(buttonSubject,gbc2);
				buttonRemoveSubject = new JButton(iconTrash);
				buttonRemoveSubject.addActionListener( new ButtonRemoveSubjectListener( s ) );
				gbc2.gridx = 1;
				gbc2.gridy = 0;
				gbc2.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
				gbc2.gridheight = 1; // One cell in height
				gbc2.weightx = 0;
				gbc2.weighty = 0;
				gbc2.fill = GridBagConstraints.NONE;
				gbc2.anchor = GridBagConstraints.CENTER; // Position
				panelAssociate.add(buttonRemoveSubject,gbc2	);
				listButton.add(panelAssociate);
				listButtonSubject.add(buttonSubject);
				listButtonSubject.add(buttonRemoveSubject);
			}
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
			gbc.gridheight = 1; // One cell in height
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.fill = GridBagConstraints.BOTH;
			gbc.anchor = GridBagConstraints.CENTER; // Position
			gbc.insets = new Insets(10, 10, 10, 10);
			panel.add(listButton,gbc);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
