package exercises14.exercise01.location;

public class Location {

	public int x = 0;
	public int y = 0;

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
