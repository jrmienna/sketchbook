package examples;

public class TestIteration {

	public int day = 2;
	public int month = 2;
	
	private int daysInMonth(int month) {
		if (month > 0) {
			return 1;
		}
		if (month < 0) {
			return -1;
		}
		return 0;
	}
	
	public void nextDay() {
		this.day = daysInMonth(this.month = -5);
	}
	
	
	public static void main(String[] args) {
	
		TestIteration ti = new TestIteration();
		
		ti.nextDay();
		
		System.out.println(ti.day);
		System.out.println(ti.month);
		
		
		
		
	}
}
