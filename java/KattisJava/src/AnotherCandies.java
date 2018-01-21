import java.util.Scanner;

public class AnotherCandies {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		long N;
		long heapSum;
		for (int i = 0; i < T; i++) {
			sc.nextLine();
			N = Long.parseLong(sc.nextLine());
			heapSum = 0;
			for (int j = 0; j < N; j++) {
				heapSum += Long.parseLong(sc.nextLine());
			}
			if (heapSum % N == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		sc.close();
	}
}
