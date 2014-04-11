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
	private String loginURL;
	private JTextField pinInputField;

	public LoginPage(GUI gui, String loginURL) {
		this.gui = gui;
		this.loginURL = loginURL;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Twitter Desktop Application");
		setBounds(getBounRectangle());
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setVisible(true);
	}

	public void printLoginPage() {
		addLoginPanel();
	}

	private Rectangle getBounRectangle() {
		return new Rectangle(gui.getBounds()[0], gui.getBounds()[1],
				gui.getBounds()[2], gui.getBounds()[3]);
	}

	public void addLoginPanel() {
		getContentPane().removeAll();
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		JLabel greetingNote = new JLabel("To Enter Twitter Please Click on the Icon");
		greetingNote.setHorizontalAlignment(JLabel.CENTER);
		greetingNote.setVerticalAlignment(JLabel.TOP);
		JButton loginButton = getIconButton("icon/loginIcon.png", getWidth()/5, getLoginButtonListener());
		loginPanel.setBackground(Color.white);
		loginPanel.add(greetingNote,BorderLayout.NORTH);
		loginPanel.add(loginButton,BorderLayout.SOUTH);
		add(loginPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	private ImageIcon getImageIcon(String iconPath,int dimension) {
		Image img = new ImageIcon(iconPath).getImage();
		img = img.getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
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
		//pinInputPanel created
		JPanel pinInputPanel = new JPanel();
		pinInputPanel.setLayout(new BorderLayout());
		pinInputPanel.setBackground(Color.white);
		//text field created
		pinInputField = new JTextField();
		pinInputField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));;
		pinInputField.setHorizontalAlignment(JTextField.CENTER);
		pinInputPanel.add(pinInputField, BorderLayout.CENTER);
		//ButtonPanel created
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBackground(Color.white);
		//Ok and Back buttons are created
		JButton okButton = getIconButton("icon/ok.png",getWidth()/5,getOkButtonListener());
		JButton backButton = getIconButton("icon/back.png",getWidth()/5,getBackButtonListener());
		//pinLabel created
		JLabel pinLabel = new JLabel("Please enter PIN");
		pinLabel.setHorizontalAlignment(JLabel.CENTER);

		pinLabel.setVerticalAlignment(JLabel.TOP);
		pinInputPanel.add(pinLabel,BorderLayout.NORTH);
		buttonPanel.add(okButton, BorderLayout.WEST);
		buttonPanel.add(backButton, BorderLayout.EAST);
		pinInputPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(pinInputPanel);
		getContentPane().repaint();
		getContentPane().validate();
	}

	private JButton getIconButton(String iconLocation, int size, ActionListener listener){
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setBackground(Color.white);
		button.setContentAreaFilled(false);
		button.setIcon(getImageIcon(iconLocation,size));
		button.addActionListener(listener);
		return button;
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
