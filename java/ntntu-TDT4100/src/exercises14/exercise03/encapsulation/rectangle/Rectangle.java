package exercises14.exercise03.encapsulation.rectangle;

public class Rectangle {

	private int upperLeftX, upperLeftY;
	private int lowerRightX, lowerRightY;

	public Rectangle() {
	}

	public Rectangle(int x, int y, int width, int height) {
		upperLeftX = x;
		upperLeftY = y;
		lowerRightX = x + width;
		lowerRightY = y - height;
	}

	public int getMinX() {
		return upperLeftX;
	}

	public int getMinY() {
		return lowerRightY;
	}

	public int getMaxX() {
		return lowerRightX;
	}

	public int getMaxY() {
		return upperLeftY;
	}

	public int getWidth() {
		if (isPoint()) {
			return 1;
		} else {
			return Math.abs(lowerRightX - upperLeftX);
		}
	}

	public int getHeight() {
		if (isPoint()) {
			return 1;
		} else {
			return Math.abs(upperLeftY - lowerRightY);
		}
	}

	public boolean isPoint() {
		return lowerRightX == upperLeftX && lowerRightY == upperLeftY;
	}

	public boolean isEmpty() {
		return (getWidth() == 0 || getHeight() == 0);
	}

	public boolean contains(int x, int y) {
		return upperLeftX <= x && x <= lowerRightX && lowerRightY <= y
				&& y <= upperLeftY;
	}

	public boolean contains(Rectangle rect) {

		return upperLeftX <= rect.getMinX() && rect.getMaxX() <= lowerRightX
				&& lowerRightY <= rect.getMinY()
				&& rect.getMaxY() <= upperLeftY;
	}

	public boolean add(int x, int y) {
		if (isEmpty() || isPoint()) {
			lowerRightX = x;
			upperLeftX = x;
			lowerRightY = y;
			upperLeftY = y;
			return true;
		} else {
			if (x > lowerRightX) {
				if (y < lowerRightY) {
					lowerRightX = x;
					lowerRightY = y;
					return true;
				} else if (y > upperLeftY) {
					lowerRightX = x;
					upperLeftY = y;
					return true;
				} else {
					lowerRightX = x;
					return true;
				}
			} else if (x < upperLeftX) {
				if (y > upperLeftY) {
					upperLeftX = x;
					upperLeftY = y;
					return true;
				} else if (y < lowerRightY) {
					upperLeftX = x;
					lowerRightY = y;
					return true;
				} else {
					upperLeftX = x;
					return true;
				}
			} else {
				if (y > upperLeftY) {
					upperLeftY = y;
					return true;
				} else if (y < lowerRightY) {
					lowerRightY = y;
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public boolean add(Rectangle rect) {
		if (contains(rect)) {
			return false;
		} else {
			add(rect.getMinX(), rect.getMaxY());
			add(rect.getMaxX(), rect.getMinY());
			return true;
		}
	}

	public Rectangle union(Rectangle rect) {
		return rect;
	}

	public String toString() {
		return "[x=" + upperLeftX + ", y=" + upperLeftY + ", width="
				+ getWidth() + ", height=" + getHeight() + "]";
	}
}
