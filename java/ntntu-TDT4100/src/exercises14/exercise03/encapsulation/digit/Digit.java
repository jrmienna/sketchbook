package exercises14.exercise03.encapsulation.digit;

public class Digit {
	private int base;

	private int digit;

	public Digit(int i) {
		if (i >= 0) {
			this.base = i;
			this.digit = 0;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getValue() {
		return this.digit;
	}

	public boolean increment() {
		if (this.digit == this.base - 1) {
			this.digit = 0;
			return true;
		} else {
			this.digit++;
			return false;
		}
	}

	public String toString() {
		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return "" + str.charAt(this.digit);
	}
}
