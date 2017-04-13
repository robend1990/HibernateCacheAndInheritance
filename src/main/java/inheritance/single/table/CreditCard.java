package inheritance.single.table;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {

	protected String cardNumber;
	
	protected String expMonth;

	protected String expYear;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	
	
}
