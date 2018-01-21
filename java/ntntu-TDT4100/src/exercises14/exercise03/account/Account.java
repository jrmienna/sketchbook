package exercises14.exercise03.account;

public class Account {
	
	private double balance;
	private double interestRate;
	
	public Account(double balance, double interestRate) {
		if(balance > 0 && interestRate > 0) {
			this.balance = balance;
			this.interestRate = interestRate;
		}
		else {
			throw new IllegalArgumentException("Illegal argument in constructor");
		}
	}
	public void deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
		}
		else {
			throw new IllegalArgumentException("Illegal argument in double amount");
		}
	}
	public void withdraw(double amount) {
		if (amount >= 0 && amount <= balance) {
			this.balance -= amount;
		}
		else if(amount < 0) {
			throw new IllegalArgumentException("Illegal argument in double amount");
		}
		else {
			throw new IllegalStateException("Illegal State in double balance");
		}
	}
	public double getBalance() {
		return balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		if(interestRate > 0) {
			this.interestRate = interestRate;
		}
		else {
			throw new IllegalArgumentException("Illegal argument: double interestRate");
		}
	}
	public void addInterest() {
		double interest = this.balance * this.interestRate/100;
		this.balance += interest;
	}
	public String toString() {
		return "Account balance: " + balance;
	}
}
