package GUIListeners.DMessageListeners;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

public class DirectMessageFieldListener implements FocusListener{

	@Override
	public void focusGained(FocusEvent e) {
		JTextArea message = (JTextArea) e.getSource();
		if (message.getText().equals("Enter your message.."))
			message.setText("");
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
			JTextArea message = (JTextArea) e.getSource();
			if (message.getText().equals(""))
				message.setText("Enter your message..");
	}

}
