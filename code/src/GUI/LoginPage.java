package GUI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;

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
		setSize(width, height);
		setLocation(xPos, yPos);
		JButton loginButton = new JButton();
		loginButton.setText("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openWebPage();
			}
		});
		add(loginButton);
		setResizable(false);
		setVisible(true);
	}

	private void openWebPage() {
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(loginURL));
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
