package GUIListeners.DMessageListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import GUI.MainPanel;

public class DirectMessageFieldEnterKeyListener implements KeyListener {
	MainPanel mainPanel;

	public DirectMessageFieldEnterKeyListener(MainPanel mainPanel) {
	
		this.mainPanel = mainPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		JTextArea message = (JTextArea) e.getSource();
		if (KeyEvent.VK_ENTER == e.getKeyCode() && !message.equals(""))
			mainPanel.newDMessageEntered();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

