package view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class IncorrectPinText extends JPanel {
	public IncorrectPinText() {
		/* 
		 * Do nothing
		 */
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Incorrect PIN! " , 110, 70);
	}

}
