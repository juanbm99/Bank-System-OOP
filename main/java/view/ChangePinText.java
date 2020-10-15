package view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ChangePinText extends JPanel {
	
	
	public ChangePinText() {
		/* 
		 * Do nothing
		 */
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Pin Successfully changed! " , 65, 70);
	}
}
