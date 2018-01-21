package exercises14.exercise01.account;

/**
 * Operates data in a bank account. 
 * Account balance can be changed by deposits or interest.
 * @author John Rikard Mienna
 * 
 */
public class Account {
	
	public double balance = 0;
	public double interestRate = 12;
	
	/**
	 * Deposits money to the bank account.
	 * @param amount the amount to deposit
	 */
	public void deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
		}
	}
	
	/**
	 * Calculates the interest and adds it to the bank account.
	 */
	public void addInterest() {
		double interest = this.balance * this.interestRate/100;
		this.balance += interest;
	}
	
	/**
	 * Converts the account balance to a string.
	 */
	public String toString() {
		return "Account balance: " + balance;
	}
	// @enduml
}
