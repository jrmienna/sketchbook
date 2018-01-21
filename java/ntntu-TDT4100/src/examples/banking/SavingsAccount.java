package examples.banking;

import java.util.ArrayList;
import java.util.Collections;

public class SavingsAccount implements Account {
	
	String name;
	double balance, interest;
	
	public SavingsAccount(String name, double balance, double interest) {
		this.name = name;
		this.balance = balance;
		this.interest = interest;
	}
	
	public String getName() {
		return name;
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException();
		} else {
			balance += amount;
		}
	}

	public void withdraw(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException();
		} else if (amount > balance) {
			throw new IllegalStateException();
		} else {
			balance -= amount;
		}
	}

	public double getBalance() {
		return balance;
	}
	
	public String toString() {
		return name + "'s checkings account: " + balance;
	}
	
	// Accrue interest of account based on balance and interest
	public void accrueInterest() {
		balance *= (1 + interest);
	}

	public int compareTo(Account o) {
		if (this.balance < o.getBalance()) {
			return 1;
		} else if (this.balance > o.getBalance()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		ArrayList<SavingsAccount> accounts = new ArrayList<SavingsAccount>();
		SavingsAccount savingsAccount1 = new SavingsAccount("David", 400, 0.04);
		SavingsAccount savingsAccount2 = new SavingsAccount("Arne", 100, 0.05);
		SavingsAccount savingsAccount3 = new SavingsAccount("Roger", 200, 0.02);
		accounts.add(savingsAccount1);
		accounts.add(savingsAccount2);
		accounts.add(savingsAccount3);
		System.out.println(accounts);
		Collections.sort(accounts);
		System.out.println(accounts);
	}
}