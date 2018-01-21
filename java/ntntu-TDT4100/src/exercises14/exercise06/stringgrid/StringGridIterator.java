package exercises14.exercise06.stringgrid;

import java.util.Iterator;

public class StringGridIterator implements Iterator<String> {

	private StringGrid grid;
	private boolean rowMajor;
	private int rowIndex;
	private int columnIndex;

	public StringGridIterator(StringGrid grid, boolean rowMajor) {
		this.grid = grid;
		this.rowMajor = rowMajor;
		rowIndex = 0;
		columnIndex = 0;
	}

	public boolean hasNext() {
		return rowIndex != (grid.getRowCount() - 1)
				|| columnIndex != (grid.getColumnCount() - 1);
	}

	public String next() {
		String element = grid.getElement(rowIndex, columnIndex);
		if (rowMajor) {
			columnIndex++;
			if (columnIndex >= grid.getColumnCount()) {
				columnIndex = 0;
				rowIndex++;
			}
		} else {
			rowIndex++;
			if (rowIndex >= grid.getRowCount()) {
				rowIndex = 0;
				columnIndex++;
			}
		}
		return element;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
