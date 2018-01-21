package examples.banking;

import java.util.Collections;
import java.util.Iterator;

public class BankIterator implements Iterator<Account> {
	
	Bank bank;
	int iterationIndex;
	
	public BankIterator(Bank bank, boolean lexically) {
		this.bank = bank;
		this.iterationIndex = 0;
		if (lexically) {
			// Use AccountNameComparator object to sort lexically
			Collections.sort(bank.accounts, new AccountNameComparator());
		} else {
			// Use compareTo method to sort accounts by balance
			Collections.sort(bank.accounts);
		}
	}
	
	public boolean hasNext() {
		return iterationIndex < bank.accounts.size();
	}

	@Override
	public Account next() {
		return bank.accounts.get(iterationIndex++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}