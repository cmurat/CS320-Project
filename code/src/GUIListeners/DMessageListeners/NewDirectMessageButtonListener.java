package GUIListeners.DMessageListeners;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import GUI.MainPanel;

public class NewDirectMessageButtonListener implements MouseListener {
	MainPanel mainPanel;
	JButton button;
	
	public NewDirectMessageButtonListener(MainPanel mainPanel,JButton button) {
		
		this.mainPanel = mainPanel;
		this.button=button;
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		button.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		mainPanel.newDMessageButtonClicked();
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
