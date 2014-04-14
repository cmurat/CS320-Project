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

	private JButton getIconButton(String iconLocation, int size,
			ActionListener listener) {
		JButton button = new JButton();
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setIcon(getImageIcon(iconLocation, size));
		button.addActionListener(listener);
		return button;
	}

	private void calculateBounds() {
		setBounds(0, 0, gui.getWidth(), gui.getHeight() / 8);
	}

	private void addHomeButton(int dimension) {
		JButton homeButton = getIconButton("icon/home.png", dimension,
				getHomeButtonListener());
		buttonList.add(homeButton);
	}

	private ActionListener getHomeButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("\n Home Button button is clicked!");
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					gui.homeButtonClicked();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	private void addNotificationsButton(int dimension) {
		JButton notificationsButton = getIconButton("icon/mail2.png", dimension,
				getNotificationsButtonListener());
		buttonList.add(notificationsButton);
	}

	private ActionListener getNotificationsButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					setCursor(new Cursor(Cursor.WAIT_CURSOR));
					gui.mentionsButtonClicked();
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\nNotifications button is clicked!");
			}
		};
	}

	private void addMeButton(int dimension) {
		JButton meButton = getIconButton("icon/profile-icon.png", dimension,
				getMeButtonListener());
		buttonList.add(meButton);
	}

	private ActionListener getMeButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nMe button is clicked!");
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				gui.meButtonClicked();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};
	}

	private void addSearchButton(int dimension) {
		JButton searchButton = getIconButton("icon/search.png", dimension,
				getSearchButtonListener());
		buttonList.add(searchButton);
	}

	private ActionListener getSearchButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nSearch button is clicked!");
			}
		};
	}

	private void addDMessageButton(int dimension) {
		JButton dMessageButton = getIconButton("icon/mail3.png", dimension,
				getDMessageButtonListener());
		buttonList.add(dMessageButton);
	}

	private ActionListener getDMessageButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nDMessage button is clicked!");
			}
		};
	}

	private void addSettingsButton(int dimension) {
		JButton settingsButton = getIconButton("icon/settings.png", dimension,
				getSettingsButtonListener());
		buttonList.add(settingsButton);
	}

	private ActionListener getSettingsButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nSettings button is clicked!");
			}
		};
	}
}
