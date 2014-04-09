package GUI;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
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
				try {
					openWebPage();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(loginButton);
		setResizable(false);
		setVisible(true);
	}

	private void openWebPage() throws URISyntaxException {
		try {
			java.awt.Desktop.getDesktop().browse(new URL(loginURL).toURI());
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
