package exercises14.exercise03.account;

import java.util.Scanner;

public class AccountProgram {

	private Account myCheckings;
	private Printer printer;
	private Scanner in;

	private void init() {
		printer = new Printer();
		in = new Scanner(System.in);
		printer.newAccount();
		System.out.println("Initial balance: ");
		double balance = in.nextDouble();
		System.out.println("Interst Rate: ");
		double interestRate = in.nextDouble();
		myCheckings = new Account(balance, interestRate);
	}

	private void run() {
		printer.commandList();
		while (true) {
			String command = in.next();
			if (command.equals("d")) {
				System.out.println("Amount: ");
				double amount = in.nextDouble();
				myCheckings.deposit(amount);
				printer.currentCheckings(myCheckings.getBalance() - amount, myCheckings.getBalance());
			}
			else if (command.equals("w")) {
				System.out.println("Amount: ");
				double amount = in.nextDouble();
				myCheckings.withdraw(amount);
				printer.currentCheckings(myCheckings.getBalance() + amount, myCheckings.getBalance());
			}
			else if (command.equals("b")) {
				System.out.println(myCheckings.getBalance());
			}
			else if (command.equals("i")) {
				printer.interstRateCommandList();
				while (true) {
					command = in.next();
					if(command.equals("g")) {
						System.out.println(myCheckings.getInterestRate());
					}
					else if (command.equals("s")) {
						System.out.println("New interest rate: ");
						double interest = in.nextDouble();
						myCheckings.setInterestRate(interest);
					}
					else if(command.equals("a")) {
						double temp = myCheckings.getBalance();
						myCheckings.addInterest();
						printer.currentCheckings(temp, myCheckings.getBalance());
					}
					else if(command.equals("back")) {
						break;
					}
					else if(command.equals("c")) {
						printer.interstRateCommandList();
					}
					else {
						System.out.println("Unknown command!");
					}
					
				}
				
			}
			else if (command.equals("c")) {
				printer.commandList();
			}
			else if(command.equals("esc")) {
				break;
			}
			else {
				System.out.println("Unknown command!");
			}
		}
		printer.goodbye();
		in.close();
	}

	public static void main(String[] args) {
		AccountProgram main = new AccountProgram();
		main.init();
		main.run();
	}
}
