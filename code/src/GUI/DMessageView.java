package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import DataRequester.DMessage;
import GUIListeners.DMessageListeners.DMessageNavigationFieldListener;
import GUIListeners.DMessageListeners.DirectMessageFieldEnterKeyListener;
import GUIListeners.DMessageListeners.DirectMessageFieldKeyListener;
import GUIListeners.DMessageListeners.DirectMessageFieldListener;
import GUIListeners.DMessageListeners.NameContentListener;
import GUIListeners.DMessageListeners.NewDirectMessageButtonListener;
import GUIListeners.DMessageListeners.PeerImageMouseListener;
import GUIListeners.DMessageListeners.ReceiverFieldListener;

@SuppressWarnings("serial")
public class DMessageView extends JPanel {

	private MainPanel mainPanel;
	private ArrayList<DMessage> directMessages;
	private JPanel messageListPanel;
	private JPanel newMessagePanel;
	private JPanel conversationView;
	private TreeMap<String, ArrayList<DMessage>> sortedConversationMap;

	public DMessageView(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		directMessages = new ArrayList<DMessage>();
		sortedConversationMap = new TreeMap<String, ArrayList<DMessage>>();
		messageListPanel = null;
		newMessagePanel = null;
		conversationView = null;
		calculateBounds();
		setLayout(new BorderLayout());
	}

	private void calculateBounds() {
		int xPos = mainPanel.getX();
		int yPos = mainPanel.getY();
		int width = mainPanel.getWidth();
		int height = mainPanel.getHeight() - mainPanel.getHeight() / 20;
		setBounds(xPos, yPos, width, height);
		setPreferredSize(new Dimension(width, height));
	}

	public void printDMessageListPanel(ArrayList<DMessage> dMessages) {
		removeAll();
		if (dMessages != null) {
			directMessages.addAll(dMessages);
			createMessageListPanel();
		}
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
		headerPanel.add(getDMessageField(), BorderLayout.WEST);
		headerPanel.add(getNewDMessageButton(), BorderLayout.EAST);
		headerPanel
		.setPreferredSize(new Dimension(getWidth(), getHeight() / 20));
		return headerPanel;
	}

	private JTextField getDMessageField() {
		JTextField dMessageField = new JTextField("  Direct Message");
		dMessageField.setFont(getFont().deriveFont(Font.BOLD));
		dMessageField.setFocusable(false);
		dMessageField.setBackground(null);
		dMessageField.setBorder(null);
		return dMessageField;
	}

	private JButton getNewDMessageButton() {
		JButton newDMessageButton = new JButton("New Message");
		newDMessageButton.setBorderPainted(false);
		newDMessageButton.setFocusPainted(false);
		newDMessageButton.addMouseListener(new NewDirectMessageButtonListener(mainPanel,newDMessageButton));
		return newDMessageButton;
	}

	private void addConversationListPanel() {
		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridy = 1;
		messageListPanel.add(createConversationPane(), d);
	}

	private JScrollPane createConversationPane() {
		JPanel layoutPanel = new JPanel(new BorderLayout());
		layoutPanel.add(getConversationListPanel(), BorderLayout.NORTH);
		JScrollPane conversationPane = new JScrollPane(layoutPanel);
		conversationPane.setOpaque(true);
		conversationPane
		.setPreferredSize(new Dimension(getWidth(), getHeight()));
		conversationPane.getVerticalScrollBar().setUnitIncrement(16);
		conversationPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		conversationPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return conversationPane;
	}

	private JPanel getConversationListPanel() {
		JPanel conversationListPanel = new JPanel(new GridBagLayout());
		addConversationsTo(conversationListPanel);
		conversationListPanel.setBackground(null);
		return conversationListPanel;
	}

