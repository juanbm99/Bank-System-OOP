package model;

import exceptions.CreditCardNumberOutOfRangeException;
import exceptions.CreditCardUnknownException;
import exceptions.IncorrectPinException;
import exceptions.NotEnoughMoneyException;

public interface PrepaidSystemInterface {
	/*
	 * Creates a new Object Credit Card with name,
	 * surname, pin an amount supplied. Adds this new
	 * card to the Credit Card List
	 */
	
	public void buyNewCard(String name, String surname, String pin, float amount) throws CreditCardNumberOutOfRangeException;
	
	/*
	 * Subtracts the amount to pay
	 * from the current balance of the
	 * credit card identified by its credit
	 * card number and its pin
	 */
	
	public void payAmount(String creditCardNumber, float newPay, String pin) throws CreditCardUnknownException, NotEnoughMoneyException;
	
	/*
	 * Charges the amount supplied on the 
	 * credit card identified by its number 
	 * and its pin
	 */
	
	public void chargeAmount(String creditCardNumber, float newCharge, String pin) throws CreditCardUnknownException;
	
	/*
	 * searches all the movements 
	 * related with the credit card 
	 * identified by its number and
	 * its pin
	 */
	
	public void consultMovements(String creditCardNumber, String pin) throws CreditCardUnknownException, IncorrectPinException;
	
	/*
	 * Consults the balance of
	 * the credit card identified by 
	 * its number and its pin
	 */
	
	public void consultBalance(String creditCardNumber, String pin) throws CreditCardUnknownException, IncorrectPinException;


}
