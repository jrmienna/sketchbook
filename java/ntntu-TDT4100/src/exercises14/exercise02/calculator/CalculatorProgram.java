package exercises14.exercise02.calculator;

import java.util.Scanner;

public class CalculatorProgram {

	public SimpleCalculator calculator;
	public Printer printer;

	public void init() {
		calculator = new SimpleCalculator();
		printer = new Printer();
	}

	public void run() {
		printer.printWelcome();
		Scanner in = new Scanner(System.in);
		
		int n = 100;
		while (n > 0) {
			if (in.hasNextDouble()) {
				calculator.setFirstOperand(in.nextDouble());
				calculator.setOperator(in.next().charAt(0));
				calculator.setSecondOperand(in.nextDouble());
				System.out.println(calculator.toString());
			} else if (in.hasNextLine()) {
				calculator.calculateAndSetFirstOperand();
				calculator.setOperator(in.next().charAt(0));
				calculator.setSecondOperand(in.nextDouble());
				System.out.println(calculator.toString());
			}
		}
		in.close();
	}

	public static void main(String[] args) {
		CalculatorProgram program = new CalculatorProgram();
		program.init();
		program.run();
	}

}
