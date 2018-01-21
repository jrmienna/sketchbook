package examples.banking;

public class CheckingsAccount implements Account {
	
	String name;
	double balance;
	
	public CheckingsAccount(String name, double balance) {
		this.name = name;
		this.balance = balance;
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
	
	public int compareTo(Account o) {
		if (this.balance < o.getBalance()) {
			return 1;
		} else if (this.balance > o.getBalance()) {
			return -1;
		} else {
			return 0;
		}
	}
}