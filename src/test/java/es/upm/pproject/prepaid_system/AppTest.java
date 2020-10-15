package es.upm.pproject.prepaid_system;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;






import exceptions.CreditCardNumberOutOfRangeException;
import model.CreditCard;
import model.PrepaidSystem;
import exceptions.CardExpiredException;
import exceptions.CreditCardUnknownException;
import exceptions.IncorrectPinException;
import exceptions.NotEnoughMoneyException;

/**
 * Unit test for PrepaidSystem App.
 */
public class AppTest 
{


	private PrepaidSystem ps;

	@BeforeEach
	public void testInicializacion () {
		ps=new PrepaidSystem();
	}
	
	@Test
	public void testSave() throws IOException {
		ps.getCardHistory().save((ArrayList<CreditCard>)ps.getCardHistory().getList());
		assertTrue(ps.getCardHistory().getList().isEmpty());
		
	}
	
	@Test
	public void testRead() throws IOException, ClassNotFoundException {
		ps.getCardHistory().read();
		assertTrue(ps.getCardHistory().getList().isEmpty());
		
	}
	
	@Test
	public void testSetList() {
		List<CreditCard> list=new ArrayList<CreditCard>();
		list.add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010"));
		ps.getCardHistory().setList(list);
		assertEquals(ps.getCardHistory().getList().get(0).getName(),"Pedro");
		
	}
	

	@Test
	public void testChangePin() throws IncorrectPinException
	{
		CreditCard cc=new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010");
		ps.getCardHistory().getList().add(cc);
		assertTrue(ps.getCardHistory().getList().get(ps.getCardHistory().getList().size()-1).changePIN("1333", "0000"));

	}

	@Test
	public void testChangePin2() throws IncorrectPinException
	{
		CreditCard cc=new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010");
		ps.getCardHistory().getList().add(cc);
		assertThrows(IncorrectPinException.class, () -> {
			ps.getCardHistory().getList().get(ps.getCardHistory().getList().size()-1).changePIN("2333", "0000");
		});

	}
	

	@Test
	public void testSearchCreditCard()
	{
		CreditCard cc=new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010");
		ps.getCardHistory().getList().add(cc);
		assertFalse(ps.getCardHistory().searchCreditCard(cc.getNumber())==null);

	}
	@Test
	public void testSearchCreditCard2()
	{
		CreditCard cc=new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010");
		ps.getCardHistory().getList().add(cc);
		assertTrue(ps.getCardHistory().searchCreditCard(cc.getNumber()).equals(cc));
	}
	@Test
	public void testCheckPin()
	{
		CreditCard cc=new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010");
		ps.getCardHistory().getList().add(cc);
		assertTrue(ps.getCardHistory().checkPIN("999999000000", "1333"));

	}

	@Test
	public void testCheckPin2()
	{
		CreditCard cc=new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010");
		ps.getCardHistory().getList().add(cc);
		assertFalse(ps.getCardHistory().checkPIN("999999000000", "1331"));
	}

	@Test
	public void testBuyCard() throws CreditCardNumberOutOfRangeException
	{
		
		ps.buyNewCard("Juan", "Bernal", "3344", (float)500.0);	
		assertFalse(ps.getCardHistory().getList().isEmpty());
	}

	

	@Test
	public void testBuyCard3() throws CreditCardNumberOutOfRangeException 
	{
		int aux1=ps.getCardHistory().getList().size();
		ps.buyNewCard("Juan", "Bernal", "3344", (float)500.0);
		ps.buyNewCard("Juan", "Bernal", "3344", (float)500.0);
		ps.buyNewCard("Juan", "Bernal", "3344", (float)500.0);
		ps.buyNewCard("Juan", "Bernal", "3344", (float)500.0);
		ps.buyNewCard("Juan", "Bernal", "3344", (float)500.0);
		int aux2=ps.getCardHistory().getList().size();
		assertEquals(aux2,aux1+6,0.0001);
	}

	@Test
	public void testBuyCard4() throws CreditCardNumberOutOfRangeException
	{

		String names[]= {"Tarjeta","Juan","Pedro","Lucas","Rodrigo"};
		ps.buyNewCard("Juan", "Bernal", "3344", (float)500.0);
		ps.buyNewCard("Pedro", "Gonzalez", "2134", (float)100.0);
		ps.buyNewCard("Lucas", "Garcia", "2021", (float)200.0);
		ps.buyNewCard("Rodrigo", "Perez", "1119", (float)300.0);
		int j=0;
		for (int i=0;i<ps.getCardHistory().getList().size();i++) {
			if(j==Integer.parseInt(ps.getCardHistory().getList().get(i).getNumber().substring(11))) {
				assertTrue(names[i].equals(ps.getCardHistory().getList().get(i).getName()));
			}
			j++;
		}

	}
	
