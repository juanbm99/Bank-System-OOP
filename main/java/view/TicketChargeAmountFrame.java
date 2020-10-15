package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TicketChargeAmountFrame extends JFrame {
	public TicketChargeAmountFrame(String name, String surname, float amount,String number, float balance) {

		setTitle("Charge Amount Ticket");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		ChargeAmountText bc=new ChargeAmountText(name,surname,amount,number,balance);
		add(bc);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new TicketChargeAmountFrame("Juan","Pedro",(float)20.0,"000000000000",(float)102.0);
			ex.setVisible(true);
		});

	}
}
