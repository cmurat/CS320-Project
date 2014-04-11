package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {

	private GUI gui;
	private JTextField pinInputField;

	public LoginPage(GUI gui) {
		this.gui = gui;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Twitter Desktop Application");
		setBounds(getBounRectangle());
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setVisible(true);
	}

	private Rectangle getBounRectangle() {
		return new Rectangle(gui.getBounds()[0], gui.getBounds()[1],
				gui.getBounds()[2], gui.getBounds()[3]);
	}

	public void printLoginPage() {
		addLoginPanel();
	}

	private void addLoginPanel() {
		getContentPane().removeAll();
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		JButton loginButton = getIconButton("icon/loginIcon.png",
				getWidth() / 5, getLoginButtonListener());
		loginPanel.setBackground(Color.white);
		loginPanel.add(getGreetingNote(), BorderLayout.NORTH);
		loginPanel.add(loginButton, BorderLayout.SOUTH);
		add(loginPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	private ActionListener getLoginButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.loginButtonClicked();
				printPINField();
			}
		};
	}

	private JButton getIconButton(String iconLocation, int size,
			ActionListener listener) {
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setBackground(Color.white);
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

	public void printPINField() {
		getContentPane().removeAll();
		JPanel pinInputPanel = createWhiteBorderPanel();
		addPINLabelTo(pinInputPanel);
		addPINInputFieldTo(pinInputPanel);
		addButtonPanelTo(pinInputPanel);
		add(pinInputPanel);
		getContentPane().repaint();
		getContentPane().validate();
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
