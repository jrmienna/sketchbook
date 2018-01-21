package examples.banking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Bank implements Iterable<Account>{
	
	ArrayList<Account> accounts;
	boolean alphanumerically;
	
	public Bank(boolean alphanumerically) {
		accounts = new ArrayList<Account>();
		this.alphanumerically = alphanumerically;
	}
	
	// Method for iterating through all accounts in a bank 
	// and accruing the interest of savings accounts
	public void accrueInterest() {
		// Notation for invoking default iterator (the one that 
		// is returned when iterator() is called)
		for (Account account : this) {
			// Check whether or not an account is a savings account
			if (account instanceof SavingsAccount) {
				// Cast from Account to SavingsAccount before
				// calling accrueInterest()
				((SavingsAccount) account).accrueInterest();
			}
		}
	}
	
	public static void main(String[] args) {
		Bank bank = new Bank(false);
		SavingsAccount savingsAccount1 = new SavingsAccount("David", 400, 0.04);
		SavingsAccount savingsAccount2 = new SavingsAccount("Arne", 100, 0.05);
		CheckingsAccount checkingsAccount1 = new CheckingsAccount ("Per", 150);
		CheckingsAccount checkingsAccount2 = new CheckingsAccount("Berit", 300);
		bank.accounts.add(savingsAccount1);
		bank.accounts.add(savingsAccount2);
		bank.accounts.add(checkingsAccount1);
		bank.accounts.add(checkingsAccount2);
		System.out.println(bank.accounts);
		Collections.sort(bank.accounts);
		System.out.println(bank.accounts);
		Collections.sort(bank.accounts, new AccountNameComparator());
		System.out.println(bank.accounts);
		
		Iterator<?> iterator1 = new BankIterator(bank, true);
		while (iterator1.hasNext()) {
			System.out.println(iterator1.next());
		}

		Iterator<?> iterator2 = bank.iterator();
		while (iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}
		bank.accrueInterest();
		// Notation for iteration
		for (Account account : bank) {
			System.out.println(account);
		}
		
	}

	public Iterator<Account> iterator() {
		return new BankIterator(this, alphanumerically);
	}
}