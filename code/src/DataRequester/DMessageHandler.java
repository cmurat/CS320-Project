package DataRequester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;
import twitter4j.TwitterException;

public class DMessageHandler extends TwitterConnection {
	private static DMessageHandler dMessageHandler;

	
	public static DMessageHandler getInsance(){
		if(dMessageHandler ==null)
			dMessageHandler = new DMessageHandler();
		return dMessageHandler;
		
	}

	public DMessage sendDirectMessage(String screenName, String message)
			throws TwitterException {
		DirectMessage dMessage = twitter.sendDirectMessage(screenName, message);
		return new DMessage(dMessage, twitter.getScreenName());
	}

	public ResponseList<DirectMessage> getDirectMessages() {
		try {
			return twitter.getDirectMessages();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResponseList<DirectMessage> getSentDirectMessages() {
		try {
			return twitter.getSentDirectMessages();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<DMessage> getAllDMessages() {
		ResponseList<DirectMessage> receivedDMessages = getDirectMessages();
		ResponseList<DirectMessage> sentDMessages = getSentDirectMessages();
		receivedDMessages.addAll(sentDMessages);
		sortDMessagesByDate(receivedDMessages);
		return convertToDMessageList(receivedDMessages);
	}

	private void sortDMessagesByDate(ResponseList<DirectMessage> receivedDMessages) {
		Collections.sort(receivedDMessages, new Comparator<DirectMessage>() {
			@Override
			public int compare(DirectMessage o1, DirectMessage o2) {
				Date date1 = o1.getCreatedAt();
				Date date2 = o2.getCreatedAt();
				return date2.compareTo(date1);
			}
		});
	}

	private ArrayList<DMessage> convertToDMessageList(
			ResponseList<DirectMessage> allDMessages) {
		String userScreenName = getUserScreenName();
		ArrayList<DMessage> dMessages = new ArrayList<DMessage>();
		for (int i = 0; i < allDMessages.size(); i++) {
			dMessages.add(new DMessage(allDMessages.get(i), userScreenName));
		}
		return dMessages;
	}

	private String getUserScreenName() {
		String userScreenName = "";
		try {
			userScreenName = twitter.getScreenName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userScreenName;
	}

}
