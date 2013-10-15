

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;

import server.IServerForum;
import server.ISubjectDiscussion;

// extends Thread
public class ButtonSubjectListener implements ActionListener {

	// Add comments 
	//private static MonitorExchanger<String> monitor = new MonitorExchanger();
	private JButton button;
	private ISubjectDiscussion subject;
	private IDisplayClient client; 
	
	public ButtonSubjectListener( JButton b, ISubjectDiscussion s ){
		this.button = b;
		this.subject = s; 
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		System.out.println( "Appui sur " + this.button.getText() ); 
		this.client = new DisplayClient( this.button.getText(), subject ); 
		System.out.println( 
			"demande d'inscription de " + this.client + 
			" au sujet " + this.subject + "... "
		); 
		/*try {
			//this.subject.registration( client ); 
			button.setEnabled( false ); 
		} catch( RemoteException e1 ) {
			System.out.println( "Erreur : impossible d'inscrire le client au sujet" ); 
//			e1.printStackTrace();
		} */
	}
	
//	// Lancee des la creation du Listener a l'aide de la methode start
//	public void run(){
//		while(true){
//			attendre();
//			button.setEnabled(false);
//		//	button.setText(monitor.echange(button.getText()));;
//			button.setEnabled(true);
//		}
//	}
//	
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		reprendre();
//	}
//	
//	public synchronized void attendre(){
//		try {
//			wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public synchronized void reprendre(){
//		notify();
//	}
//	
}