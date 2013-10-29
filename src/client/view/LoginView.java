package client.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


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
	private JButton validateButton = new JButton("Valider");
	private ImageIcon validateIcon = new ImageIcon("img/badge_32.png");
	private Color redColor = new Color(255, 55, 63);

	private Action sendLogin = new ActionSend(this);

	private class ActionSend extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = -185704272279549730L;
		private LoginView source ;

		public ActionSend(LoginView source){
			this.source = source;
		}

		public void actionPerformed(ActionEvent e) {
			sendLogin(source);
		}
	};

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
		pseudoField.addKeyListener(new PseudoKeyListener());
		pseudoField.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "sendLogin");
		pseudoField.getActionMap().put("sendLogin", sendLogin);
		panel.add(pseudoField,gbc);
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
			sendLogin(source);
		}

	}

	private class PseudoKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent k) {
		}

		@Override
		public void keyReleased(KeyEvent k) {
		}

		@Override
		public void keyTyped(KeyEvent k) {
			if (k.getKeyChar()==' ')
			{ 
				k.consume();
			}
		}

	}

	/**
	 * Send the login source to the server
	 * @param source
	 */
	private void sendLogin(LoginView source){
		boolean dispo = false;
		try {
			dispo = parent.getController().verifyAvailableLogin(pseudoField.getText()); 
		} catch (RemoteException e1) {
			e1.printStackTrace();
			displayError("Impossible d'acceder au server pour verifier la disponibilite du pseudo. \nVeuillez recommencer ulterieurement");
		}
		if (dispo){
			parent.setPseudo(pseudoField.getText());
			source.dispose(); // Close windows
		}
		else {
			messageLabel.setText("Pseudo " + pseudoField.getText() + " already use. Please choose another");
			pseudoField.setText("");
		}
	}

	/**
	 * Display a JDialog with the error
	 * @param error
	 */
	public void displayError(String error){
		JOptionPane.showMessageDialog(null, error, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
