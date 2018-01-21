package examples.banking;

public interface Account extends Comparable<Account> {
	
	// Interface for accounts
	
	public String getName();
	
	public void deposit(double amount);

	public void withdraw(double amount);
	
	public double getBalance();
}
