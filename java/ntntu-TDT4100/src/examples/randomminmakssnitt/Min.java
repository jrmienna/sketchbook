package examples.randomminmakssnitt;

public class Min {
	private long min = Long.MAX_VALUE;
	
	public void updateMin(long random){
		if (random < min) {
			min = random;
		}
	}
	public long getMin() {
		return min;
	}
	
}
