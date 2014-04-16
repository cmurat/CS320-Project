package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import GUI.listeners.changeImageButtonListener;
import GUI.listeners.changeUsernameButtonListener;
import GUI.listeners.logoutButtonListener;

@SuppressWarnings("serial")
public class SettingsScreen extends JPanel {
	private MainPanel mainPanel;
	
	public SettingsScreen(MainPanel mainPanel){
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		calculateBounds();
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(3, 1));
		settingsPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
		
		
		addChangeImageButton(settingsPanel);
		addChangeUsernameButton(settingsPanel);
		addLogoutButton(settingsPanel);
		
		add(settingsPanel, BorderLayout.NORTH);
		
	}

	private void addChangeImageButton(JPanel settingsPanel) {
		JButton changeImageButton = createButton("Change Profile Image");
		changeImageButton.addActionListener(new changeImageButtonListener(changeImageButton));
		settingsPanel.add(changeImageButton);
	}

	private void addChangeUsernameButton(JPanel settingsPanel) {
		JButton changeUsernameButton = createButton("Change User Name");
		changeUsernameButton.addActionListener(new changeUsernameButtonListener(changeUsernameButton));
		settingsPanel.add(changeUsernameButton);
	}

	private void addLogoutButton(JPanel settingsPanel) {
		JButton logoutButton = createButton("Logout");
		logoutButton.addActionListener(new logoutButtonListener(logoutButton));
		settingsPanel.add(logoutButton);
	}

	private JButton createButton(String title) {
		JButton button = new JButton(title);
		button.setBackground(Color.WHITE);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(true);
		button.setHorizontalAlignment(JButton.LEFT);
		return button;
	}


	//TODO set these
	private void calculateBounds() {
		setBounds(mainPanel.getBounds().x, mainPanel.getBounds().y,
				mainPanel.getBounds().width, mainPanel.getBounds().height);
	}
}
