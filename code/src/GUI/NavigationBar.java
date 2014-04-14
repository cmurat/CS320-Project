package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import twitter4j.TwitterException;

@SuppressWarnings("serial")
public class NavigationBar extends JPanel {

	private GUI gui;
	private ArrayList<JButton> buttonList;

	public NavigationBar(GUI gui) {
		this.gui = gui;
		buttonList = new ArrayList<JButton>();
		calculateBounds();
		setOpaque(true);
		setLayout(new GridLayout(1,6));
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
		int dimension = getWidth()/8;
		addHomeButton(dimension);
		addNotificationsButton(dimension);
		addMeButton(dimension);
		addSearchButton(dimension);
		addDMessageButton(dimension);
		addSettingsButton(dimension);
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
		setBounds(0, 0, gui.getWidth(), gui.getHeight() / 8);
	}

	private void addHomeButton(int dimension) {

		JButton homeButton = getIconButton("icon/home.png", dimension);
		ActionListener listen = getHomeButtonListener(homeButton);
		homeButton.addActionListener(listen);
		homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(homeButton);
	}

	private ActionListener getHomeButtonListener(final JButton homeButton) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\n Home Button button is clicked!");
				homeButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				gui.homeButtonClicked();
				homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		};
	}

	private void addNotificationsButton(int dimension) {
		JButton notificationsButton = getIconButton("icon/mail2.png", dimension);
		ActionListener listen = getNotificationsButtonListener(notificationsButton);
		notificationsButton.addActionListener(listen);
		notificationsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(notificationsButton);
	}

	private ActionListener getNotificationsButtonListener(final JButton notificationsButton) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				notificationsButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				gui.mentionsButtonClicked();
				notificationsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				System.out.println("\nNotifications button is clicked!");
			}
		};
	}

	private void addMeButton(int dimension) {
		JButton meButton = getIconButton("icon/profile-icon.png", dimension);
		ActionListener listen = getMeButtonListener(meButton);
		meButton.addActionListener(listen);
		meButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(meButton);
	}

	private ActionListener getMeButtonListener(final JButton meButton) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nMe button is clicked!");
				meButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				gui.meButtonClicked();
				meButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		};
	}

	private void addSearchButton(int dimension) {
		JButton searchButton = getIconButton("icon/search.png", dimension);
		ActionListener listen = getSearchButtonListener(searchButton);
		searchButton.addActionListener(listen);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(searchButton);
	}

	private ActionListener getSearchButtonListener(final JButton searchButton) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				System.out.println("\nSearch button is clicked!");
				searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		};
	}

	private void addDMessageButton(int dimension) {
		JButton dMessageButton = getIconButton("icon/mail3.png", dimension);
		ActionListener listen = getDMessageButtonListener(dMessageButton);
		dMessageButton.addActionListener(listen);
		dMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(dMessageButton);
	}

	private ActionListener getDMessageButtonListener(final JButton dMessageButton) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dMessageButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				System.out.println("\nDMessage button is clicked!");
				dMessageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));			
			}
		};
	}

	private void addSettingsButton(int dimension) {
		JButton settingsButton = getIconButton("icon/settings.png", dimension);
		ActionListener listen = getSettingsButtonListener(settingsButton);
		settingsButton.addActionListener(listen);
		settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonList.add(settingsButton);
	}

	private ActionListener getSettingsButtonListener(final JButton settingsButton) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				settingsButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				System.out.println("\nSettings button is clicked!");
				settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			}
		};
	}
}
