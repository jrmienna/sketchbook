package exercises14.exercise01.account;

import java.util.Scanner;

/*
 * @author John Rikard Mienna
 * 
 * @startuml
 * Klient --> Tjener : "deposit"
 * Tjener --> Klient : "amount?"
 * Klient --> Tjener : 2000
 * Tjener --> Klient : "account balance: 2000"
 * Klient --> Tjener : "add"
 * Tjener --> Klient : "account balance: 2240"
 * Klient --> Tjener : "bye"
 * 
 * @enduml
 */

public class Main {
	public Account myCheckings;
	public Scanner in;
	
	
	public void init() {
		myCheckings = new Account();
		in = new Scanner(System.in);
	}
	public void run() {
		System.out.println(myCheckings.toString());
		while (true) {
			String command = in.next();
			if (command.equals("deposit")) {
				System.out.println("Amount:");
				int amount = in.nextInt();
				myCheckings.deposit(amount);
			}
			else if(command.equals("add")) {
				myCheckings.addInterest();
			}
			else if(command.equals("bye")) {
				break;
			}
			System.out.println(myCheckings.toString());
		}
	}
	public static void main(String[] args) {
		Main program = new Main();
		program.init();
		program.run();
	}
}
