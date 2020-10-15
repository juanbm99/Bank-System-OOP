package model;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;




public class CardHistory {
	private List<CreditCard> creditCardList;
	public CardHistory() {
		creditCardList= new ArrayList<>();
		// add(new CreditCard("Tarjeta","Inicial","0000",(float)0.0,"000000000000","20-12-2019"))
	}


	public List<CreditCard> getList() {
		return creditCardList;
	}

	public void setList(List<CreditCard> lista) {
		this.creditCardList=lista;
	}

	/*
	 * Returns the credit card
	 * identified by its number
	 */
	public CreditCard searchCreditCard(String creditCardNumber) {
		CreditCard cc = null;
		boolean found = false;
		for(int i=0; i<creditCardList.size() && !found; i++) {
			if(Long.parseLong(creditCardList.get(i).getNumber()) == Long.parseLong(creditCardNumber)) {
				found = true;
				cc = creditCardList.get(i);
			}
		}

		return cc;
	}

	/*
	 * Checks if the pin provided 
	 * corresponds to the credit card identified
	 * by its number
	 */
	public boolean checkPIN(String creditCardNumber, String pin) {
		for (int i=0;i<creditCardList.size();i++) {
			if(creditCardList.get(i).getNumber().equals(creditCardNumber)) {
				String pinHashed=creditCardList.get(i).getSHA256(pin);
				if(creditCardList.get(i).getPin().equals(pinHashed)) {
					return true;
				}
			}
		}
		return false;
	}
	public void save(ArrayList<CreditCard> creditCardList) throws IOException  {
		String file="DataBase.txt";
		FileOutputStream fout=null; 
		ObjectOutputStream oos=null;
		
		try {
			fout=new FileOutputStream (file);
			oos= new ObjectOutputStream(fout);
			oos.writeObject(creditCardList);
		} finally {
			try { if (oos != null) oos.close(); } catch(IOException e) {Logger.getLogger("Exception");}
			try { if (fout!= null) fout.close(); } catch(IOException e) {Logger.getLogger("Exception");}
			

		}	
			}


			public List<CreditCard> read() throws ClassNotFoundException, IOException  {
				FileInputStream fin= null;
				ObjectInputStream ois = null;
				List<CreditCard> lista=new ArrayList();

				try {
					fin= new FileInputStream ("DataBase.txt");
					ois = new ObjectInputStream(fin);
					lista=(ArrayList<CreditCard>)ois.readObject();
				}

				finally {	
					try { if (ois != null) ois.close(); } catch(IOException e) {Logger.getLogger("Exception");}
					try { if (fin!= null) fin.close(); } catch(IOException e) {Logger.getLogger("Exception");}
					
				}
				return lista;

			}




		}
