import java.util.Scanner;

public class DiceGame {
	
	static int sum(String[] a) {
		int res = 0;
		for (int i = 0; i < a.length; i++) {
			res += Integer.parseInt(a[i]);
		}
		return res;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int gunnar = sum(sc.nextLine().split(" "));
		int emma = sum(sc.nextLine().split(" "));
		
		if (gunnar > emma) {
			System.out.println("Gunnar");
		} else if (gunnar < emma) {
			System.out.println("Emma");
		} else {
			System.out.println("Tie");
		}
		sc.close();
	}
}
