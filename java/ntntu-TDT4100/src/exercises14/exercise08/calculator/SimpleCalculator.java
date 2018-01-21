package exercises14.exercise08.calculator;

public class SimpleCalculator {

	private final static String OPERATORS = "+-*/";

	private double leftOperand, rightOperand;
	private char operator;
	private boolean hasOutput;

	public SimpleCalculator() {
		leftOperand = Double.NaN;
		rightOperand = Double.NaN;
		hasOutput = false;
		operator = Character.UNASSIGNED;
	}

	protected void setLeftOperand(double operand) {
		this.leftOperand = operand;
	}

	protected void setRightOperand(double operand) {
		this.rightOperand = operand;
	}

	public void setOperator(char operator) {
		if (!OPERATORS.contains("" + operator)) {
			throw new IllegalArgumentException("Illegal operator");
		}
		this.operator = operator;
	}

	protected double getResult() {
		return this.leftOperand;
	}

	public void takeInputNumber(double number) {
		if(hasOutput) {
			setRightOperand(number);
		} else {
			setLeftOperand(number);
		}
	}

	public void takeInputOperator(char operator) {
		if (leftOperand != Double.NaN) {
			setOperator(operator);
		}
	}

	public boolean hasOutput() {
		return this.operator != Character.UNASSIGNED;
	}

	public double getOutput() {
		if (operator == '+') {
			leftOperand = leftOperand + rightOperand;
		}
		if (operator == '-') {
			leftOperand = leftOperand - rightOperand;
		}
		if (operator == '*') {
			leftOperand = leftOperand * rightOperand;
		}
		if (operator == '/') {
			if (rightOperand == 0) {
				throw new IllegalStateException();
			}
			leftOperand = leftOperand / rightOperand;
		}
		if (operator == '^') {
			leftOperand = Math.pow(leftOperand, rightOperand);
		}
		this.operator = Character.UNASSIGNED;
		return leftOperand;
	}
}
