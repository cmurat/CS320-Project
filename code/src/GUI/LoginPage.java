package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import DataRequester.Tweet;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {

	private GUI gui;
	private String loginURL;
	private JTextField pinInputField;

	public LoginPage(GUI gui, String loginURL) {
		this.gui = gui;
		this.loginURL = loginURL;
	}

	public void printLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	public void addLoginButton() {
		getContentPane().removeAll();
		JButton loginButton = new JButton();
		loginButton.setBorderPainted(false);
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.WHITE);
		loginButton.setContentAreaFilled(false);
		loginButton.setIcon(getImageIcon("icon/loginIcon.png"));
		loginButton.addActionListener(getLoginButtonListener());
		add(loginButton);
		getContentPane().repaint();
		getContentPane().validate();
	}

	private ImageIcon getImageIcon(String iconPath) {
		Image img = new ImageIcon(iconPath).getImage();
		img = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	private ActionListener getLoginButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				gui.loginButtonClicked();
				System.out.println("Login button is clicked!");
				printPinField();
			}
		};
	}
	
	public void printPinField() {
		getContentPane().removeAll();
		JPanel pinInputPanel = new JPanel();
		pinInputPanel.setLayout(new BorderLayout());
		pinInputField = new JTextField();
		pinInputPanel.add(pinInputField, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		JButton okButton = getOkButton();
		JButton backButton = getBackButton();
		buttonPanel.add(okButton, BorderLayout.WEST);
		buttonPanel.add(backButton, BorderLayout.EAST);
		pinInputPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(pinInputPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	private JButton getOkButton() {
		JButton okButton = new JButton("Ok");
		okButton.setBackground(Color.black);
		okButton.setFocusable(false);
		okButton.addActionListener(getOkButtonListener());
		return okButton;
	}

	private JButton getBackButton() {
		JButton backButton = new JButton("Back");
		backButton.setBackground(Color.black);
		backButton.setFocusable(false);
		backButton.addActionListener(getBackButtonListener());
		return backButton;
	}
	
	private ActionListener getBackButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.backToLoginButtonClicked();
			}
		};
	}

	private ActionListener getOkButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gui.pinEntered();
			}
		};
	}

	public String getPin() {
		return pinInputField.getText();
	}

}
