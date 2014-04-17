package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.User;

@SuppressWarnings("serial")
public class DMessageView extends JPanel {
	
	private MainPanel mainPanel;
	private ResponseList<DirectMessage> directMessages;
	private JPanel messageListPanel;
	private JPanel newMessagePanel;
	
	public DMessageView(MainPanel mainPanel, ResponseList<DirectMessage> responseList){
		this.mainPanel = mainPanel;
		directMessages = responseList;
		messageListPanel = null;
		newMessagePanel = null;
//		mainPanel.add(this);
		calculateBounds();
		setLayout(new GridLayout(0, 1));
	}
	
	private void calculateBounds() {
		int xPos = mainPanel.getBounds().x;
		int yPos = mainPanel.getBounds().y + mainPanel.getBounds().height / 10;
		int width = mainPanel.getBounds().width;
		int height = mainPanel.getBounds().height - 2 * yPos;
		setBounds(xPos, yPos, width, height);
	}
	

	public void printDMessageView() {
		messageListPanel = new JPanel();
		messageListPanel.setLayout(null);
		messageListPanel.setBackground(Color.cyan);
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(getX(), getY(), getWidth(), getHeight() / 9);
		headerPanel.setLocation(0, 0);
		headerPanel.setLayout(new BorderLayout());
		JTextField dMessageField = new JTextField("  Direct Message");
		dMessageField.setEditable(false);
		dMessageField.setFocusable(false);
		dMessageField.setBackground(null);
		dMessageField.setBorder(null);
		JButton newDMessageButton = new JButton("New Message");
		newDMessageButton.setBorderPainted(false);
		newDMessageButton.setFocusPainted(false);
		headerPanel.add(dMessageField, BorderLayout.WEST);
		headerPanel.add(newDMessageButton, BorderLayout.EAST);
		messageListPanel.add(headerPanel);
		add(messageListPanel);
		repaint();
		revalidate();
	}
}
