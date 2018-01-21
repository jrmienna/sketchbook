package exercises14.exercise03.encapsulation.stack;

public class Printer {
	public void printWelcome() {
		System.out.println("**********************************");
		System.out.println("**********************************");
		System.out.println("* Welcome to Random String Stack *");
		System.out.println("**********************************");
		System.out.println("******* Command List:  ***********");
		System.out.println("******* <pu>  - push   ***********");
		System.out.println("******* <pe>  - peek   ***********");
		System.out.println("******* <po>  - pop    ***********");
		System.out.println("******* <cl>   - size   **********");
		System.out.println("******* <s>   - size   ***********");
		System.out.println("******* <esc> - exit   ***********");
		System.out.println("**********************************");
		System.out.println("**********************************");
		System.out.println();
		
	}
	public void printWarning() {
		System.out.println("Unknown command!");
	}
	public void printIndex() {
		System.out.println("Level (index): ");
	}
	public void printLevels() {
		System.out.println("Levels: ");
	}
	public void printGoodbye() {
		System.out.println();
		System.out.println();
		System.out.println("*********************************");
		System.out.println("*********************************");
		System.out.println("*******  Good bye!  *************");
		System.out.println("*********************************");
		System.out.println("*********************************");
	}
}
