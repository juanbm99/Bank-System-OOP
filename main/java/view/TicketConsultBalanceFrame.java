package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TicketConsultBalanceFrame extends JFrame {

	public TicketConsultBalanceFrame(String name, String surname, String number, float balance) {
		setTitle("Consult Balance Ticket");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		ConsultBalanceText bc = new ConsultBalanceText(name, surname, number, balance);
		add(bc);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new TicketChargeAmountFrame("Juan","Pedro",(float)20.0,"000000000000",(float)102.0);
			ex.setVisible(true);
		});

	}
}
