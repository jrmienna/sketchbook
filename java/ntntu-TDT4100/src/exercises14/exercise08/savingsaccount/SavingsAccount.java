package exercises14.exercise08.savingsaccount;

public class SavingsAccount implements Account {

	public SavingsAccount(double renteFot) {
		/*- konstruktør som tar inn rentefoten på kontoen 
		 * (et desimaltall, f.eks. 0.05 tilsvarer en rente på 5%). 
		 * Åpningsbalansen skal være 0.
		 */
	}

	public void endYearUpdate() {
		// - forrenter kontobalansen basert på rentefoten.
	}

	@Override
	public void deposit(double amount) {
		/*
		 * Øker kontobalansen med innskutt beløp. Merk at det innskutte beløpet
		 * må være positivt. Ved ulovlig innskudd skal en
		 * IllegalArgumentException utløses
		 */
	}

	@Override
	public void withdraw(double amount) {
		/*
		 * Minsker kontobalansen med beløpet som blir tatt ut. Merk at
		 * uttaksbeløpet må være positivt, ellers skal et unntak av typen
		 * IllegalArgumentException utløses. Dersom det ikke er dekning på
		 * kontoen (en SavingsAccount kan ikke ha negativ balanse) skal et
		 * unntak av typen IllegalStateException utløses.
		 */
	}

	@Override
	public double getBalance() {
		// returnerer kontobalansen.
		return 0;
	}

}
