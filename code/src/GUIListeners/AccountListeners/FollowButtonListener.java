package GUIListeners.AccountListeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import GUI.MainPanel;

public class FollowButtonListener implements ActionListener {
	private long userID;
	private MainPanel mainPanel;
	private JButton button;

	public FollowButtonListener(long userID, MainPanel mainPanel, JButton button) {
		this.userID = userID;
		this.mainPanel = mainPanel;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\nFollow Button pressed");
		mainPanel.followButtonPressed(userID);
		button.setText("UnFollow");
		button.removeActionListener(this);
		button.addActionListener(new UnFollowButtonListener(userID, mainPanel, button));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.revalidate();
		button.repaint();
	}

}
