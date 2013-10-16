package client.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginView extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2986772578238274359L;
	
	private JLabel indicationLabel = new JLabel("Choose a pseudo");
	private JLabel messageLabel = new JLabel();
	private JTextField pseudoField = new JTextField();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel panel = new JPanel();
	/*private JButton verifyButton = new JButton();
	private ImageIcon verifyIcon = new ImageIcon("img/recycling_32.png");*/
	private JButton validateButton = new JButton("Valider");
	private ImageIcon validateIcon = new ImageIcon("img/badge_32.png");
	private Color redColor = new Color(255, 55, 63); 

	private MainWindowClient parent;
	
	public LoginView(JFrame parent, boolean modal){
		super(parent, modal);
		this.parent = (MainWindowClient) parent;
		panel.setLayout(new GridBagLayout());
		/* Label */
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(10, 10, 5, 10); // Haut, gauche, bas , droite
		panel.add(indicationLabel,gbc);
		/* Pseudo */
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.RELATIVE; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1.9;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(5, 10, 5, 10);
		panel.add(pseudoField,gbc);
		/*gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 5, 5, 10);
		verifyButton.setIcon(verifyIcon);
		panel.add(verifyButton, gbc);*/
		/* Message error */
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER; // Single component so he is the last
		gbc.gridheight = 1; // One cell in height
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER; // Position
		gbc.insets = new Insets(5, 10, 5, 10);
		messageLabel.setForeground(redColor);
		panel.add(messageLabel,gbc);
		/* Validate button */
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 10, 10, 10);
		validateButton.setIcon(validateIcon);
		validateButton.addActionListener(new ValidateButtonListener(this));
		panel.add(validateButton, gbc);
		
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	private class ValidateButtonListener implements ActionListener {
		
		private LoginView source;
		
		public ValidateButtonListener(LoginView source) {
			this.source = source;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Regarder si pseudo dispo, si oui on l'ajoute à la liste et on ferme la modale, si non on revient sur cette fenêtre et on affiche un message pour d
			// dire que c'est pas OK
			if (parent.getController().verifyAvailableLogin(pseudoField.getText())){
				parent.setPseudo(pseudoField.getText());
				source.dispose();
			}
			else {
				messageLabel.setText("Pseudo " + pseudoField.getText() + " already use. Please choose another");
				pseudoField.setText("");
			}
		}
		
	}

}
