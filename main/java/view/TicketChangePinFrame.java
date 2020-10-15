package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TicketChangePinFrame extends JFrame {
	public TicketChangePinFrame() {

		setTitle("Change Pin Ticket");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		ChangePinText bc=new ChangePinText();
		add(bc);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new TicketChangePinFrame();
			ex.setVisible(true);
		});

	}
}
