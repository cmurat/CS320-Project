package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import GUI.MainPanel;

public class ChangeProfilePictureButtonListener implements ActionListener{
	private MainPanel mainPanel;
	private JButton changeProfilePictureButton;
	
	public ChangeProfilePictureButtonListener(JButton changeProfilePictureButton, MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.changeProfilePictureButton = changeProfilePictureButton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("\n change username is clicked!");
		changeProfilePictureButton.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.changeProfilePictureClicked(askPictureFile());
		changeProfilePictureButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private File askPictureFile() {
		JFileChooser jfc = new JFileChooser();
	    jfc.showDialog(null,"Please Select the File");
	    jfc.setVisible(true);
	    return jfc.getSelectedFile();
	}

}
