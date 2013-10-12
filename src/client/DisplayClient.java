package client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JTextField;

import server.ISubjectDiscussion;


public class DisplayClient extends JFrame implements IDisplayClient{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5527962306243213784L;


	private JPanel panel = new JPanel();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel labelTitle = new JLabel();
	private JTextArea discussionArea = new JTextArea();
	private JScrollPane discussionPane = new JScrollPane(discussionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextField newMessageArea = new JTextField();
	ImageIcon icon = new ImageIcon("img/email_32.png");
	private JButton sendButton = new JButton("Envoyer",icon);

	private ISubjectDiscussion subject;

	private class SubjectWindowAdapter extends WindowAdapter {
		private ISubjectDiscussion sujet;
		private IDisplayClient client; 
		public SubjectWindowAdapter( ISubjectDiscussion sujet, IDisplayClient client ) {
			this.sujet = sujet; 
			this.client = client; 
		}
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame)e.getSource();

			int result = JOptionPane.showConfirmDialog(
					frame,
					"Are you sure?",
					"Exit Application",
					JOptionPane.YES_NO_OPTION
					);

			if (result == JOptionPane.YES_OPTION) {
				// recuperer sujet 
				try {
					System.out.println( "recuperer sujet " + this.sujet.getTitle() );
					// desinscrire du sujet 
					this.sujet.desinscription( client );
				} catch( RemoteException e1 ) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				// reactiver button 
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		}
	}



	// TODO: Redimensionner champ pour nouveau msg + mettre les multilignes
	public DisplayClient(String title, ISubjectDiscussion subject){
		this.subject = subject; 
		this.labelTitle.setText(title);

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
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 10, 5, 10);
		discussionArea.setEditable(false);
		panel.add(discussionPane,gbc);
		/* New Message Area */
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 10, 5, 10);
		panel.add(newMessageArea,gbc);
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
		panel.add(sendButton,gbc);


		this.setTitle("Sujet: "+title);
		this.setSize(400, 600);
		this.setLocationRelativeTo(null); // TODO: Centrer la fenêtre par rapport au parent par la suite
		this.setResizable(true);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener( 
				new SubjectWindowAdapter( this.subject, this )
				);

	}

	// TODO Add sender : msg
	@Override
	public void display(String msg) throws RemoteException {
		discussionArea.setText(discussionArea.getText() + msg); 
	}

}
