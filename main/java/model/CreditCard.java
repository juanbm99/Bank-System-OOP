package model;


import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import exceptions.IncorrectPinException;

public class CreditCard implements Serializable{

	private String name;
	private String surname;
	private String pin;
	private float amount;
	private String number;
	private String date;
	private ArrayList<String> movements;
	

	public CreditCard(String name, String surname, String pin, float amount, String number, String date) {
		this.name = name;
		this.surname = surname;
		this.pin=getSHA256(pin);
		this.amount = amount;
		this.number = number;
		this.date=date;
		this.movements = new ArrayList<>();
	}

	public boolean changePIN(String oldPin, String newPin) throws IncorrectPinException {
		String oldpinHashed=getSHA256(oldPin);
		if (!pin.equals(oldpinHashed)) {
			throw new IncorrectPinException("Pin incorrecto");
		}
		else {
			setPin(getSHA256(newPin));
			return true;
		}
		
	}

	public String getSHA256(String input){
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			Logger.getLogger("Failed to Hash");
		}
		return toReturn;
	}
	public String getNumber() {
		return number;
	}


	public float getAmount() {
		return amount;
	}

	public void charge(float newAmount) {
		amount = amount + newAmount;
	}

	public void pay(float newAmount) {
		amount = amount - newAmount;
	}

	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getLast4Digits(String number) {
		return number.substring(8);
	}
	

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	
	public void setMovement(String movement) {
		movements.add(movement);
	}

	public List<String> getMovements() {
		for(int i=movements.size()-1; i>=0; i--) {
			Logger.getLogger(movements.get(i));
		}
		return movements;
		
	}
	
}
