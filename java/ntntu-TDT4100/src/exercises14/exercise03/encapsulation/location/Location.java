package exercises14.exercise03.encapsulation.location;

public class Location {

	private int x = 0;
	private int y = 0;

	public void up() {
		this.y--;
	}

	public void down() {
		this.y++;
	}

	public void left() {
		this.x--;
	}

	public void right() {
		this.x++;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
