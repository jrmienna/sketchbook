package exercises14.exercise01.upordowncounter;

public class UpOrDownCounter {

	private int counter;
	private int end;

	public UpOrDownCounter(int start, int end) {
		if (start != end) {
			this.counter = start;
			this.end = end;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public String toString() {
		return "[Counter counter=" + counter + " end=" + end + "]";
	}

	public int getCounter() {
		return counter;
	}

	public boolean count(int increment) {
		if (counter < end) {
			counter += increment;
		} else if (counter > end) {
			counter -= increment;
		}
		return counter != end;
	}

	public boolean count() {
		if (counter < end) {
			counter++;
		} else if (counter > end) {
			counter--;
		}
		return counter != end;
	}
}