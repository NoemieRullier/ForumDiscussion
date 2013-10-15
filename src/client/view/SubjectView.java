package client.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.ISubjectDiscussion;
import client.controller.IClientController;
import client.model.IClient;


public class SubjectView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 131464122541584573L;
	
	
	private JPanel panel = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel labelTitle = new JLabel();
	private JTextArea discussionArea = new JTextArea();
	private JScrollPane discussionPane = new JScrollPane(discussionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextArea newMessageArea = new JTextArea();
	private JScrollPane newMessagePane = new JScrollPane(newMessageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private ImageIcon icon = new ImageIcon("img/email_32.png");
	private JButton sendButton = new JButton("Envoyer",icon);

	private IClientController controller;
	private IClient client;
	private ISubjectDiscussion subject; 
	private String title; 

	public SubjectView( ISubjectDiscussion subject, IClientController controller, IClient client ){
		this.subject = subject; 
		this.client = client;
		
		try {
			this.title = subject.getTitle();
		} catch( RemoteException e ) {
			this.title = ""; 
		} 
		this.controller = controller; 

		this.labelTitle.setText( title );

		/************ IHM ************/
		panel.setLayout(new GridBagLayout());
		/* Title */
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		panel.add(labelTitle,gbc);
		/* Discussion Area */
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 10, 5, 10);
		discussionArea.setEditable(false);
		discussionArea.setLineWrap(true);
		panel.add(discussionPane,gbc);
		/* New Message Area */
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0.2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 10, 5, 10);
		newMessageArea.setLineWrap(true);
		//newMessageArea.requestFocus();
		panel.add(newMessagePane,gbc);
		/* Send Button */
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 10, 10, 10);
		sendButton.addActionListener( new ButtonSendListener() );
		panel.add(sendButton,gbc);

		this.setTitle( "Sujet: " + title );
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setContentPane(panel);
		this.setVisible(true);
		newMessageArea.requestFocus();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener( new SubjectWindowAdapter() );
	}

	public void displayMessage( String msg ) throws RemoteException {
		discussionArea.append( client.getLogin() + " : " + msg + "\n" ); 
	}

	
	private class ButtonSendListener implements ActionListener {

		@Override
		public void actionPerformed( ActionEvent e ) {
			try {
				controller.pleaseSendMessage( subject, newMessageArea.getText() );
			} catch( RemoteException e1 ) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			newMessageArea.setText( "" ); 
		}
	}

	private class SubjectWindowAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getSource();
			int result = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Exit Application", JOptionPane.YES_NO_OPTION );
			if (result == JOptionPane.YES_OPTION) {
				try {
					controller.pleaseUnsubscribe(subject, client);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					// reactiver button
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		}
	}
}
