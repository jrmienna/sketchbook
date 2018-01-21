package exercises14.exercise06.stringgrid;

public interface StringGrid {

	public int getRowCount();

	public int getColumnCount();

	public String getElement(int row, int column);

	public void setElement(int row, int column, String element);
}
