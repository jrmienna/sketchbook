import java.util.Scanner;
import java.lang.Math;

public class Different {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] split;
		while (sc.hasNext()) {
			split = sc.nextLine().split(" ");
			long a = Long.parseLong(split[0]);
			long b = Long.parseLong(split[1]);
			System.out.println(Math.abs(a-b));
		}
		sc.close();
	}
}
