package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.MainPanel;

public class ChangeProfilePictureButtonListener implements ActionListener {
	private MainPanel mainPanel;
	private JButton changeProfilePictureButton;

	public ChangeProfilePictureButtonListener(JButton changeProfilePictureButton, MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.changeProfilePictureButton = changeProfilePictureButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("\n change profile picture is clicked!");
		changeProfilePictureButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		File pictureFile = askPictureFile();
		if (pictureFile != null)
			mainPanel.changeProfilePictureClicked(pictureFile);
		changeProfilePictureButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private File askPictureFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Image Files", "jpg", "jpeg", "gif", "png", 
				"JPG", "JPEG", "GIF", "PNG");
		fileChooser.setFileFilter(filter);
		fileChooser.showDialog(mainPanel, "OK");
		fileChooser.setVisible(true);
		return fileChooser.getSelectedFile();
	}

}
