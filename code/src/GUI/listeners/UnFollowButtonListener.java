package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import GUI.MainPanel;

public class UnFollowButtonListener implements ActionListener {
	private long userID;
	private MainPanel mainPanel;
	private JButton button;

	public UnFollowButtonListener(long userID, MainPanel mainPanel, JButton button) {
		this.userID = userID;
		this.mainPanel = mainPanel;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\nUnFollow button clicked");
		mainPanel.unFollowButtonClicled(userID);
		button.setText("Follow");
		button.removeActionListener(this);
		button.addActionListener(new FollowButtonListener(userID, mainPanel,button));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.revalidate();
		button.repaint();
	}

}
