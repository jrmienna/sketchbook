package tests14.encapsulation;

import no.hal.jex.runtime.JExercise;
import no.hal.jex.standalone.JexStandalone;
import junit.framework.TestCase;
import exercises14.exercise03.encapsulation.location.Location;

@JExercise(
	description="The Location class must contain two integer fields named x and y, that represent the x, y coordinates of the current location."
			+ "The x and y coordinates increase to the right and down, respectively."
)
public class LocationTest extends TestCase {
	
	Location location;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		location = new Location();
	}
	
	private void testLocation(int x, int y) {
		assertEquals(x, location.getX());
		assertEquals(y, location.getY());
	}
	
	@JExercise(
		tests="void setX(int);int getX()",
		description="The setX(int) method takes and int value as its arguments and sets this to the x field. The getX() method returns this field."
	)
	public void testGetSetX() {
		location.setX(0);
		assertEquals(location.getX(), 0);
		location.setX(10);
		assertEquals(location.getX(), 10);
		location.setX(-1);
		assertEquals(location.getX(), -1);
	}
	
	@JExercise(
			tests="void setY(int);int getY()",
			description="The setY(int) method takes and int value as its arguments and sets this to the x field. The getY() method returns this field."
		)
		public void testGetSetY() {
			location.setY(0);
			assertEquals(location.getY(), 0);
			location.setY(10);
			assertEquals(location.getY(), 10);
			location.setY(-1);
			assertEquals(location.getY(), -1);
		}
	
	@JExercise(
		tests="void left()",
		description="The left() method must change the x,y coordinates so it refers to the location to the left of the current one."
	)
	public void testLeft() {
		testLocation(0, 0);
		location.left();
		testLocation(-1, 0);
	}
	
	@JExercise(
		tests="void up()",
		description="The up() method must change the x,y coordinates so it refers to the location above the current one."
	)
	public void testUp() {
		testLocation(0, 0);
		location.up();
		testLocation(0, -1);
	}
	
	@JExercise(
		tests="void right()",
		description="The right() method must change the x,y coordinates so it refers to the location to the right of the current one."
	)
	public void testRight() {
		testLocation(0, 0);
		location.right();
		testLocation(1, 0);
	}
	
	@JExercise(
		tests="void down()",
		description="The down() method must change the x,y coordinates so it refers to the location below the current one."
	)
	public void testDown() {
		testLocation(0, 0);
		location.down();
		testLocation(0, 1);
	}
	
	//
	
	public static void main(String[] args) {
		JexStandalone.main(LocationTest.class);
	}
}