	private void addConversationsTo(JPanel conversationListPanel) {
		sortedConversationMap = sortConversationsByDate(getConversations());
		Set<String> keys = sortedConversationMap.keySet();
		int i = 0;
		GridBagConstraints c = new GridBagConstraints();
		for (String key : keys) {
			DMessage lastMessage = sortedConversationMap.get(key).get(0);
			JPanel conversationPanel = createListItemPanel();
			addPeerImage(lastMessage, conversationPanel);
			addMiddlePanel(conversationPanel, key, lastMessage);
			addTimePanel(lastMessage, conversationPanel);
			conversationPanel.addMouseListener(getConversationMouseAdapter());
			addConversationPanel(conversationListPanel, i++, c,
					conversationPanel);
		}
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

	private TreeMap<String, ArrayList<DMessage>> sortConversationsByDate(
			HashMap<String, ArrayList<DMessage>> conversationMap) {
		TreeMap<String, ArrayList<DMessage>> sortedConversationMap = new TreeMap<String, ArrayList<DMessage>>(
				new ConversationComparator(conversationMap));
		sortedConversationMap.putAll(conversationMap);
		return sortedConversationMap;
	}

	private JPanel createListItemPanel() {
		JPanel listItemPanel = new JPanel(new BorderLayout());
		listItemPanel.setOpaque(false);
		listItemPanel.setBackground(Color.white);
		listItemPanel.setPreferredSize(new Dimension(getWidth(),
				getHeight() / 10));
		listItemPanel.setBorder(new LineBorder(Color.black));
		return listItemPanel;
	}

	private void addPeerImage(DMessage lastMessage, JPanel conversationPanel) {
		JPanel imagePanel = new JPanel(new FlowLayout());
		imagePanel.add(getPeerImage(lastMessage));
		imagePanel.setPreferredSize(new Dimension(getHeight() / 10,
				getHeight() / 10));
		imagePanel.addMouseListener(new PeerImageMouseListener());
		conversationPanel.add(imagePanel, BorderLayout.WEST);
	}

	private JLabel getPeerImage(DMessage lastMessage) {
		JLabel image = null;
		try {
			image = new JLabel(new ImageIcon(new URL(lastMessage.getPeer()
					.getMiniProfileImageURL()), lastMessage.getPeer()
					.getScreenName()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		image.setPreferredSize(new Dimension(image.getIcon().getIconWidth(),
				image.getIcon().getIconHeight()));
		return image;
	}

	private void addMiddlePanel(JPanel conversationPanel, String key,
			DMessage lastMessage) {
		JPanel middlePanel = new JPanel(new GridLayout(2, 1));
		middlePanel.add(getLastMessagePanel(lastMessage), 1, 0);
		middlePanel.add(getNamePanel(key, lastMessage), 0, 0);
		conversationPanel.add(middlePanel, BorderLayout.CENTER);
	}

	private JPanel getLastMessagePanel(DMessage lastMessage) {
		JPanel lastMessagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		if (lastMessage.getIsSent())
			lastMessagePanel
			.add(new JLabel(new ImageIcon("icon/replyIcon.png")));
		JLabel label = new JLabel(lastMessage.getMessage());
		label.setFont(getFont().deriveFont(Font.PLAIN));
		label.setForeground(Color.gray);
		lastMessagePanel.add(label);
		return lastMessagePanel;
	}

	private JPanel getNamePanel(String key, DMessage lastMessage) {
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel peerName = new JLabel(lastMessage.getPeer().getName());
		peerName.addMouseListener(new NameContentListener());
		JLabel peerHandle = new JLabel('@' + key);
		peerHandle.addMouseListener(new NameContentListener());
		peerHandle.setFont(getFont().deriveFont((float) 11));
		namePanel.add(peerName);
		namePanel.add(peerHandle);
		return namePanel;
	}

	@SuppressWarnings("deprecation")
	private void addTimePanel(DMessage lastMessage, JPanel conversationPanel) {
		JPanel timePanel = new JPanel();
		Date messageDate = lastMessage.getDate();
		JLabel timeLabel = new JLabel("nanik");
		Date now = new GregorianCalendar().getTime();
		StringTokenizer tokens = new StringTokenizer(messageDate.toGMTString());
		if (messageDate.getYear() < now.getYear()) {
			timeLabel.setText(tokens.nextToken() + " " + tokens.nextToken()
					+ " " + tokens.nextToken());
		} else if (messageDate.getMonth() == now.getMonth()
				&& messageDate.getDay() == now.getDay()) {
			if (messageDate.getHours() < now.getHours()) {
				timeLabel.setText((now.getHours() - messageDate.getHours())
						+ "h");
			} else if (messageDate.getMinutes() < now.getMinutes()) {
				timeLabel.setText((now.getMinutes() - messageDate.getMinutes())
						+ "m");
			} else {
				timeLabel.setText((now.getSeconds() - messageDate.getSeconds())
						+ "s");
			}
		} else {
			timeLabel.setText(tokens.nextToken() + " " + tokens.nextToken());
		}
		timePanel.add(timeLabel);
		conversationPanel.add(timePanel, BorderLayout.EAST);
	}

	private MouseAdapter getConversationMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				JPanel conversation = (JPanel) e.getSource();
				conversation.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				JPanel imagePanel = (JPanel) conversation.getComponent(0);
				JLabel image = (JLabel) imagePanel.getComponent(0);
				mainPanel.conversationClicked(image.getIcon().toString());
				conversation.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		};
	}

	private void addConversationPanel(JPanel conversationListPanel, int i,
			GridBagConstraints c, JPanel conversationPanel) {
		c.gridy = i;
		c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		conversationListPanel.add(conversationPanel, c);
	}

	public void printConversationView(String peer, URL userPicture) {
		removeAll();
		JPanel messagePanel=new JPanel();
		messagePanel.setLayout(new FlowLayout());
		createConversationView(peer, userPicture);
		add(conversationView,BorderLayout.NORTH);
		repaint();
		revalidate();
	}

	private void createConversationView(String peer, URL userPicture) {
		conversationView = new JPanel(new GridBagLayout());
		addConversationHeader(peer);
		addMessages(peer, userPicture);
	}

	private void addConversationHeader(String peer) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		conversationView.add(getConversationHeader(peer), c);
	}

	private Component getConversationHeader(String peer) {
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.add(getNavigationField(peer), BorderLayout.WEST);
		headerPanel
		.setPreferredSize(new Dimension(getWidth(), getHeight() / 20));
		return headerPanel;
	}

	private JPanel getNavigationField(String peer) {
		JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		addBackToDMessageListButtonTo(navigationPanel);
		addPeerFieldTo(navigationPanel, peer);
		return navigationPanel;
	}

	private void addBackToDMessageListButtonTo(JPanel navigationPanel) {
		JTextField dMessageField = getDMessageField();
		dMessageField.addMouseListener(new DMessageNavigationFieldListener(mainPanel,dMessageField));
		navigationPanel.add(dMessageField);
	}

	private void addPeerFieldTo(JPanel navigationPanel, String peer) {
		JTextField peerField = getDMessageField();
		peerField.setText(" > with " + peer);
		navigationPanel.add(peerField);
	}

	private void addMessages(String peer, URL userPicture) {
		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridy = 1;
		conversationView.add(createMessagePane(peer, userPicture), d);
	}

	private Component createMessagePane(String peer, URL userPicture) {
		JPanel layout = new JPanel(new BorderLayout());
		layout.add(getMessageList(peer, userPicture), BorderLayout.NORTH);
		JScrollPane messagePane = new JScrollPane(layout);
		messagePane.setOpaque(true);
		messagePane.getVerticalScrollBar().setUnitIncrement(16);
		messagePane.setPreferredSize(new Dimension(getWidth(), getHeight() - 50));
		messagePane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		messagePane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return messagePane;
	}

	private Component getMessageList(String peer, URL userPicture) {
		JPanel messageListPanel = new JPanel(new GridBagLayout());
		addMessagesTo(messageListPanel, peer, userPicture);
		messageListPanel.setBackground(null);
		return messageListPanel;
	}

	private void addMessagesTo(JPanel messageListPanel, String peer,
			URL userPicture) {
		ArrayList<DMessage> messages = sortedConversationMap.get(peer);
		int i = 0;
		GridBagConstraints c = new GridBagConstraints();

		for (DMessage message : messages) {
			JPanel messagePanel = createListItemPanel();
			if (message.getIsSent()) {
				addSentMessageTo(messagePanel, message, userPicture);
			} else {
				addReceivedMessageTo(messagePanel, message);
			}
			c.gridy = i++;
			c.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
			messageListPanel.add(messagePanel, c);

		}
	}

	private void addSentMessageTo(JPanel messagePanel, DMessage message,
			URL userPicture) {
		messagePanel.add(getImage(userPicture), BorderLayout.EAST);
		JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel messageLabel = new JLabel(message.getMessage());
		middlePanel.add(messageLabel);
		messagePanel.add(middlePanel, BorderLayout.CENTER);
	}

	private JPanel getImage(URL userPicture) {
		JPanel imagePanel = new JPanel(new FlowLayout());
		JLabel image = new JLabel(new ImageIcon(userPicture));
		image.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		image.setPreferredSize(new Dimension(image.getIcon().getIconWidth(),
				image.getIcon().getIconHeight()));
		imagePanel.add(image);
		imagePanel.setPreferredSize(new Dimension(getHeight() / 10,
				getHeight() / 10));
		return imagePanel;
	}

	private void addReceivedMessageTo(JPanel messagePanel, DMessage message) {
		try {
			messagePanel.add(getImage(new URL(message.getPeer().getOriginalProfileImageURL())),
					BorderLayout.WEST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel messageLabel = new JLabel(message.getMessage());
		middlePanel.add(messageLabel);
		messagePanel.add(middlePanel, BorderLayout.CENTER);
	}

	public void printNewDMessageView() {
		removeAll();
		newMessagePanel = new JPanel(new BorderLayout());
		JTextField receiver = new JTextField("Enter the receiver..");
		receiver.setBorder(new LineBorder(Color.black));
		receiver.setPreferredSize(new Dimension(getWidth(), getHeight() / 20));
		receiver.addFocusListener(new ReceiverFieldListener());
		JTextArea dMessage = new JTextArea("Enter your message..");
		dMessage.setPreferredSize(new Dimension(getWidth(), getHeight() / 10));
		dMessage.setLineWrap(true);
		dMessage.setOpaque(false);
		dMessage.setColumns(140);
		dMessage.setBorder(new LineBorder(Color.black));
		dMessage.addFocusListener(new DirectMessageFieldListener() );
		dMessage.addKeyListener(new DirectMessageFieldEnterKeyListener(mainPanel) );
		dMessage.addKeyListener(new DirectMessageFieldKeyListener());
		newMessagePanel.add(receiver, BorderLayout.NORTH);
		newMessagePanel.add(dMessage, BorderLayout.SOUTH);
		add(newMessagePanel);
		repaint();
		revalidate();
	}

	public String getNewDMessageReceiver() {
		return ((JTextField)newMessagePanel.getComponent(0)).getText();
	}

	public String getNewDMessage() {
		return ((JTextArea)(newMessagePanel.getComponent(1))).getText();
	}

	public void printDMessageError(String errorMessage) {
		JTextField receiverField = (JTextField) newMessagePanel.getComponent(0);
		receiverField.setText(errorMessage);
		receiverField.setForeground(Color.red);
		receiverField.transferFocusUpCycle();
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
