package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import GUIListeners.SettingsListeners.ChangeProfilePictureButtonListener;
import GUIListeners.SettingsListeners.ChangeUsernameButtonListener;

@SuppressWarnings("serial")
public class SettingsScreen extends JPanel {

	private final int NUMBER_OF_SETTINGS = 2;
	private MainPanel mainPanel;

	public SettingsScreen(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		calculateBounds();

		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(NUMBER_OF_SETTINGS, 1));
		settingsPanel.setBorder(new LineBorder(Color.BLACK, 1, true));

		addChangeProfilePictureButton(settingsPanel);
		addChangeUsernameButton(settingsPanel);

		add(settingsPanel, BorderLayout.NORTH);
	}

	private void addChangeProfilePictureButton(JPanel settingsPanel) {
		JButton changeProfilePictureButton = createButton("Change Profile Picture");
		changeProfilePictureButton
				.addActionListener(new ChangeProfilePictureButtonListener(
						changeProfilePictureButton, mainPanel));
		settingsPanel.add(changeProfilePictureButton);
	}

	private void addChangeUsernameButton(JPanel settingsPanel) {
		JButton changeUsernameButton = createButton("Change User Name");
		changeUsernameButton
				.addActionListener(new ChangeUsernameButtonListener(
						changeUsernameButton, mainPanel));
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
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return button;
	}

	// TODO set these
	private void calculateBounds() {
		setBounds(mainPanel.getBounds().x, mainPanel.getBounds().y,
				mainPanel.getBounds().width, mainPanel.getBounds().height);
	}
}
