package view;


import java.util.List;

import javax.swing.JFrame;

public class TicketConsultMovementsFrame extends JFrame {
	
	public TicketConsultMovementsFrame(String name, String surname, String number, List<String> movs) {
		setTitle("Ticket Movements");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		ConsultMovementsText bc = new ConsultMovementsText(name, surname, number, movs);
		add(bc);
	}

}
