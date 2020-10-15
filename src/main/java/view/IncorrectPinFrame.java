package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class IncorrectPinFrame extends JFrame {
	public IncorrectPinFrame() {

		setTitle("Incorrect Pin Ticket");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		IncorrectPinText bc=new IncorrectPinText();
		add(bc);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new TicketChangePinFrame();
			ex.setVisible(true);
		});

	}

}
