package GUI.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

public class DirectMessageFieldKeyListener implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		JTextArea message = (JTextArea) e.getSource();
		if (message.getText().length() >= 140) {
			message.setText(message.getText().substring(0, 140));
		}
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
