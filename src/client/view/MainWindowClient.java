/**
 * 
 */
package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.ISubjectDiscussion;
import client.ButtonSubjectListener;
import client.controller.IClientController;
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
	private JPanel panel = new JPanel(); 

	public MainWindowClient( List<ISubjectDiscussion> listSubject, IClientController controller, IClient client ) throws RemoteException {
		
		this.controller = controller; 
		this.client = client; 
		
		for ( ISubjectDiscussion s : listSubject ){
			buttonSubject = new JButton( s.getTitle() );
			
			buttonSubject.addActionListener( new ButtonSubscribeListener( s ) ); 
			
			panel.add(buttonSubject);
		}
		this.setContentPane(panel);

		this.setVisible(true);
		this.setSize(600, 70);
	}
	
	
	
	public class ButtonSubscribeListener implements ActionListener {
		
		private ISubjectDiscussion subject; 
		
		public ButtonSubscribeListener( ISubjectDiscussion subject ) {
			this.subject = subject; 
		}
		
		
		@Override
		public void actionPerformed( ActionEvent e ) {
			try {
				controller.pleaseSubscribe( subject, client ); 
			} catch( RemoteException e1 ) {
				// TODO Auto-generated catch block
				System.out.println( "le main window client marche paaaaas T_T" );
				e1.printStackTrace();
			} 
		}
		
		
		
	}
	
}
