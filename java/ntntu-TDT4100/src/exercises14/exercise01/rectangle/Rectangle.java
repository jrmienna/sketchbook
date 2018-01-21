package exercises14.exercise01.rectangle;

public class Rectangle {

	private int xMin, yMax; //upper left corner
	private int xMax, yMin; //lower right corner

	public Rectangle() {
		xMin = 0;
		xMax = 0;
		yMax = 0;
		yMin = 0;
	}

	public Rectangle(int x, int y, int width, int height) {
		if(width < 0 || height < 0) {
			throw new IllegalArgumentException("Widht and height must be positive");
		}
		xMin = x;
		yMax = y;
		xMax = x + width;
		yMin = y - height;
	}

	public int getMinX() {
		return xMin;
	}

	public int getMinY() {
		return yMin;
	}

	public int getMaxX() {
		return xMax;
	}

	public int getMaxY() {
		return yMax;
	}

	public int getWidth() {
		if (isPoint()) {
			return 1;
		}
		return Math.abs(xMax - xMin);
	}

	public int getHeight() {
		if (isPoint()) {
			return 1;
		}
		return Math.abs(yMax - yMin);
	}

	public boolean isPoint() {
		return (xMax == xMin && yMin == yMax) && (xMin != 0 && yMax != 0);
	}

	public boolean isEmpty() {
		return (getWidth() == 0 || getHeight() == 0);
	}

	public boolean contains(int x, int y) {
		return xMin <= x && x <= xMax && yMin <= y && y <= yMax;
	}

	public boolean contains(Rectangle rect) {

		return xMin <= rect.getMinX() && rect.getMaxX() <= xMax
				&& yMin <= rect.getMinY() && rect.getMaxY() <= yMax;
	}

	public boolean add(int x, int y) {
		if (contains(x,y)) {
			return false;
		}
		if (isEmpty()) {
			xMin = x;
			xMax = x;
			yMax = y;
			yMin = y;
			return true;
		}
		if (x < xMin) {
			if (y > yMax) {
				xMin = x;
				yMax = y;
			} else if (y < yMin) {
				xMin = x;
				yMin = y;
			} else {
				xMin = x;
			}
		} else if (x > xMax) {
			if (y > yMax) {
				xMax = x;
				yMax = y;
			} else if (y < yMin) {
				xMax = x;
				yMin = y;
			} else {
				xMax = x;
			}
		} else {
			if (y > yMax) {
				yMax = y;
			} else if (y < yMin) {
				yMin = y;
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean add(Rectangle rect) {
		if (contains(rect)) {
			return false;
		}
		add(rect.getMinX(), rect.getMaxY());
		add(rect.getMaxX(), rect.getMinY());
		return true;
	}

	public Rectangle union(Rectangle rect) {
		return rect;
	}

	public String toString() {
		return "(" + xMin + ", " + yMax + ") " + "(" + xMax + ", " + yMin + ") Width:"
				+ getWidth() + " Height: " + getHeight();
	}

	public static void main(String[] args) {

		Rectangle rect1 = new Rectangle(1,1,1,2);
		Rectangle rect2 = new Rectangle(-1,0,1,1);
		System.out.println(rect1.toString());
		rect1.add(rect2);
		System.out.println(rect1.toString());
		// assertValues(rect, x1, y1, x1, y1, 1, 1);
		//assertValues(rect, minX1X2, minY1Y2, maxX1X2, maxY1Y2, maxX1X2 - minX1X2 + 1, maxY1Y2 - minY1Y2 + 1);
	}
}
