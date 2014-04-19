package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.AttributeSet.FontAttribute;
import javax.swing.text.IconView;
import javax.swing.text.StyleConstants.FontConstants;
import javax.swing.text.StyledEditorKit.FontSizeAction;

import DataRequester.DMessage;

@SuppressWarnings("serial")
public class DMessageView extends JPanel {

	private MainPanel mainPanel;
	private ArrayList<DMessage> directMessages;
	private JPanel messageListPanel;
	private JPanel newMessagePanel;

	public DMessageView(MainPanel mainPanel, ArrayList<DMessage> dMessages) {
		this.mainPanel = mainPanel;
		directMessages = dMessages;
		messageListPanel = null;
		newMessagePanel = null;
		calculateBounds();
		setLayout(new BorderLayout());
	}

	private void calculateBounds() {
		int xPos = mainPanel.getX();
		int yPos = mainPanel.getY();
		int width = mainPanel.getWidth();
		int height = mainPanel.getHeight();
		setBounds(xPos, yPos, width, height);
	}

	public void printDMessageView() {
		removeAll();
		createMessageListPanel();
		add(messageListPanel, BorderLayout.NORTH);
		repaint();
		revalidate();
	}

	private void createMessageListPanel() {
		messageListPanel = new JPanel(new GridBagLayout());
		addHeaderPanel();
		addConversationListPanel();
	}

