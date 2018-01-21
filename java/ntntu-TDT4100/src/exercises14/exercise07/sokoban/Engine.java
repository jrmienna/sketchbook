package exercises14.exercise07.sokoban;

public class Engine {

	private int r;
	private int c;

	private char currentTile;
	private char nextTile;
	private char beyondNextTile;

	private Board board;
	private Tiles tile;
	private Level level;
	private GameHistory history;
	private FileReaderWriter file;

	public Engine() {
		level = new Level();
		board = new Board();
		tile = new Tiles();
		history = new GameHistory();
		file = new FileReaderWriter();
	}

	public void start(int levelNumber) {
		level.setLevel(levelNumber);
		board.setGameBoard(level.getCurrentLevel());
		history.restart();

		setPlayerPosition();
	}

	public void restart() {
		board.setGameBoard(level.getCurrentLevel());
		history.restart();

		setPlayerPosition();
	}

	public void saveGame() {
		if (board.getGameBoard() != null) {
			file.writeFile(board.toString(), level.getCurrentLevelNumber());
		}
	}

	public void loadGame() {
		String loaded = file.readFile();
		int levelNumber = Integer.parseInt(loaded.substring(0,
				loaded.indexOf('\n')).trim());
		String loadedBoard = loaded.substring(loaded.indexOf('\n')+1,
				loaded.length());

		level.setLevel(levelNumber);
		board.setGameBoard(loadedBoard);
		history.restart();
		
		setPlayerPosition();
	}

	public int getLevelNumber() {
		return level.getCurrentLevelNumber();
	}
	public String printGameBoard() {
		return board.toString();
	}

	private void setPlayerPosition() {
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardColumns(); j++) {
				if (tile.isPlayer(board.getGameBoard()[i][j])
						|| tile.isPlayerOnGoal(board.getGameBoard()[i][j])) {
					r = i;
					c = j;
				}
			}
		}
	}

	private void move(int rowInc, int colInc) {
		if (isAcceptedMove(rowInc, colInc)) {
			// lagrer nåværende tilstand før ny tilstand settes
			history.setHistory(board.toString());

			/** LOGIKK **/
			// setter currentTile
			if (tile.isPlayer(currentTile)) {
				board.setTileAt(Tiles.EMPTY, r, c);
			} else if (tile.isPlayerOnGoal(currentTile)) {
				board.setTileAt(Tiles.GOAL, r, c);
			}
			// setter nextTile og beyondNextTile
			if (tile.isEmpty(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r + rowInc, c + colInc);
			} else if (tile.isGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r + rowInc, c + colInc);
			} else if (tile.isBox(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r + rowInc, c + colInc);
				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r + 2 * rowInc, c + 2 * colInc);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r + 2 * rowInc, c + 2
							* colInc);
				}
			} else if (tile.isBoxOnGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r + rowInc, c + colInc);
				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r + 2 * rowInc, c + 2 * colInc);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r + 2 * rowInc, c + 2
							* colInc);
				}
			}
			r += rowInc;
			c += colInc;

		}
	}

	public void moveUp() {
		move(-1, 0);
	}

	public void moveDown() {
		move(1, 0);
	}

	public void moveLeft() {
		move(0, -1);
	}

	public void moveRight() {
		move(0, 1);
	}

	public void undoMove() {
		if (!history.isEmpty()) {
			// henter ut forrige tilstand
			String previousBoard = history.getHistory();
			// putter nåværende tilstand i undone-stacken
			history.setUndone(board.toString());
			// endrer tilstand
			board.setGameBoard(previousBoard);
			setPlayerPosition();
		}
	}

	public void redoMove() {
		if (history.hasUndoneMoves()) {
			// henter ut forrige tilstand
			String undoneBoard = history.getUndone();
			// putt nåværende tilstand i history-stacken
			history.setHistory(board.toString());
			// endrer tilstand
			board.setGameBoard(undoneBoard);
			setPlayerPosition();
		}
	}

	private boolean isAcceptedMove(int rowIncr, int colIncr) {
		// Spilleren står intil en vegg
		if (r == (board.getBoardRows() - 2) && rowIncr > 0) {
			return false;
		}
		if (r == 1 && rowIncr < 0) {
			return false;
		}
		if (c == (board.getBoardColumns() - 2) && colIncr > 0) {
			return false;
		}
		if (c == 1 && colIncr < 0) {
			return false;
		}

		// forutsett at inkrementet er 1 eller -1
		currentTile = board.getTileAt(r, c);
		nextTile = board.getTileAt(r + rowIncr, c + colIncr);
		beyondNextTile = board.getTileAt(r + rowIncr * 2, c + colIncr * 2);

		// Prøver å gå i en vegg
		if (tile.isWall(nextTile)) {
			return false;
		}
		// Prøver å dytte to bokser
		if ((tile.isBox(nextTile) && tile.isBox(beyondNextTile))
				|| (tile.isBoxOnGoal(nextTile) && tile
						.isBoxOnGoal(beyondNextTile))) {
			return false;
		}
		if ((tile.isBox(nextTile) && tile.isBoxOnGoal(beyondNextTile))
				|| (tile.isBox(beyondNextTile) && tile.isBoxOnGoal(nextTile))) {
			return false;
		}
		// Prøver å dytte en boks gjennom vegg
		if ((tile.isBox(nextTile) || tile.isBoxOnGoal(nextTile))
				&& tile.isWall(beyondNextTile)) {
			return false;
		}
		return true;
	}

	public boolean isFinished() {
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardColumns(); j++) {
				char thisTile = board.getGameBoard()[i][j];
				if (tile.isPlayerOnGoal(thisTile) || tile.isBox(thisTile)
						|| tile.isGoal(thisTile)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isGameCompleted() {
		return level.getCurrentLevelNumber() == level.getFinalLevelNumber();
	}
}
