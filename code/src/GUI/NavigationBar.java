package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NavigationBar extends JPanel {

	private GUI gui;
	private ArrayList<JButton> buttonList;

	public NavigationBar(GUI gui) {
		this.gui = gui;
		buttonList = new ArrayList<JButton>();
		calculateBounds();
		setOpaque(true);
		setLayout(new FlowLayout());
		addButtonToButtonList();
		printbuttonList();
	}

	private void printbuttonList() {
		int widthOfButtons = getWidth() / buttonList.size();
		for (int buttonIndex = 0; buttonIndex < buttonList.size(); ++buttonIndex) {
			JButton button = buttonList.get(buttonIndex);
			int xPos = getBounds().x + buttonIndex * widthOfButtons;
			int yPos = getBounds().y;
			int width = widthOfButtons;
			int height = getBounds().height;
			button.setBounds(xPos, yPos, width, height);
			add(button);
		}
	}

	private void addButtonToButtonList() {
		addHomeButton();
		addNotificationsButton();
		addMeButton();
		addSearchButton();
		addDMessageButton();
		addSettingsButton();
	}

	private void calculateBounds() {
		setBounds(0, 0,
				gui.getWidth(), gui.getHeight() / 10);
	}

	private void addHomeButton() {
		JButton homeButton = new JButton("^");
		homeButton.addActionListener(getHomeButtonListener());
		buttonList.add(homeButton);
	}

	private ActionListener getHomeButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nHome button is clicked!");
			}
		};
	}

	private void addNotificationsButton() {
		JButton notificationsButton = new JButton("#");
		notificationsButton.addActionListener(getNotificationsButtonListener());
		buttonList.add(notificationsButton);
	}

	private ActionListener getNotificationsButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("\nNotifications button is clicked!");
			}
		};
	}

	private void addMeButton() {
		JButton meButton = new JButton("Me");
		meButton.addActionListener(getMeButtonListener());
		buttonList.add(meButton);
	}

	private ActionListener getMeButtonListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("\nMe button is clicked!");
				gui.meButtonClicked();
			}
		};
	}

	private void addSearchButton() {
		JButton searchButton = new JButton("?");
		searchButton.addActionListener(getSearchButtonListener());
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

	private void addDMessageButton() {
		JButton dMessageButton = new JButton("DM");
		dMessageButton.addActionListener(getDMessageButtonListener());
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

	private void addSettingsButton() {
		JButton settingsButton = new JButton("S");
		settingsButton.addActionListener(getSettingsButtonListener());
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
