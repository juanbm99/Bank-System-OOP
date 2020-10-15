package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CardNotFoundFrame extends JFrame {
	public CardNotFoundFrame() {

		setTitle("Card Not Found Ticket");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		CardNotFoundText bc=new CardNotFoundText();
		add(bc);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new TicketChangePinFrame();
			ex.setVisible(true);
		});

	}
}
