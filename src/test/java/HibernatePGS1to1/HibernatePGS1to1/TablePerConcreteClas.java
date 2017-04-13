package HibernatePGS1to1.HibernatePGS1to1;

import inheritance.table.per.concrete.clas.BankAccount;
import inheritance.table.per.concrete.clas.CreditCard;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TablePerConcreteClas {

	@Test
	public void clearDB(){
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from inheritance.table.per.concrete.clas.BillingDetails").executeUpdate();
        session.flush();
        tx.commit();
        session.close();
	}
	
	@Test
	public void saveBankAccount(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setOwner("Owner");
        bankAccount.setBankname("mBank");
        bankAccount.setAccount("Account");
        bankAccount.setSwift("swift");
        session.save(bankAccount);
        tx.commit();
        List<BankAccount> accounts = session.createQuery("from inheritance.table.per.concrete.clas.BankAccount ba").list();
        assertEquals(1, accounts.size());
        bankAccount = accounts.get(0);
        assertEquals("Owner", bankAccount.getOwner());
        assertEquals("mBank", bankAccount.getBankname());
        assertEquals("Account", bankAccount.getAccount());
        assertEquals("swift", bankAccount.getSwift());
        session.close();
	}
	
	@Test
	public void saveCreditCard(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        CreditCard creditCard = new CreditCard();
        creditCard.setOwner("rdrewniak");
        creditCard.setCardNumber("asdasd123");
        creditCard.setExpMonth("12");
        creditCard.setExpYear("2018");
        session.save(creditCard);
        tx.commit();
        List<CreditCard> creditCards = session.createQuery("from inheritance.table.per.concrete.clas.CreditCard cd").list();
        assertEquals(1, creditCards.size());
        creditCard = creditCards.get(0);
        assertEquals("rdrewniak", creditCard.getOwner());
        assertEquals("asdasd123", creditCard.getCardNumber());
        assertEquals("12", creditCard.getExpMonth());
        assertEquals("2018", creditCard.getExpYear());
        session.close();
	}
	
}
