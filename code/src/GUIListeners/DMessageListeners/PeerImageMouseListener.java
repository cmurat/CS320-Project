package GUIListeners.DMessageListeners;

import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
public class PeerImageMouseListener implements MouseListener {
	
		@Override
		public void mouseClicked(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			JLabel image = (JLabel) panel.getComponent(0);
			System.out.println("Direct Message: "
					+ image.getIcon().toString() + " is clicked!");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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

