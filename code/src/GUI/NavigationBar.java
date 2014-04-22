package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.listeners.DMessageListener;
import GUI.listeners.HomeButtonListener;
import GUI.listeners.LogoutButtonListener;
import GUI.listeners.MeButtonListener;
import GUI.listeners.NotificationsButtonListener;
import GUI.listeners.SearchButtonListener;
import GUI.listeners.SettingsButtonListener;

@SuppressWarnings("serial")
public class NavigationBar extends JPanel {

	private GUI gui;
	private ArrayList<JButton> buttonList;

	public NavigationBar(GUI gui) {
		this.gui = gui;
		buttonList = new ArrayList<JButton>();
		calculateBounds();
		setOpaque(true);
		setLayout(new GridLayout(1, 6));
		setBackground(Color.WHITE);
		addButtonToButtonList();
		printbuttonList();
	}

	private void printbuttonList() {
		for (JButton button : buttonList) {
			add(button);
		}
	}

	private void addButtonToButtonList() {
		int dimension = getWidth() / 8;
		addHomeButton(dimension);
		addNotificationsButton(dimension);
		addMeButton(dimension);
		addSearchButton(dimension);
		addDMessageButton(dimension);
		addSettingsButton(dimension);
		addLogOutButton(dimension);
	}

	private ImageIcon getImageIcon(String iconPath, int dimension) {
		Image img = new ImageIcon(iconPath).getImage();
		img = img.getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	private JButton getIconButton(String iconLocation, int size) {
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setIcon(getImageIcon(iconLocation, size));
		return button;
	}

	private void calculateBounds() {
		setBounds(0, gui.getY(), gui.getWidth(), gui.getHeight() / 10);
	}

	private void addHomeButton(int dimension) {

		JButton homeButton = getIconButton("icon/home.png", dimension);
		homeButton.setToolTipText("Home");
		homeButton.addActionListener(new HomeButtonListener(homeButton, gui));
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(homeButton);
	}

	private void addNotificationsButton(int dimension) {
		JButton notificationsButton = getIconButton("icon/notification2.png", dimension);
		notificationsButton.setToolTipText("Notifications");
		notificationsButton.addActionListener(
				new NotificationsButtonListener(notificationsButton, gui));
		notificationsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(notificationsButton);
	}

	private void addMeButton(int dimension) {
		JButton meButton = getIconButton("icon/profile-icon.png", dimension);
		meButton.setToolTipText("My Profile");
		meButton.addActionListener(new MeButtonListener(meButton, gui));
		meButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(meButton);
	}

	private void addSearchButton(int dimension) {
		JButton searchButton = getIconButton("icon/search.png", dimension);
		searchButton.setToolTipText("Search");
		searchButton.addActionListener(new SearchButtonListener(searchButton, gui));
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(searchButton);
	}

	private void addDMessageButton(int dimension) {
		JButton dMessageButton = getIconButton("icon/messages.png", dimension);
		dMessageButton.setToolTipText("Direct Message");
		dMessageButton.addActionListener(new DMessageListener(dMessageButton, gui));
		dMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(dMessageButton);
	}

	private void addSettingsButton(int dimension) {
		JButton settingsButton = getIconButton("icon/settings.png", dimension);
		settingsButton.setToolTipText("Settings");
		settingsButton.addActionListener(
				new SettingsButtonListener(settingsButton, gui));
		settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(settingsButton);
	}

	private void addLogOutButton(int dimension) {
		JButton logOutButton = getIconButton("icon/logOut2.jpg", dimension);
		logOutButton.setToolTipText("LogOut");
		logOutButton.addActionListener(new LogoutButtonListener(logOutButton, gui));
		logOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(logOutButton);
	}
}
