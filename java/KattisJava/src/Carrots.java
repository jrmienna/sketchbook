import java.util.Scanner;

public class Carrots {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String firstLine = scanner.nextLine();
		
		int carrots = Integer.parseInt(firstLine.split(" ")[1]);
		System.out.println(carrots);
		scanner.close();
	}
}
