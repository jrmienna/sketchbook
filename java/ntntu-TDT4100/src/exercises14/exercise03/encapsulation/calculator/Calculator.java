package exercises14.exercise03.encapsulation.calculator;

public class Calculator {

	private double firstOperand;
	private double secondOperand;
	private char operator;

	public double getFirstOperand() {
		return firstOperand;
	}

	public double getSecondOperand() {
		return secondOperand;
	}

	public char getOperator() {
		return operator;
	}

	public Calculator() {
		firstOperand = Double.NaN;
		secondOperand = Double.NaN;
		operator = ' ';
	}

	public void setFirstOperand(double arg) {
		firstOperand = arg;
	}

	public void setSecondOperand(double arg) {
		secondOperand = arg;
	}

	public void setOperator(char operator) {
		if("+-*/^%".contains("" + operator)) {
			this.operator = operator;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public double calculateResult() {
		if (operator == '+') {
			return firstOperand + secondOperand;
		} else if (operator == '-') {
			return firstOperand - secondOperand;
		} else if (operator == '*') {
			return firstOperand * secondOperand;
		} else if (operator == '/'){
			if (secondOperand == 0) {
				throw new IllegalStateException();
			}
			return firstOperand / secondOperand;
		} else if (operator == '^') {
			return Math.pow(firstOperand, secondOperand);
		} else if (operator == '%') {
			return firstOperand % secondOperand;
		} else {
			throw new IllegalStateException();
		}
		
	}

	public void calculateAndSetFirstOperand() {
		firstOperand = calculateResult();

	}

	public String toString() {
		return "" + calculateResult();
	}
		
}
