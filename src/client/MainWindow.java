package client;


import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.ISubjectDiscussion;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8596715838932975027L;

	private JButton buttonSubject;
	private ButtonSubjectListener buttonListener; 
	private JPanel panel = new JPanel();

	public MainWindow(List<ISubjectDiscussion> listSubject) throws RemoteException {
		for (ISubjectDiscussion s : listSubject){
			buttonSubject = new JButton(s.getTitle());
			buttonListener = new ButtonSubjectListener(buttonSubject, s); 
			buttonSubject.addActionListener(buttonListener); 
			panel.add(buttonSubject);
		}
		this.setContentPane(panel);

		this.setVisible(true);
		this.setSize(600, 70);
	}

}