package GUI.listeners;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import GUI.MainPanel;

public class DeleteButtonListener implements ActionListener {
	private long tweetId;
	private JButton deleteButton;
	private MainPanel mainPanel;
	private JPanel tweetPanel;

	public DeleteButtonListener(long tweetId, JButton deleteButton, MainPanel mainPanel, JPanel tweetPanel) {
		this.tweetId = tweetId;
		this.deleteButton = deleteButton;
		this.mainPanel = mainPanel;
		this.tweetPanel=tweetPanel;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("delete is clicked!");
		deleteButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.deleteButtonClicked(tweetId);
		tweetPanel.removeAll();
		tweetPanel.revalidate();
		tweetPanel.repaint();
		deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
}