	private void addHeaderPanel() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		messageListPanel.add(getHeaderPanel(), c);
	}
	
	private JPanel getHeaderPanel() {
		JPanel headerPanel = new JPanel(new BorderLayout());
		JButton newDMessageButton = getNewDMessageButton();
		headerPanel.add(getDMessageField(), BorderLayout.WEST);
		headerPanel.add(newDMessageButton, BorderLayout.EAST);
		headerPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 20));
		return headerPanel;
	}

	private JButton getNewDMessageButton() {
		JButton newDMessageButton = new JButton("New Message");
		newDMessageButton.setBorderPainted(false);
		newDMessageButton.setFocusPainted(false);
		return newDMessageButton;
	}

	private JTextField getDMessageField() {
		JTextField dMessageField = new JTextField("  Direct Message");
		dMessageField.setFocusable(false);
		dMessageField.setBackground(null);
		dMessageField.setBorder(null);
		return dMessageField;
	}

	private void addConversationListPanel() {
		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridy = 1;
		messageListPanel.add(getConversationListPanel(), d);
	}

	private JPanel getConversationListPanel() {
		JPanel conversationListPanel = new JPanel(new GridBagLayout());
		addConversationsTo(conversationListPanel);
		conversationListPanel.setBackground(null);
		return conversationListPanel;
	}

	private void addConversationsTo(JPanel conversationListPanel) {
		TreeMap<String, ArrayList<DMessage>> sortedConversationMap = sortConversationsByDate(getConversations());
		Set<String> keys = sortedConversationMap.keySet();
		int i = 0;
		GridBagConstraints c = new GridBagConstraints();
		for (String key : keys) {
			DMessage lastMessage = sortedConversationMap.get(key).get(0);
			JPanel conversationPanel = createConversationPanel();
			addPeerImage(lastMessage, conversationPanel);
			addMiddlePanel(conversationPanel, key, lastMessage);
			addTimePanel(lastMessage, conversationPanel);
			conversationPanel.addMouseListener(getConversationMouseAdapter());
			addConversationPanel(conversationListPanel, i++, c, conversationPanel);
		}
	}

	private void addConversationPanel(JPanel conversationListPanel, int i,
			GridBagConstraints c, JPanel conversationPanel) {
		c.gridy = i;
		c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		conversationListPanel.add(conversationPanel, c);
	}

	private MouseAdapter getConversationMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel conversation = (JPanel) e.getSource();
				JPanel imagePanel = (JPanel) conversation.getComponent(0);
				JLabel image = (JLabel) imagePanel.getComponent(0);
				System.out.println("Direct Message: Assume " + image.getIcon().toString() + " is opened.");
			}
		};
	}

	private void addTimePanel(DMessage lastMessage, JPanel conversationPanel) {
		JPanel timePanel = new JPanel();
		Date messageDate = lastMessage.getDate();
		JLabel timeLabel = new JLabel("nanik");
		Date now = new GregorianCalendar().getTime();
		StringTokenizer tokens = new StringTokenizer(messageDate.toGMTString());
		if (messageDate.getYear() < now.getYear()) {
			timeLabel.setText(tokens.nextToken() + " " + tokens.nextToken() + " " + tokens.nextToken());
		} else if (messageDate.getMonth() == now.getMonth() && messageDate.getDay() == now.getDay()) {
			if (messageDate.getHours() < now.getHours()) {
				timeLabel.setText((now.getHours() - messageDate.getHours()) + "h");
			} else if (messageDate.getMinutes() < now.getMinutes()) {
				timeLabel.setText((now.getMinutes() - messageDate.getMinutes()) + "m");
			} else {
				timeLabel.setText((now.getSeconds() - messageDate.getSeconds()) + "s");
			}
		} else {
			timeLabel.setText(tokens.nextToken() + " " + tokens.nextToken());
		}
		timePanel.add(timeLabel);
		conversationPanel.add(timePanel, BorderLayout.EAST);
	}

	private void addMiddlePanel(JPanel conversationPanel, String key, DMessage lastMessage) {
		JPanel middlePanel = new JPanel(new GridLayout(2, 1));
		middlePanel.add(getLastMessagePanel(lastMessage), 1, 0);
		middlePanel.add(getNamePanel(key, lastMessage), 0, 0);
		conversationPanel.add(middlePanel, BorderLayout.CENTER);
	}

	private JPanel getLastMessagePanel(DMessage lastMessage) {
		JPanel lastMessagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		if (lastMessage.getIsSent()) {
			lastMessagePanel.add(new JLabel(new ImageIcon("icon/replyIcon.png")));
		}
		JLabel label = new JLabel(lastMessage.getMessage());
		label.setFont(getFont().deriveFont(Font.PLAIN));
		label.setForeground(Color.gray);
		lastMessagePanel.add(label);
		return lastMessagePanel;
	}

	private JPanel createConversationPanel() {
		JPanel conversationPanel = new JPanel();
		conversationPanel.setLayout(new BorderLayout());
		conversationPanel.setOpaque(false);
		conversationPanel.setBackground(Color.white);
		conversationPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 10));
		conversationPanel.setBorder(new LineBorder(Color.black));
		return conversationPanel;
	}

	private JPanel getNamePanel(String key, DMessage lastMessage) {
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel peerName = new JLabel(lastMessage.getPeer().getName());
		peerName.addMouseListener(getNameContentMouseAdapter());
		JLabel peerHandle = new JLabel('@' + key);
		peerHandle.addMouseListener(getNameContentMouseAdapter());
		peerHandle.setFont(getFont().deriveFont((float)11));
		namePanel.add(peerName);
		namePanel.add(peerHandle);
		return namePanel;
	}

	private MouseAdapter getNameContentMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Direct Message: " + ((JLabel)e.getSource()).getText() + " is clicked!");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				((JLabel)e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				((JLabel)e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};
	}

	private void addPeerImage(DMessage lastMessage, JPanel conversationPanel) {
		JPanel imagePanel = new JPanel(new FlowLayout());
		imagePanel.add(getPeerImage(lastMessage));
		imagePanel.setPreferredSize(new Dimension(getHeight() / 10, getHeight() / 10));
		imagePanel.addMouseListener(getPeerImageMouseAdapter());
		conversationPanel.add(imagePanel, BorderLayout.WEST);
	}

	private MouseAdapter getPeerImageMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel panel = (JPanel) e.getSource();
				JLabel image = (JLabel) panel.getComponent(0);
				System.out.println("Direct Message: " + image.getIcon().toString() + " is clicked!");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				JPanel panel = (JPanel) e.getSource();
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				JPanel panel = (JPanel) e.getSource();
				panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};
	}

	private JLabel getPeerImage(DMessage lastMessage) {
		JLabel image = new JLabel(new ImageIcon(lastMessage.getPeer().getProfileImageUrlHttps(), lastMessage.getPeer().getName()));
		image.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		image.setPreferredSize(new Dimension(image.getIcon().getIconWidth(), image.getIcon().getIconHeight()));
		return image;
	}

	private TreeMap<String, ArrayList<DMessage>> sortConversationsByDate(
			HashMap<String, ArrayList<DMessage>> conversationMap) {
		TreeMap<String, ArrayList<DMessage>> sortedConversationMap = new TreeMap<String, ArrayList<DMessage>>(
				new ConversationComparator(conversationMap));
		sortedConversationMap.putAll(conversationMap);
		return sortedConversationMap;
	}

	private HashMap<String, ArrayList<DMessage>> getConversations() {
		HashMap<String, ArrayList<DMessage>> conversationMap = new HashMap<String, ArrayList<DMessage>>();

		for (int i = 0; i < directMessages.size(); i++) {
			DMessage dMessage = directMessages.get(i);
			String peerScreenName = dMessage.getPeer().getScreenName();
			if (conversationMap.containsKey(peerScreenName)) {
				conversationMap.get(peerScreenName).add(dMessage);
			} else {
				ArrayList<DMessage> dMessages = new ArrayList<DMessage>();
				dMessages.add(dMessage);
				conversationMap.put(peerScreenName, dMessages);
			}
		}
		return conversationMap;
	}

}

class ConversationComparator implements Comparator<String> {

	Map<String, ArrayList<DMessage>> conversationMap;

	public ConversationComparator(
			Map<String, ArrayList<DMessage>> conversationMap) {
		this.conversationMap = conversationMap;
	}

	@Override
	public int compare(String key1, String key2) {
		ArrayList<DMessage> dMessageList1 = conversationMap.get(key1);
		ArrayList<DMessage> dMessageList2 = conversationMap.get(key2);
		Date date1 = dMessageList1.get(0).getDate();
		Date date2 = dMessageList2.get(0).getDate();
		return date2.compareTo(date1);
	}

}