	@Test
	public void testBuyCardOutOfRangeException() throws CreditCardNumberOutOfRangeException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1234",(float)10.0,"999999999999","12/2020"));

		assertThrows(CreditCardNumberOutOfRangeException.class, () -> {
			ps.buyNewCard("Tarjeta", "Erronea", "8878", (float)23.0);
		});
	}

	@Test
	public void testPayAmount() throws IncorrectPinException, CreditCardUnknownException, NotEnoughMoneyException, CardExpiredException
	{

		CreditCard cd=new CreditCard("Guillermo","Lopez","1333",(float)250.0,"123456789012","03/2022");
		ps.getCardHistory().getList().add(cd);
		ps.payAmount("123456789012", (float)100.0, "1333");


		for(int i=0;i<ps.getCardHistory().getList().size();i++) {
			if(ps.getCardHistory().getList().get(i).getName().equals("Guillermo")) {
				float aux1=ps.getCardHistory().getList().get(i).getAmount();
				float aux2=(float)150.0;
				assertEquals(aux1,aux2,0.0001);
			}
		}
	}
	@Test
	public void testPayAmount2() throws IncorrectPinException, CreditCardUnknownException, NotEnoughMoneyException, CardExpiredException
	{

		CreditCard cd=new CreditCard("Guillermo","Lopez","1333",(float)250.0,"123456789012","03/2022");
		ps.getCardHistory().getList().add(cd);
		ps.payAmount("123456789012", (float)250.0, "1333");
		for(int i=0;i<ps.getCardHistory().getList().size();i++) {
			if(ps.getCardHistory().getList().get(i).getName().equals("Guillermo")) {
				float aux1=ps.getCardHistory().getList().get(i).getAmount();
				float aux2=(float)0.0;
				assertEquals(aux1,aux2,0.0001);
			}
		}
	}
	@Test
	public void testPayAmountCardUnknownException() throws IncorrectPinException, CreditCardUnknownException, 
	NotEnoughMoneyException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		assertThrows(CreditCardUnknownException.class, () -> {
			ps.payAmount("123456789013", (float)100.0, "1333");
		});
	}
	@Test
	public void testPayAmountNotMoneyException() throws IncorrectPinException, CreditCardUnknownException, 
	NotEnoughMoneyException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)10.0,"999999000000","12/2020"));
		assertThrows(NotEnoughMoneyException.class, () -> {
			ps.payAmount("999999000000", (float)100.0, "1333");
		});
	}
	@Test
	public void testPayAmountCardExpiredException() throws IncorrectPinException, CreditCardUnknownException, 
	NotEnoughMoneyException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010"));
		assertThrows(CardExpiredException.class, () -> {
			ps.payAmount("999999000000", (float)100.0, "1333");
		});
	}

	@Test
	public void testPayAmountIncorrectPinException() throws IncorrectPinException, CreditCardUnknownException, 
	NotEnoughMoneyException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010"));
		assertThrows(IncorrectPinException.class, () -> {
			ps.payAmount("999999000000", (float)100.0, "1313");
		});
	}
	@Test
	public void testChargeAmount() throws IncorrectPinException, CreditCardUnknownException, CardExpiredException
	{
		CreditCard cd=new CreditCard("Guillermo","Lopez","1333",(float)250.0,"123456789012","03/2022");
		ps.getCardHistory().getList().add(cd);
		ps.chargeAmount("123456789012", (float)250.0, "1333");
		for(int i=0;i<ps.getCardHistory().getList().size();i++) {
			if(ps.getCardHistory().getList().get(i).getName().equals("Guillermo")) {
				float aux1=ps.getCardHistory().getList().get(i).getAmount();
				float aux2=(float)500.0;
				assertEquals(aux1,aux2,0.0001);
			}
		}
	}
	@Test
	public void testChargeAmount2() throws IncorrectPinException, CreditCardUnknownException, CardExpiredException
	{
		CreditCard cd=new CreditCard("Guillermo","Lopez","1333",(float)0.0,"123456789012","03/2022");
		ps.getCardHistory().getList().add(cd);
		ps.chargeAmount("123456789012", (float)11550.0, "1333");

		for(int i=0;i<ps.getCardHistory().getList().size();i++) {
			if(ps.getCardHistory().getList().get(i).getName().equals("Guillermo")) {
				float aux1=ps.getCardHistory().getList().get(i).getAmount();
				float aux2=(float)11550.0;
				assertEquals(aux1,aux2,0.0001);
			}
		}
	}
	@Test
	public void testChargeAmountCardUnknownException() throws IncorrectPinException, CreditCardUnknownException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		assertThrows(CreditCardUnknownException.class, () -> {
			ps.chargeAmount("123456789013", (float)100.0, "1333");
		});
	}
	@Test
	public void testChargeAmountCardExpiredException() throws IncorrectPinException, CreditCardUnknownException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010"));
		assertThrows(CardExpiredException.class, () -> {
			ps.chargeAmount("999999000000", (float)100.0, "1333");
		});
	}

	@Test
	public void testChargeAmountIncorrectPinException() throws IncorrectPinException, CreditCardUnknownException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2010"));
		assertThrows(IncorrectPinException.class, () -> {
			ps.chargeAmount("999999000000", (float)100.0, "1313");
		});
	}
	@Test
	public void ConsultMovementsCardUnknownException() throws CreditCardUnknownException, IncorrectPinException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		assertThrows(CreditCardUnknownException.class, () -> {
			ps.consultMovements("999999000001", "1333");

		});
	}
	@Test
	public void ConsultMovementsIncorrectPinException() throws CreditCardUnknownException, IncorrectPinException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		assertThrows(IncorrectPinException.class, () -> {
			ps.consultMovements("999999000000", "1332");

		});
	}

	@Test
	public void ConsultMovements () throws CreditCardUnknownException, IncorrectPinException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		assertTrue(ps.consultMovements("999999000000", "1333").isEmpty());
	}

	@Test
	public void ConsultMovements2 () throws CreditCardUnknownException, IncorrectPinException, NotEnoughMoneyException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));

		ps.payAmount("999999000000", (float)200.0, "1333");

		assertFalse(ps.consultMovements("999999000000", "1333").isEmpty());
	}

	@Test
	public void ConsultMovements3 () throws CreditCardUnknownException, IncorrectPinException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));	
		ps.chargeAmount("999999000000", (float)200.0, "1333");
		assertFalse(ps.consultMovements("999999000000", "1333").isEmpty());
	}

	@Test
	public void ConsultMovements4 () throws CreditCardUnknownException, IncorrectPinException, NotEnoughMoneyException, CardExpiredException
	{
		String res ="";
		boolean flag=false;
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000004","12/2020"));
		ps.payAmount("999999000004", (float)200.0, "1333");
		List<String> moves=ps.consultMovements("999999000004", "1333");
		for(int i=0;i<moves.size();i++) {
			for(int w=0;w<moves.get(i).length();w++) {
				if(moves.get(i).charAt(w) == '-') {
					flag=true;
				}
				if(flag) {
					res=res+moves.get(i).charAt(w);
				}
			}
		}
		assertTrue(res.equals("-200.0"));
	}

	@Test
	public void ConsultBalanceCardUnknownException() throws CreditCardUnknownException, IncorrectPinException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		assertThrows(CreditCardUnknownException.class, () -> {
			ps.consultBalance("999999000001", "1333");

		});
	}

	@Test
	public void ConsultBalanceIncorrectPinException() throws CreditCardUnknownException, IncorrectPinException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		assertThrows(IncorrectPinException.class, () -> {
			ps.consultBalance("999999000000", "1332");
		});
	}
	@Test
	public void ConsultBalance () throws CreditCardUnknownException, IncorrectPinException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		float aux1=ps.consultBalance("999999000000", "1333");
		float aux2=(float)1000.0;
		assertEquals(aux1,aux2,0.0001);
	}

	@Test
	public void ConsultBalance2 () throws CreditCardUnknownException, IncorrectPinException, NotEnoughMoneyException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		ps.payAmount("999999000000", (float)200.0, "1333");
		float aux1=(float)1000.0-ps.consultBalance("999999000000", "1333");
		float aux2=(float)200.0;
		
		assertEquals(aux1,aux2,0.0001);
	}

	@Test
	public void ConsultBalance3 () throws CreditCardUnknownException, IncorrectPinException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		ps.chargeAmount("999999000000", (float)200.0, "1333");
		float aux1=ps.consultBalance("999999000000", "1333");
		float aux2=(float)1200.0;
		assertEquals(aux1,aux2,0.0001);
	}

	@Test
	public void ConsultBalance4 () throws CreditCardUnknownException, IncorrectPinException, CardExpiredException
	{
		ps.getCardHistory().getList().add(new CreditCard("Pedro","Lopez","1333",(float)1000.0,"999999000000","12/2020"));
		ps.chargeAmount("999999000000", (float)200.0, "1333");
		float aux1=ps.consultBalance("999999000000", "1333")-(float)1100.0;
		float aux2=(float)100.0;
		assertEquals(aux1,aux2,0.0001);
	}









}
