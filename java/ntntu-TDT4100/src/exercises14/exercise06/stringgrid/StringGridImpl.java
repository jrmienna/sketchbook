package exercises14.exercise06.stringgrid;

import java.util.Iterator;

public class StringGridImpl implements StringGrid, Iterable<String> {

	private int r;
	private int c;
	private String[][] stringGrid;

	public StringGridImpl(int rows, int columns) {
		stringGrid = new String[rows][columns];
		r = rows;
		c = columns;
	}

	public int getRowCount() {
		return r;
	}

	public int getColumnCount() {
		return c;
	}

	public String getElement(int row, int column) {
		return stringGrid[row][column];
	}

	public void setElement(int row, int column, String element) {
		stringGrid[row][column] = element;
	}

	public Iterator<String> iterator() {
		return new StringGridIterator (this, true);
	}
}
