package examples.randomminmakssnitt;

import java.util.Scanner;

public class RandomMinMaksSnittProgram {
	
	public Random random;
	public Min min;
	public Max max;
	public Average average;
	
	private void init() {
		random = new Random();
		min = new Min();
		max = new Max();
		average = new Average();
	}		
	
	public String toString() {
		return "[min=" + min.getMin() + " max=" + max.getMax() + " snitt=" + average.getAverage() + "]";
	}
	
	private void run() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insert n:");
		int n = scanner.nextInt();
		for(int i = 0; i < n; i++) {
			long randomN = random.getRandom();
			System.out.println(randomN);
			min.updateMin(randomN);
			max.updateMax(randomN);
			average.updateAverage(randomN);
			System.out.println(this);
			System.out.println();
		}
		scanner.close();
	}
	public static void main(String[] args) {
		RandomMinMaksSnittProgram program = new RandomMinMaksSnittProgram();
		program.init();
		program.run();
	}
}
