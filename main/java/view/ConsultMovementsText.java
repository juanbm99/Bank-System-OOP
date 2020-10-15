package view;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class ConsultMovementsText extends JPanel {
	private String name;
	private String surname;
	private String number;
	private List<String> movs;
	
	public ConsultMovementsText(String name, String surname, String number, List<String> movs) {
		this.name = name;
		this.surname = surname;
		this.number = number;
		this.movs=movs;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Dear " + name + " " + surname + " " + ",", 60, 50);
		g.drawString("Card Number:  "  + number + " " + ",", 60, 75);
		int height=90;
		for(int i=0;i<movs.size();i++) {
			g.drawString(movs.get(i), 60, height);
			height+=15;
		}
		g.drawString("Thanks for using the system", 60,320);
	}

}
