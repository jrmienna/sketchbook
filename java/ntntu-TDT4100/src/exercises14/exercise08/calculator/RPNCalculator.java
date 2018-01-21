package exercises14.exercise08.calculator;

import java.util.Stack;

public class RPNCalculator extends SimpleCalculator {

	private Stack<Double> operands = new Stack<Double>();
	
	@Override
	public void takeInputNumber(double number) {
		operands.push(number);
	}
	
	@Override
	public void takeInputOperator(char operator) {
		setLeftOperand(operands.pop());
		setOperator(operator);
		setRightOperand(operands.pop());
	};
	
	@Override
	public boolean hasOutput() {
		return super.hasOutput();
	}
	
	@Override
	public double getOutput() {
		return super.getOutput();
	}
}
