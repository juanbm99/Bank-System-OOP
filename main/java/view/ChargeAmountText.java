package view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class ChargeAmountText extends JPanel {
	private String name;
	private String surname;
	private String number;
	private float balance;
	private float amount;
	
	public ChargeAmountText(String name, String surname,float amount,String number, float balance) {
		this.name=name;
		this.surname=surname;
		this.amount=amount;
		this.number=number;
		this.balance=balance;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Dear " + name + " " + surname + " " + ",", 60, 50);
		g.drawString("Amount:  "  + amount + " " + ",", 60, 75);
		g.drawString("Card Number:  "  + number + " " + ",", 60, 90);
		g.drawString("Balance:  "  + balance + " " + ",", 60, 105);
		g.drawString("Thanks for using the system", 60,120);
	}
}
