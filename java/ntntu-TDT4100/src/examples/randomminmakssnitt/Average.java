package examples.randomminmakssnitt;

public class Average {

	private int count = 0;
	private int sum = 0;
	
	public void updateAverage(long random) {
		count ++;
		sum += random;
	}
	public int getAverage() {
		return (sum/count);
	}
}

