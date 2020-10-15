package view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CardNotFoundText extends JPanel {
	public CardNotFoundText() {
		/* 
		 * Do nothing
		 */
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Credit Card Not Found! " , 65, 70);
	}
}
