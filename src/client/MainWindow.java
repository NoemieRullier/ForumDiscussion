package client;


import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.ISubjectDiscussion;

public class MainWindow extends JFrame {

	private JButton button;
	private JPanel panel = new JPanel();

	public MainWindow(List<ISubjectDiscussion> listSubject) throws RemoteException {
		for (ISubjectDiscussion s : listSubject){
			button = new JButton(s.getTitle());
			panel.add(button);
		}
		this.setContentPane(panel);
/*		BoutonListener boutonListener =  new BoutonListener(bouton1);
		bouton1.addActionListener(boutonListener);
		boutonListener.start();
		boutonListener =  new BoutonListener(bouton2);
		bouton2.addActionListener(boutonListener);
		boutonListener.start();
		boutonListener =  new BoutonListener(bouton3);
		bouton3.addActionListener(boutonListener);
		boutonListener.start();
		boutonListener =  new BoutonListener(bouton4);
		bouton4.addActionListener(boutonListener);
		boutonListener.start();
		boutonListener =  new BoutonListener(bouton5);
		bouton5.addActionListener(boutonListener);
		boutonListener.start();*/

		this.setVisible(true);
		this.setSize(600, 70);
	}

}