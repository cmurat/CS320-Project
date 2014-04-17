package GUI;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SearchScreen extends JPanel {
	private MainPanel mainPanel;
	private JTextField searchBar;
	private JPanel searchResult;

	public SearchScreen(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setLayout(new BorderLayout());
		addSearchResultScreen();
		addSearchBar();
	}

	private void addSearchResultScreen() {
		this.searchResult = new JPanel();
		this.searchResult.setLayout(new BorderLayout());
		add(searchResult, BorderLayout.CENTER);
	}

	private void addSearchBar() {
		searchBar = new JTextField("Enter a search word..");
		searchBar.addKeyListener(getEnterKeyAdapter(searchBar));
		add(searchBar, BorderLayout.NORTH);
	}

	public String getSearch() {
		return searchBar.getText();
	}

	public void printStream(JScrollPane pane) {
		searchResult.removeAll();
		searchResult.add(pane);
		revalidate();
		repaint();
	}

	private KeyListener getEnterKeyAdapter(final JTextField searchBar) {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					mainPanel.searchEntered();
					searchBar.setText("Enter a search word..");
					searchBar.transferFocusUpCycle();
				}
			}
		};

	}

}
