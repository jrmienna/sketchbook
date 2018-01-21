package exercises14.exercise03.encapsulation.rectangle;

public class RectangleProgram {
	
	private Rectangle myRectangle;
	
	public void init() {
		myRectangle = new Rectangle(-2,2,4,4);
	}
	
	public void run() {
		myRectangle.add(new Rectangle(2,2,2,2));
		System.out.println(myRectangle.toString());
		System.out.println("isPoint: " + myRectangle.isPoint());
		System.out.println("isEmpty: " + myRectangle.isEmpty());
		System.out.println("Max x: " + myRectangle.getMaxX());
		System.out.println("Min x: " + myRectangle.getMinX());
		System.out.println("Max y: " + myRectangle.getMaxY());
		System.out.println("Min y: " + myRectangle.getMinY());
		System.out.println("Contains (0,0): " + myRectangle.contains(0, 0));
		System.out.println("Contains (-1,1,2,2): " + myRectangle.contains(new Rectangle(-1,1,2,2)));
	}
	public static void main(String[] args) {
		RectangleProgram program = new RectangleProgram();
		program.init();
		program.run();
	}
}
