package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import DataRequester.DetailedAccount;
import GUI.listeners.ChangeUsernameButtonListener;


@SuppressWarnings("serial")
public class SettingsScreen extends JPanel {
	
	private final int NUMBER_OF_SETTINGS = 2;
	private MainPanel mainPanel;
	private DetailedAccount account;
	
	public SettingsScreen(MainPanel mainPanel, DetailedAccount account){
		this.mainPanel = mainPanel;
		this.account = account;
		setLayout(new BorderLayout());
		calculateBounds();
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(NUMBER_OF_SETTINGS, 1));
		settingsPanel.setBorder(new LineBorder(Color.BLACK, 1, true));
		
		addChangeImageButton(settingsPanel);
		addChangeUsernameButton(settingsPanel);
		
		add(settingsPanel, BorderLayout.NORTH);
	}

	private void addChangeImageButton(JPanel settingsPanel) {
		JButton changeImageButton = createButton("Change Profile Image");
		//changeImageButton.addActionListener(new ChangeImageButtonListener(changeImageButton));
		settingsPanel.add(changeImageButton);
	}

	private void addChangeUsernameButton(JPanel settingsPanel) {
		JButton changeUsernameButton = createButton("Change User Name");
		//changeUsernameButton.addActionListener(new ChangeUsernameButtonListener(account.getUserID(), changeUsernameButton, mainPanel));
		settingsPanel.add(changeUsernameButton);
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
