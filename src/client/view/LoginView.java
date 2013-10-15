package client.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2986772578238274359L;
	
	private JTextField pseudoField = new JTextField();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel panel = new JPanel();
	private JButton verifyButton = new JButton();
	private ImageIcon verifyIcon = new ImageIcon("img/recycling_32.png");
	private JButton validateButton = new JButton("Valider");
	private ImageIcon validateIcon = new ImageIcon("img/badge_32.png");
	
	public LoginView(){
		panel.setLayout(new GridBagLayout());
		/* Title */
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.RELATIVE; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1.9;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 5);
		pseudoField.setActionCommand("Please choose a login");
		panel.add(pseudoField,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 5, 5, 10);
		verifyButton.setIcon(verifyIcon);
		panel.add(verifyButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 10, 5, 10);
		validateButton.setIcon(validateIcon);
		panel.add(validateButton, gbc);
		
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setContentPane(panel);
		this.setVisible(true);
	}

}
