package exercises14.exercise03.encapsulation.location;

import java.util.Scanner;

public class Main {
	public Location myLocation;
	public Scanner in;
	
	public void init() {
		myLocation = new Location();
		in = new Scanner(System.in);
	}
	public void run() {
		System.out.println(myLocation.toString());
		while (true) {
			String command = in.next();
			if (command.equals("up")) {
				myLocation.up();
			}
			else if(command.equals("down")) {
				myLocation.down();
			}
			else if(command.equals("left")){
				myLocation.left();
			}
			else if(command.equals("right")) {
				myLocation.right();
			}
			else if(command.equals("bye")) {
				break;
			}
			System.out.println(myLocation.toString());
		}
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.init();
		program.run();
	}
}
	
