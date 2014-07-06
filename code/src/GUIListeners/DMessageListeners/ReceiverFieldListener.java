package GUIListeners.DMessageListeners;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class ReceiverFieldListener implements FocusListener{

	@Override
	public void focusGained(FocusEvent e) {
		JTextField receiver = (JTextField) e.getSource();
		if (receiver.getText().equals("Enter the receiver..") || receiver.getForeground().equals(Color.red)) {
			receiver.setText("");
			receiver.setForeground(null);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField receiver = (JTextField) e.getSource();
		if (receiver.getText().equals(""))
			receiver.setText("Enter the receiver..");	
	}

}
