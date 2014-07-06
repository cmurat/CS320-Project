package GUIListeners.DMessageListeners;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import GUI.MainPanel;

public class DMessageNavigationFieldListener implements MouseListener {
	MainPanel mainPanel;
	JTextField dMessageField;
	public DMessageNavigationFieldListener(MainPanel mainPanel,
			JTextField dMessageField) {
		
		this.mainPanel = mainPanel;
		this.dMessageField = dMessageField;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField dMessageField = (JTextField) e.getSource();
		dMessageField.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.backToMessageListClicked();
		dMessageField.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JTextField dMessageField = (JTextField) e.getSource();
		dMessageField.setForeground(Color.blue);
		dMessageField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		JTextField dMessageField = (JTextField) e.getSource();
		dMessageField.setForeground(null);
		dMessageField.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
