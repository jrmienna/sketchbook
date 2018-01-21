package exercises14.exercise03.encapsulation.rpn;

import java.util.Stack;

public class RPNCalc {
	
	private Stack<Double> stack = new Stack<Double>();
	
	public void push (double arg) {
		stack.add(0, arg);
	}
	public double pop() {
		if (getSize() == 0) {
			return Double.NaN;			
		}
		else {
			Double temp = stack.get(0);
			stack.remove(0);
			return temp;
		}
		
	}
	public double peek() {
		return stack.peek();
	}
	public double peek(int index) {
		if (0 <= index && index < getSize()) {
			return stack.elementAt(index);
		}
		else {
			return Double.NaN;
		}
	}
	public int getSize() {
		return stack.size();
	}
	public void performOperation(char operator ) {
		if(getSize() > 1) {
			if(operator == '+'){
				push(peek(0) + peek(1));
				stack.removeElementAt(1);
				stack.removeElementAt(1);
			}
			else if(operator == '-') {
				push(peek(1) - peek(0));
				stack.removeElementAt(1);
				stack.removeElementAt(1);
			}
			else if(operator == '*') {
				push(peek(0) * peek(1));
				stack.removeElementAt(1);
				stack.removeElementAt(1);
			}
			else if(operator == '/') {
				push(peek(1) / peek(0));
				stack.removeElementAt(1);
				stack.removeElementAt(1);
			}
			else if(operator == '%') {
				push(peek(1) % peek(0));
				stack.removeElementAt(1);
				stack.removeElementAt(1);
			}
			else if(operator == '~') {
				push(peek(1));
				stack.remove(2);	
			}
			else if(operator == 'p') {
				push(Math.PI);	
			}
		}
	}
	public void remove(int index) {
		stack.remove(index);
	}
	public void clear() {
		stack.clear();
	}
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public String toString() {
		return stack.toString();
	}
}
