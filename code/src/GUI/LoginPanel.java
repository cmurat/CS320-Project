package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	private GUI gui;
	private JPanel loginPanel;
	private JPanel PINInputPanel;
	private JTextField pinInputField;

	public LoginPanel(GUI gui) {
		this.gui = gui;
		setBounds(gui.getBounds());
		setOpaque(false);
	}

	public void printLoginPanel() {
		removeAll();
		loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		JButton loginButton = getIconButton("icon/loginIcon.png",
				getWidth() / 5, getLoginButtonListener());
		loginPanel.setBackground(Color.white);
		loginPanel.add(getGreetingNote(), BorderLayout.NORTH);
		loginPanel.add(loginButton, BorderLayout.SOUTH);
		add(loginPanel);
	}

	private ActionListener getLoginButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.loginButtonClicked();
				printPINInputPanel();
			}
		};
	}

	private JButton getIconButton(String iconLocation, int size,
			ActionListener listener) {
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setIcon(getImageIcon(iconLocation, size));
		button.addActionListener(listener);
		return button;
	}

	private ImageIcon getImageIcon(String iconPath, int dimension) {
		Image img = new ImageIcon(iconPath).getImage();
		img = img.getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	private JLabel getGreetingNote() {
		JLabel greetingNote = new JLabel(
				"To Enter Twitter Please Click on the Icon");
		greetingNote.setHorizontalAlignment(JLabel.CENTER);
		greetingNote.setVerticalAlignment(JLabel.TOP);
		return greetingNote;
	}

	public void printPINInputPanel() {
		removeAll();
		PINInputPanel = createWhiteBorderPanel();
		addPINLabelTo(PINInputPanel);
		addPINInputFieldTo(PINInputPanel);
		addButtonPanelTo(PINInputPanel);
		add(PINInputPanel);
		validate();
	}

	private JPanel createWhiteBorderPanel() {
		JPanel pinInputPanel = new JPanel();
		pinInputPanel.setLayout(new BorderLayout());
		pinInputPanel.setBackground(Color.white);
		return pinInputPanel;
	}

	private void addPINLabelTo(JPanel pinInputPanel) {
		JLabel pinLabel = new JLabel("Please enter PIN");
		pinLabel.setHorizontalAlignment(JLabel.CENTER);
		pinLabel.setVerticalAlignment(JLabel.TOP);
		pinInputPanel.add(pinLabel, BorderLayout.NORTH);
	}

	private void addPINInputFieldTo(JPanel pinInputPanel) {
		pinInputField = new JTextField();
		pinInputField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		pinInputField.setHorizontalAlignment(JTextField.CENTER);
		pinInputPanel.add(pinInputField, BorderLayout.CENTER);
	}

	private void addButtonPanelTo(JPanel pinInputPanel) {
		JPanel buttonPanel = createWhiteBorderPanel();
		addOkButtonTo(buttonPanel);
		addBackButtonTo(buttonPanel);
		pinInputPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void addOkButtonTo(JPanel buttonPanel) {
		JButton okButton = getIconButton("icon/ok.png", getWidth() / 5,
				getOkButtonListener());
		buttonPanel.add(okButton, BorderLayout.WEST);
	}

	private ActionListener getOkButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gui.pinEntered();
			}
		};
	}

	private void addBackButtonTo(JPanel buttonPanel) {
		JButton backButton = getIconButton("icon/back.png", getWidth() / 5,
				getBackButtonListener());
		buttonPanel.add(backButton, BorderLayout.EAST);
	}

	private ActionListener getBackButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.backToLoginButtonClicked();
			}
		};
	}
	
	public String getPIN() {
		return pinInputField.getText();
	}

}
