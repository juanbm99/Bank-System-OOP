package model;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.CardExpiredException;
import exceptions.CreditCardNumberOutOfRangeException;
import exceptions.CreditCardUnknownException;
import exceptions.IncorrectPinException;
import exceptions.NotEnoughMoneyException;

public class PrepaidSystem {
	private CardHistory ch;
	private String msgUnknown="Error: Credit Card Unknown";
	private String dateFormat="MM/yyyy";

	public PrepaidSystem() {
		ch=new CardHistory();
	}

	public CardHistory getCardHistory() {
		return ch;
	}


	public void buyNewCard(String name, String surname, String pin, float amount) throws CreditCardNumberOutOfRangeException {
		String date = new SimpleDateFormat(dateFormat).format(new Date());
		int yearBuy=Integer.parseInt(date.substring(3));
		int yearExpire=yearBuy+1;
		date=date.replace(date.substring(3), Integer.toString(yearExpire));
		if(ch.getList().isEmpty()) {
			ch.getList().add(new CreditCard("Tarjeta","Inicial","0000",(float)0.0,"000000000000","20-12-2019"));
		}
		if((Long.parseLong(ch.getList().get(ch.getList().size()-1).getNumber())+1) > Long.parseLong("999999999999")) {
			throw new CreditCardNumberOutOfRangeException("Error: credit card number out of range.");
		}else {			
			CreditCard newCreditCard = new CreditCard(name, surname, pin, amount, String.format("%012d", (Long.parseLong(ch.getList().get(ch.getList().size()-1).getNumber())+1)),date);
			ch.getList().add(newCreditCard);
		}
	}

	public void payAmount(String creditCardNumber, float newPay, String pin) throws IncorrectPinException, CreditCardUnknownException,
	NotEnoughMoneyException, CardExpiredException {
		if(ch.searchCreditCard(creditCardNumber)==null) {
			throw new CreditCardUnknownException(msgUnknown);
		}
		if(!ch.checkPIN(creditCardNumber, pin)) {
			throw new IncorrectPinException("El pin introducido no es correcto");
		}
		String fecha=ch.searchCreditCard(creditCardNumber).getDate().substring(3);
		String fechaActual=new SimpleDateFormat(dateFormat).format(new Date());
		int aux=Integer.parseInt(fecha);
		int aux1=Integer.parseInt(fechaActual.substring(3));
		if(ch.searchCreditCard(creditCardNumber).getAmount() < newPay) {
			throw new NotEnoughMoneyException("Error: not enough money to pay");	
		}else if(aux<aux1) {
			throw new CardExpiredException("Error: card expired");
		}else {
			ch.searchCreditCard(creditCardNumber).pay(newPay);
			String formatter = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			ch.searchCreditCard(creditCardNumber).setMovement(formatter + "  " + "-" +newPay);
		}

	}


	public void chargeAmount(String creditCardNumber, float newCharge, String pin) throws CreditCardUnknownException, CardExpiredException,
	IncorrectPinException {
		if(ch.searchCreditCard(creditCardNumber) == null) {
			throw new CreditCardUnknownException(msgUnknown);
		}
		if(!ch.checkPIN(creditCardNumber, pin)) {
			throw new IncorrectPinException("El pin introducido no es correcto");
		}
		String fecha=ch.searchCreditCard(creditCardNumber).getDate().substring(3);
		String fechaActual=new SimpleDateFormat(dateFormat).format(new Date());
		int aux=Integer.parseInt(fecha);
		int aux1=Integer.parseInt(fechaActual.substring(3));
		if(aux<aux1) {
			throw new CardExpiredException("Error: card expired");
		}else {
			ch.searchCreditCard(creditCardNumber).charge(newCharge);
			String formatter = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
			ch.searchCreditCard(creditCardNumber).setMovement(formatter + "  " + "+" + newCharge);
		}
	}

	public List<String> consultMovements(String creditCardNumber, String pin) throws CreditCardUnknownException, IncorrectPinException {
		List<String> result=new ArrayList();
		if(ch.searchCreditCard(creditCardNumber) == null) {
			throw new CreditCardUnknownException("Error: credit card unknown");
		}
		else {

			boolean check=ch.checkPIN(creditCardNumber,pin);
			if(check) {
				result=ch.searchCreditCard(creditCardNumber).getMovements();
				return result;
			}
			else {
				throw new IncorrectPinException("Pin incorrecto");
			}

		}
	}

	public float consultBalance(String creditCardNumber, String pin) throws CreditCardUnknownException, 
			IncorrectPinException {
		if(ch.searchCreditCard(creditCardNumber) == null) {
			throw new CreditCardUnknownException("msgUnknown");
		}else {
			boolean res=ch.checkPIN(creditCardNumber,pin);
			if(res) {
				return ch.searchCreditCard(creditCardNumber).getAmount();
			}
			else {
				throw new IncorrectPinException("Pin incorrecto");
			}
		}

	}
}
