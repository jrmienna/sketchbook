package exercises14.exercise07.sokoban;

public class Board {

	
	private String level;
	
	private char[][] gameBoard;
	
	private int boardRows;
	private int boardColumns;
	
	public char[][] getGameBoard() {
		return gameBoard;
	}
	
	public void setGameBoard(String level) {
		this.level = level;
		setBoardRows();
		setBoardColumns();
		
		gameBoard = new char[boardRows][boardColumns];
		
		String levelTiles = level.replaceAll("\\n", "");
		
		int counter = 0;
		for (int r = 0; r < boardRows; r++) {
			for(int c = 0; c < boardColumns; c++) {
				gameBoard[r][c] = levelTiles.charAt(counter);
				counter++;
			}
		}
	}
	private void setBoardRows() {
		int newLineCount = 0;
		for(int i = 0; i < level.length(); i++) {
			if (level.charAt(i) == '\n') {
				newLineCount ++;
			}
		}
		boardRows = newLineCount;
	}
	
	public int getBoardRows() {
		return boardRows;
	}
	
	private void setBoardColumns() {
		boardColumns = level.substring(0, level.indexOf('\n')).length();
	}
	
	public int getBoardColumns() {
		return boardColumns;
	}
	
	public void setTileAt(char tile, int row, int col) {
		gameBoard[row][col] = tile;
	}

	public char getTileAt(int row, int col) {
		return gameBoard[row][col];
	}
	
	public String toString() {
		String board = "";
		
		for(int r = 0; r < boardRows; r++) {
			for (int c = 0; c < boardColumns; c++) {
				board += gameBoard[r][c];
			}
			board += "\n";
		}
		return board;
	}
}
