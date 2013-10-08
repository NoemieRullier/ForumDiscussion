package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BoutonListener extends Thread implements ActionListener {

	//private static MonitorExchanger<String> monitor = new MonitorExchanger();
	private JButton button;
	
	public BoutonListener(JButton b){
		button = b;
	}
	
	// Lanc�e d�s la cr�ation du Listener � l'aide de la m�thode start
	public void run(){
		while(true){
			attendre();
			button.setEnabled(false);
		//	button.setText(monitor.echange(button.getText()));;
			button.setEnabled(true);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		reprendre();
	}
	
	public synchronized void attendre(){
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void reprendre(){
		notify();
	}
	
}