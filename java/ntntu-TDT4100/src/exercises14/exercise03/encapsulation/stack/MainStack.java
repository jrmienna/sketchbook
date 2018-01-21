package exercises14.exercise03.encapsulation.stack;

import java.util.Scanner;

public class MainStack {
	private Stack stack;
	private RandomStringGenerator generator;
	private Printer print;
	
	private void init() {
		stack = new Stack();
		generator = new RandomStringGenerator();
		print = new Printer();
	}
	private void run() {
		Scanner in = new Scanner(System.in);
		print.printWelcome();
		
		String command = "";
		while (true) {
			command = in.next();
			if (command.toString().equals("pu")) {
				print.printLevels();
				int levels = in.nextInt();
				for (int i = 0; i < levels; i++) {
					stack.push(generator.getRandomString());					
				}
				for (int i = 0; i < stack.getSize(); i++) {
					System.out.println("_" + stack.peek(i));
				}
			}
			else if(command.toString().equals("pe")) {
				print.printIndex();
				int index = in.nextInt();
				System.out.println(stack.peek(index));
			}
			else if(command.toString().equals("po")) {
				stack.pop();
				for (int i = 0; i < stack.getSize(); i++) {
					System.out.println("_" + stack.peek(i));
				}
			}
			else if(command.toString().equals("cl")) {
				stack.clear();
			}
			else if(command.toString().equals("s")) {
				System.out.println(stack.getSize());
			}
			else if (command.toString().equals("esc")){
				print.printGoodbye();
				break;
			}
			else {
				print.printWarning();
			}
		}
		in.close();
	}
	public static void main(String[] args) {
		MainStack program = new MainStack();
		program.init();
		program.run();
		
	}


}
