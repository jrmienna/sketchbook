package examples.randomminmakssnitt;

public class Max {
	private long max = Long.MIN_VALUE;
	
	public void updateMax(long random) {
		if (max < random) {
			max = random;
		}
	}
	public long getMax() {
		return max;
	}
}
