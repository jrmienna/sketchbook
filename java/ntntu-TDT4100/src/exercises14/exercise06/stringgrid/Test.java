package exercises14.exercise06.stringgrid;

public class Test {

	public static void main(String[] args) {
		StringGridImpl grid = new StringGridImpl(8,5);
			
		int rowCount = 0;
		int colCount = 0;
		
		for(int r = 0; r < grid.getRowCount(); r++) {
			rowCount++;
			colCount = 0;
			for(int c = 0; c < grid.getColumnCount(); c++) {
				colCount ++;
				grid.setElement(r, c, ""+rowCount+colCount);
			}
		}
		int counter = 0;
		for (String s : grid) {
			counter++;
			System.out.print(s + " ");
			if(counter == grid.getColumnCount()) {
				System.out.println();
				counter = 0;
			}
		}
	}
	
	
}
