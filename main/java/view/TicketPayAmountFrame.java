package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TicketPayAmountFrame extends JFrame{
	public TicketPayAmountFrame(String name, String surname, float amount,String number, float balance) {

		setTitle("Pay Amount Ticket");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		PayAmountText bc=new PayAmountText(name,surname,amount,number,balance);
		add(bc);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new TicketPayAmountFrame("Juan","Pedro",(float)20.0,"000000000000",(float)102.0);
			ex.setVisible(true);
		});

	}
}
