package exercises14.exercise02.calculator;

public class Calculator {

	public double leftOperand;
	public double rightOperand;
	public char operator;

	public Calculator() {
		leftOperand = Double.NaN;
		rightOperand = Double.NaN;
		operator = ' ';
	}
	
	public double getFirstOperand() {
		return leftOperand;
	}

	public double getSecondOperand() {
		return rightOperand;
	}

	public char getOperator() {
		return operator;
	}


	public void setFirstOperand(double arg) {
		leftOperand = arg;
	}

	public void setSecondOperand(double arg) {
		rightOperand = arg;
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
			return leftOperand + rightOperand;
		} else if (operator == '-') {
			return leftOperand - rightOperand;
		} else if (operator == '*') {
			return leftOperand * rightOperand;
		} else if (operator == '/'){
			if (rightOperand == 0) {
				throw new IllegalStateException();
			}
			return leftOperand / rightOperand;
		} else if (operator == '^') {
			return Math.pow(leftOperand, rightOperand);
		} else if (operator == '%') {
			return leftOperand % rightOperand;
		} else {
			throw new IllegalStateException();
		}
		
	}

	public void calculateAndSetFirstOperand() {
		leftOperand = calculateResult();

	}

	public String toString() {
		return "" + calculateResult();
	}
		
}
