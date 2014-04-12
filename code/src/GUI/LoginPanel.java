package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		if (loginPanel == null)
			createLoginPanel();
		add(loginPanel);
	}

	private void createLoginPanel() {
		loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		loginPanel.setBackground(Color.white);
		JButton loginButton = getIconButton("icon/loginIcon.png",
				getWidth() / 5, getLoginButtonListener());
		loginPanel.add(getGreetingNote(), BorderLayout.NORTH);
		loginPanel.add(loginButton, BorderLayout.SOUTH);
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
		pinInputField.addKeyListener(getEnterKeyAdapter());
		pinInputPanel.add(pinInputField, BorderLayout.CENTER);
	}

	private KeyAdapter getEnterKeyAdapter() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					pinEntered();
			}
		};
	}
	
	private void pinEntered() {
		pinInputField.setEnabled(false);
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		gui.pinEntered();
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
				pinEntered();
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
