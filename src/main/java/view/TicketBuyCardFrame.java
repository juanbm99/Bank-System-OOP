package view;

import java.awt.EventQueue;

import javax.swing.JFrame;



public class TicketBuyCardFrame extends JFrame {
	public TicketBuyCardFrame(String name, String surname, String number, float balance) {

		setTitle("Buy Card Ticket");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		BuyCardText bc=new BuyCardText(name,surname,number,balance);
		add(bc);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new TicketBuyCardFrame("Juan","Pedro","000000000000",(float)102.0);
			ex.setVisible(true);
		});

	}
}
