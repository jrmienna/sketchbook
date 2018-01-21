package exercises14.exercise01.digit;

/**
 * @author John R. Mienna
 */

public class Digit {
	
	/**
	 *  The base for the number system which will be used
	 */
	private int base;
	
	/** 
	 * The int this class is representing/wrapping
	 */
	private int digit;

	/**
	 * Constructor takes in a positive integer
	 * 
	 * @param i
	 * @throws IllegalArgumentException
	 */
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

	/**
	 * Increments the digit by one. If the digit reaches the base, it is set to zero.
	 * 
	 * @return true if digit is set to zero
	 */
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
