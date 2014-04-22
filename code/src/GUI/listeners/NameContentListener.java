package GUI.listeners;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class NameContentListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Direct Message: "
				+ ((JLabel) e.getSource()).getText() + " is clicked!");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		((JLabel) e.getSource()).setCursor(new Cursor(
				Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		((JLabel) e.getSource()).setCursor(new Cursor(
				Cursor.DEFAULT_CURSOR));
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
