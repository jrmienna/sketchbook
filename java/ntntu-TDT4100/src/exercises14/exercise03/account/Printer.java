package exercises14.exercise03.account;

public class Printer {

	public void newAccount() {
		System.out.println("*****************************************");
		System.out.println("********    NEW ACCOUNT   ***************");
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		System.out.println("***    ***      *** *** *** **** ********");
		System.out.println("****** *** **** ***     *** * ** ********");
		System.out.println("*** ** *** **** *** *** *** ** * ********");
		System.out.println("***    ***      *** *** *** ***  ********");
		System.out.println("*****************************************");
		System.out.println();
		
	}
	public void commandList() {
		System.out.println("****************************************");
		System.out.println("****************************************");
		System.out.println("*******  Command list: *****************");
		System.out.println("****************************************");
		System.out.println("******* <d>   - deposit       **********");
		System.out.println("******* <w>   - withdraw      **********");
		System.out.println("******* <b>   - balance       **********");
		System.out.println("******* <i>   - interest rate **********");
		System.out.println("******* <c>   - command list  **********");
		System.out.println("******* <esc> - exit          **********");
		System.out.println("****************************************");
		System.out.println("****************************************");
		System.out.println();
	}
	public void interstRateCommandList() {
		System.out.println("****************************************");
		System.out.println("****************************************");
		System.out.println("*******  Command list: *****************");
		System.out.println("****************************************");
		System.out.println("******* <a>  	- add interest rate ****");
		System.out.println("******* <g>  	- get current rate  ****");
		System.out.println("******* <s>  	- set new rate      ****");
		System.out.println("******* <c>  	- command list      ****");
		System.out.println("******* <back>  - back to menu      ****");
		System.out.println("****************************************");
		System.out.println("****************************************");
		System.out.println();
	}
	
	public void currentCheckings(double initBalance, double currentBalance ) {
		System.out.println("****************************************");
		System.out.println("****************************************");
		System.out.println("***" + " Old balance: " + initBalance);
		System.out.println("***" + " New balance: " + currentBalance);
		System.out.println("****************************************");
		System.out.println("****************************************");
	}
	public void goodbye() {
		System.out.println("*****************************************");
		System.out.println("********    GOODBYE!      ***************");
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		System.out.println("***    ***      *** *** *** **** ********");
		System.out.println("****** *** **** ***     *** * ** ********");
		System.out.println("*** ** *** **** *** *** *** ** * ********");
		System.out.println("***    ***      *** *** *** ***  ********");
		System.out.println("*****************************************");
	}
}
