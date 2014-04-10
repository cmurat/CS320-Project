package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {

	private int height;
	private int width;
	private int xPos;
	private int yPos;
	private String loginURL;

	public LoginPage(String loginURL) {
		this.loginURL = loginURL;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) (screenSize.getWidth() / 4);
		height = (int) screenSize.getHeight();
		yPos = 0;
		xPos = (int) (screenSize.getWidth() - width);
	}

	public void printLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocation(xPos, yPos);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);
		add(getLoginButton());
		setResizable(false);
		setVisible(true);
	}

	private JButton getLoginButton() {
		JButton loginButton = new JButton();
		//loginButton.setText("Login");
		loginButton.setBorderPainted(false);
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.WHITE);
		Image img = new ImageIcon("icon/loginIcon.png").getImage();
		img = img.getScaledInstance(90, 90,Image.SCALE_SMOOTH);
		loginButton.setIcon(new ImageIcon(img));
		loginButton.addActionListener(getLoginButtonListener());
		return loginButton;
	}

	private ActionListener getLoginButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					openWebPage();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		};
	}

	private void openWebPage() throws URISyntaxException {
		try {
			java.awt.Desktop.getDesktop().browse(new URL(loginURL).toURI());
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
