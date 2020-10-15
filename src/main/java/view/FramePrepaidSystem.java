package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


import javax.swing.JOptionPane;
import javax.swing.JPanel;




import controllers.PrepaidSystemController;
import exceptions.CardExpiredException;
import exceptions.CreditCardNumberOutOfRangeException;
import exceptions.CreditCardUnknownException;
import exceptions.IncorrectPinException;
import exceptions.NotEnoughMoneyException;
import model.CreditCard;
import model.PrepaidSystem;

public class FramePrepaidSystem extends JFrame{


	private PrepaidSystemController controller;

	private PrepaidSystem ps;


	public FramePrepaidSystem(PrepaidSystemController controller, List<CreditCard> lista) {
		this.controller = controller;
		this.ps = new PrepaidSystem();
		ps.getCardHistory().setList(lista);

		setTitle("PrepaidSystem");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLayout(new GridLayout(7,1));
		createFields ();
		this.setVisible(true);

	}
	private void createFields() {

		JButton buyCard = new JButton("Buy a card");
		buyCard.addActionListener(event -> {
			try {
				showDialogBuyCard();
			} catch(CreditCardNumberOutOfRangeException e) {
				Logger.getLogger(getName());
			}
		});
		buyCard.setPreferredSize(new Dimension(180,30));
		buyCard.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		JButton payAmount= new JButton("Pay Amount");
		payAmount.addActionListener(event -> {
			try {
				showDialogPayAmount();
			} catch (CreditCardUnknownException | CardExpiredException | IncorrectPinException |
					NotEnoughMoneyException e) {
				Logger.getLogger(getName());
			}
		});
		payAmount.setPreferredSize(new Dimension(180,30));
		payAmount.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		JButton chargeAmount = new JButton("Charge Amount");
		chargeAmount.addActionListener(event -> {
			try {
				showDialogChargeAmount();
			} catch (CreditCardUnknownException | CardExpiredException | IncorrectPinException e) {
				Logger.getLogger(getName());
			}
		});
		chargeAmount.setPreferredSize(new Dimension(180,30));
		chargeAmount.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		JButton consultMovements = new JButton("consultMovements");
		consultMovements.addActionListener(event -> {

			try {
				showDialogConsultMovements();
			} catch (CreditCardUnknownException |  IncorrectPinException  e) {
				Logger.getLogger(getName());
			}

		});
		consultMovements.setPreferredSize(new Dimension(180,30));
		consultMovements.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		JButton consultBalance = new JButton("consultBalance");
		consultBalance.addActionListener(event -> {
			try {
				showDialogConsultBalance();
			} catch (CreditCardUnknownException |  IncorrectPinException  e) {
				Logger.getLogger(getName());
			}
		});
		consultBalance.setPreferredSize(new Dimension(180,30));
		consultBalance.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		JButton changePIN = new JButton("changePin");
		changePIN.addActionListener(event -> {


			try {
				showDialogChangePin();
			} catch(IncorrectPinException e) {
				Logger.getLogger(getName());
			}

		});
		changePIN.setPreferredSize(new Dimension(180,30));
		changePIN.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		JButton exitSystem = new JButton("Exit");
		exitSystem.addActionListener(event -> {
			try {
				showDialogExit();
				this.dispose();
			} catch (NumberFormatException | IOException e) {
				Logger.getLogger(getName());
			}

		});
		exitSystem.setPreferredSize(new Dimension(180,30));
		exitSystem.setBackground(Color.RED);
		exitSystem.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));


		JPanel buyCardPanel = new JPanel();
		buyCardPanel.add(buyCard,BorderLayout.CENTER);
		buyCardPanel.setBackground(Color.DARK_GRAY);
		add(buyCardPanel);

		JPanel payAmountPanel = new JPanel();
		payAmountPanel.add(payAmount,BorderLayout.CENTER);
		payAmountPanel.setBackground(Color.DARK_GRAY);
		add(payAmountPanel);

		JPanel chargeAmountPanel = new JPanel();
		chargeAmountPanel.add(chargeAmount,BorderLayout.CENTER);
		chargeAmountPanel.setBackground(Color.DARK_GRAY);
		add(chargeAmountPanel);

		JPanel consultMovementsPanel = new JPanel();
		consultMovementsPanel.add(consultMovements,BorderLayout.CENTER);
		consultMovementsPanel.setBackground(Color.DARK_GRAY);
		add(consultMovementsPanel);

		JPanel consultBalancePanel = new JPanel();
		consultBalancePanel.add(consultBalance,BorderLayout.CENTER);
		consultBalancePanel.setBackground(Color.DARK_GRAY);
		add(consultBalancePanel);

		JPanel changePINPanel = new JPanel();
		changePINPanel.add(changePIN,BorderLayout.CENTER);
		changePINPanel.setBackground(Color.DARK_GRAY);
		add(changePINPanel);	

		JPanel exitPanel = new JPanel();
		exitPanel.add(exitSystem,BorderLayout.CENTER);
		exitPanel.setBackground(Color.DARK_GRAY);
		add(exitPanel);	

	}

	private void showDialogExit() throws  IOException {
		ps.getCardHistory().save((ArrayList<CreditCard>)ps.getCardHistory().getList());
	}
	private void showDialogBuyCard() throws  CreditCardNumberOutOfRangeException {
		String name = JOptionPane.showInputDialog(null,
				"Name");
		String surname = JOptionPane.showInputDialog(null,
				"Surname");
		String pin = JOptionPane.showInputDialog(null,
				"Pin");
		String amount = JOptionPane.showInputDialog(null,
				"Amount");



		ps.buyNewCard(name, surname, pin, Float.parseFloat(amount));
		String number=ps.getCardHistory().getList().get(ps.getCardHistory().getList().size()-1).getNumber();
		float balance=ps.getCardHistory().getList().get(ps.getCardHistory().getList().size()-1).getAmount();
		JFrame ex = new TicketBuyCardFrame(name,surname,number,balance);
		ex.setVisible(true);
	}

	private void showDialogChangePin() throws  IncorrectPinException {

		String number= JOptionPane.showInputDialog(null,
				" Credit Card Number");

		String oldPin = JOptionPane.showInputDialog(null,
				"Old Pin");
		String newPin = JOptionPane.showInputDialog(null,
				"New Pin");

		int answer = JOptionPane.showConfirmDialog(null,
				"Are u sure?", 
				"Select one option",
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE);

		if (answer == JOptionPane.YES_OPTION) {
			try {
			for(int i=0;i<ps.getCardHistory().getList().size();i++) {
				if(ps.getCardHistory().getList().get(i).getNumber().equals(number)) {
					ps.getCardHistory().getList().get(i).changePIN(oldPin, newPin);
				}
			}
			JFrame ex = new TicketChangePinFrame();
			ex.setVisible(true);
			}catch(IncorrectPinException e) {
				JFrame xe=new IncorrectPinFrame();
				xe.setVisible(true);
			}
		}
		else if (answer == JOptionPane.NO_OPTION) {
			JOptionPane jop=new JOptionPane();
			JDialog dialog=jop.createDialog(null, "Exiting . . .");
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					}catch(Exception e) {
						e.getMessage();
					}
					dialog.dispose();
				}
			}).start();
		}
		else {
			this.dispose();
		}

	}



	private void showDialogChargeAmount() throws  
	CreditCardUnknownException, CardExpiredException, IncorrectPinException {
		String number = JOptionPane.showInputDialog(null, "Insert credit card number");
		String amount = JOptionPane.showInputDialog(null, "Insert amount");
		String pin = JOptionPane.showInputDialog(null, "Insert PIN");

		String name = "";
		String surname = "";
		String last4digits = "";
		float balance = (float)0.0;
		try {
		ps.chargeAmount(number, Float.parseFloat(amount), pin);
		for(int i=0;i<ps.getCardHistory().getList().size();i++) {
			if(ps.getCardHistory().getList().get(i).getNumber().equals(number)) {
				name=ps.getCardHistory().getList().get(i).getName();
				surname=ps.getCardHistory().getList().get(i).getSurname();
				balance=ps.getCardHistory().getList().get(i).getAmount();
				last4digits=ps.getCardHistory().getList().get(i).getLast4Digits(number);
			}
		}

		String unknown="XXXX";
		
		JFrame ex = new TicketChargeAmountFrame(name,surname,Float.parseFloat(amount),unknown+unknown+last4digits,balance);
		ex.setVisible(true);
		}catch(IncorrectPinException e) {
			JFrame xe=new IncorrectPinFrame();
			xe.setVisible(true);
		}
		catch(CreditCardUnknownException e) {
			JFrame cu=new CardNotFoundFrame();
			cu.setVisible(true);
		}
		
	}


	private void showDialogPayAmount() throws  CreditCardUnknownException, CardExpiredException, IncorrectPinException, NotEnoughMoneyException{
		String number = JOptionPane.showInputDialog(null, "Insert credit card number");
		String amount = JOptionPane.showInputDialog(null, "Insert amount");
		String pin = JOptionPane.showInputDialog(null, "Insert PIN");

		String name = "";
		String surname = "";
		String last4digits = "";
		float balance = (float)0.0;
		try {
		ps.payAmount(number, Float.parseFloat(amount), pin);
		for(int i=0;i<ps.getCardHistory().getList().size();i++) {
			if(ps.getCardHistory().getList().get(i).getNumber().equals(number)) {
				name=ps.getCardHistory().getList().get(i).getName();
				surname=ps.getCardHistory().getList().get(i).getSurname();
				balance=ps.getCardHistory().getList().get(i).getAmount();
				last4digits=ps.getCardHistory().getList().get(i).getLast4Digits(number);
			}
		}

		String unknown="XXXX";
		JFrame ex = new TicketPayAmountFrame(name,surname,Float.parseFloat(amount),unknown+unknown+last4digits,balance);
		ex.setVisible(true);
		}catch(IncorrectPinException e) {
			JFrame xe=new IncorrectPinFrame();
			xe.setVisible(true);	
		}
		catch(CreditCardUnknownException e) {
			JFrame cu=new CardNotFoundFrame();
			cu.setVisible(true);
		}
	}





	private void showDialogConsultBalance() throws  CreditCardUnknownException, IncorrectPinException {
		String number = JOptionPane.showInputDialog(null, "Credit Card Number");
		String pin = JOptionPane.showInputDialog(null, "PIN");
		try {
		if(number != null || pin != null) {
			String name = "";
			String surname = "";
			String last4digits = "";
			
			float balance = ps.consultBalance(number, pin);

			for(int i=0;i<ps.getCardHistory().getList().size();i++) {
				if(ps.getCardHistory().getList().get(i).getNumber().equals(number)) {
					name=ps.getCardHistory().getList().get(i).getName();
					surname=ps.getCardHistory().getList().get(i).getSurname();
					last4digits=ps.getCardHistory().getList().get(i).getLast4Digits(number);
				}
			}

			JFrame ex = new TicketConsultBalanceFrame(name, surname, "XXXXXXXX" + last4digits, balance);
			ex.setVisible(true);
		}
		}catch(IncorrectPinException e) {
			JFrame xe=new IncorrectPinFrame();
			xe.setVisible(true);	
		}
		catch(CreditCardUnknownException e) {
			JFrame cu=new CardNotFoundFrame();
			cu.setVisible(true);
		}
	}

	private void showDialogConsultMovements() throws  CreditCardUnknownException, IncorrectPinException {
		String number = JOptionPane.showInputDialog(null, "Credit Card Number");
		String pin = JOptionPane.showInputDialog(null, "PIN");
		try {
		if(number != null || pin != null) {
			String name = "";
			String surname = "";
			String last4digits = "";


			for(int i=0;i<ps.getCardHistory().getList().size();i++) {
				if(ps.getCardHistory().getList().get(i).getNumber().equals(number)) {
					name=ps.getCardHistory().getList().get(i).getName();
					surname=ps.getCardHistory().getList().get(i).getSurname();
					last4digits=ps.getCardHistory().getList().get(i).getLast4Digits(number);
				}
			}

			JFrame ex = new TicketConsultMovementsFrame(name, surname, "XXXXXXXX" + last4digits, ps.consultMovements(number, pin));
			ex.setVisible(true);
		}
		}catch(IncorrectPinException e) {
			JFrame xe=new IncorrectPinFrame();
			xe.setVisible(true);	
		}
		catch(CreditCardUnknownException e) {
			JFrame cu=new CardNotFoundFrame();
			cu.setVisible(true);
		}
	}

	public void showValidationError (String message) {
		JOptionPane.showMessageDialog(this, message);
	}

}
