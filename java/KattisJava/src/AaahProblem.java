import java.util.Scanner;

/**
 * @startuml
 * Bob --> Alice : hello zup
 * Alice --> Bob: nuthin
 * 
 * @enduml
 * @author mienna
 *
 */

public class AaahProblem {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string1 = scanner.nextLine();
		String string2 = scanner.nextLine();
		boolean go = string1.compareTo(string2) <= 0;
		System.out.println(go ? "go" : "no");
		
		scanner.close();
	}
}