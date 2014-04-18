package GUIManager;

import java.util.Date;
import twitter4j.DirectMessage;
import twitter4j.User;

public class DMessage {

	private boolean isSent;
	private User peer;
	private String message;
	private Date date;

	public DMessage(DirectMessage directMessage, String userScreenName) {
		determinePeerStatus(directMessage, userScreenName);
		message = directMessage.getText();
		date = directMessage.getCreatedAt();
	}

	private void determinePeerStatus(DirectMessage directMessage,
			String userScreenName) {
		if (directMessage.getSenderScreenName().equals(userScreenName)) {
			peer = directMessage.getRecipient();
			isSent = true;
		} else {
			peer = directMessage.getSender();
			isSent = false;
		}
	}

	public boolean getIsSent() {
		return isSent;
	}

	public User getPeer() {
		return peer;
	}

	public String getMessage() {
		return message;
	}

	public Date getDate() {
		return date;
	}

}
