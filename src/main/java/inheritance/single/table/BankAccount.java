package inheritance.single.table;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails{

	protected String account;
	
	protected String bankname;

	protected String swift;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}
	
	
}
