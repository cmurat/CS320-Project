package GUI;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import DataRequester.DetailedAccount;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel {

	private MainPanel mainPanel;
	private DetailedAccount account;

	public ProfilePanel(MainPanel mainPanel, DetailedAccount account, TweetBox tweetBox) {
		this.mainPanel = mainPanel;
		this.account = account;
		calculateBounds();
		setLayout(new BorderLayout());

		AccountProfileScreen accountInfoPanel = new AccountProfileScreen(account, mainPanel);

		JPanel tweetStreamPanel = new JPanel();
		tweetStreamPanel.setLayout(new BorderLayout());

		add(accountInfoPanel, BorderLayout.NORTH);
		addTweetStreamTo(tweetBox) ;
	}

	private void addTweetStreamTo(TweetBox tweetBox) {
		add(TweetStreamFactory.printTweetStream(account.getTweets(), mainPanel, tweetBox),BorderLayout.CENTER);
	}

	private void calculateBounds() {
		setBounds(mainPanel.getBounds().x, mainPanel.getBounds().y,
				mainPanel.getBounds().width, mainPanel.getBounds().height);
	}

}
