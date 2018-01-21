package exercises14.exercise03.encapsulation.upordown;

import java.util.Scanner;

public class Main {
	
	public UpOrDownCounter counter;
	public Scanner in;
	
	public void init() {
		counter = new UpOrDownCounter(0,10);
		in = new Scanner(System.in);
	}
	public void run() {
		System.out.println(counter.toString() + " " + counter.count());
		System.out.println("Type counter increment: ");
		while(in.hasNextInt()) {
			int n = in.nextInt();
			if (counter.count(n)){
				System.out.println("The End");
				break;
			}
			System.out.println(counter.toString());
		}
	}
	public static void main(String[] args) {
		Main program = new Main();
		program.init();
		program.run();
	}
}
