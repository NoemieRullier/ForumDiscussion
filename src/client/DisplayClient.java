package client;

import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DisplayClient extends JFrame implements IDisplayClient{

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JLabel title;
	private JTextField discussionArea = new JTextField();
	private JTextField newMessage = new JTextField();
	private JButton send = new JButton("Envoyer");

	// TODO: Redimensionner champ pour nouveau msg
	public DisplayClient(String title){
		this.title = new JLabel(title);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(this.title);
		panel.add(discussionArea);
		panel.add(newMessage);
		panel.add(send);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setSize(400, 600);
	}
	
	// TODO Add sender : msg
	@Override
	public void display(String msg) throws RemoteException {
		discussionArea.setText(discussionArea.getText() + msg); 
	}

}
