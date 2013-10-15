package client.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;

public class LoginView extends JWindow {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2986772578238274359L;
	
	private JTextField pseudoField = new JTextField();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel panel = new JPanel();
	
	public LoginView(){
		panel.setLayout(new GridBagLayout());
		/* Title */
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10);
		//pseudoField.setActionCommand("Please choose a login");
		panel.add(pseudoField,gbc);
	//	this.setTitle( "Login");
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		//this.setResizable(true);
		this.setContentPane(panel);
		this.setVisible(true);
	}

}
