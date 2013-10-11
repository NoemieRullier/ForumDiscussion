package client;


import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.IServerForum;
import server.ISubjectDiscussion;

public class MainWindow extends JFrame {

	private JButton button;
	private BoutonListener buttonListener; 
	private JPanel panel = new JPanel();

	public MainWindow(List<ISubjectDiscussion> listSubject) throws RemoteException {
		for (ISubjectDiscussion s : listSubject){
			button = new JButton(s.getTitle());
			buttonListener = new BoutonListener( button, s ); 
			button.addActionListener( buttonListener ); 
			panel.add(button);
		}
		this.setContentPane(panel);

		this.setVisible(true);
		this.setSize(600, 70);
	}

}