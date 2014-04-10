package GUI;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {

	private GUI gui;
	private String loginURL;

	public LoginPage(GUI gui, String loginURL) {
		this.gui = gui;
		this.loginURL = loginURL;
	}

	public void printLoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(getBounRectangle());
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.WHITE);
		add(getLoginButton());
		setResizable(false);
		setVisible(true);
	}

	private Rectangle getBounRectangle() {
		return new Rectangle(gui.getBounds()[0], gui.getBounds()[1],
				gui.getBounds()[2], gui.getBounds()[3]);
	}

	private JButton getLoginButton() {
		JButton loginButton = new JButton();
		loginButton.setBorderPainted(false);
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.WHITE);
		loginButton.setContentAreaFilled(false);
		loginButton.setIcon(getImageIcon("icon/loginIcon.png"));
		loginButton.addActionListener(getLoginButtonListener());
		return loginButton;
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
//				openWebPage();
				gui.loginButtonClicked();				
			}
		};
	}

	private void openWebPage() {
		try {
			Desktop.getDesktop().browse(new URL(loginURL).toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
