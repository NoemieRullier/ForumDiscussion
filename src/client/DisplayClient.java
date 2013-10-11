package client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.IServerForum;
import server.ISubjectDiscussion;


public class DisplayClient extends JFrame implements IDisplayClient{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JLabel title;
	private ISubjectDiscussion sujet;
	private JTextField discussionArea = new JTextField();
	private JTextField newMessage = new JTextField();
	private JButton send = new JButton("Envoyer");
	
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
	
	
	
	// TODO: Redimensionner champ pour nouveau msg
	public DisplayClient(String title, ISubjectDiscussion sujet){
		this.sujet = sujet; 
		this.title = new JLabel(title);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(this.title);
		panel.add(discussionArea);
		panel.add(newMessage);
		panel.add(send);
		
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(400, 600);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener( 
			new SubjectWindowAdapter( this.sujet, this )
		);
		
	}
	
	// TODO Add sender : msg
	@Override
	public void display(String msg) throws RemoteException {
		discussionArea.setText(discussionArea.getText() + msg); 
	}
	
}
