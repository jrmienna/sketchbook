package exercises14.exercise04.sokoban;

public class Engine {

	private int r;
	private int c;

	private char currentTile;
	private char nextTile;
	private char beyondNextTile;

	private Board board;
	private Tiles tile;
	private Level level;

	public Engine() {
		level = new Level();
		board = new Board();
		tile = new Tiles();
	}

	public void start(int levelNumber) {
		level.setLevel(levelNumber);
		board.setGameBoard(level.getLevel());

		setPlayerPosition();
	}

	public void restart() {
		board.setGameBoard(level.getLevel());

		setPlayerPosition();
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

	public void moveUp() {
		if (isAcceptedMove(-1, 0)) {
			if (tile.isPlayer(currentTile)) {
				board.setTileAt(Tiles.EMPTY, r, c);

			} else if (tile.isPlayerOnGoal(currentTile)) {
				board.setTileAt(Tiles.GOAL, r, c);
			}
			if (tile.isEmpty(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r - 1, c);
			} else if (tile.isGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r - 1, c);
			} else if (tile.isBox(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r - 1, c );

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r - 2, c);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r - 2, c);
				}
			} else if (tile.isBoxOnGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r - 1, c);

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r - 2, c);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r - 2, c);
				}
			}
			r--;
		}
	}

	public void moveDown() {
		if (isAcceptedMove(1, 0)) {
			if (tile.isPlayer(currentTile)) {
				board.setTileAt(Tiles.EMPTY, r, c);

			} else if (tile.isPlayerOnGoal(currentTile)) {
				board.setTileAt(Tiles.GOAL, r, c);
			}

			if (tile.isEmpty(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r + 1, c);
			} else if (tile.isGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r + 1, c);
			} else if (tile.isBox(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r + 1, c);

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r + 2, c);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r + 2, c);
				}
			} else if (tile.isBoxOnGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r + 1, c);

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r + 2, c);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r + 2, c);
				}
			}
			r++;
		}
	}

	public void moveLeft() {
		if (isAcceptedMove(0, -1)) {
			if (tile.isPlayer(currentTile)) {
				board.setTileAt(Tiles.EMPTY, r, c);

			} else if (tile.isPlayerOnGoal(currentTile)) {
				board.setTileAt(Tiles.GOAL, r, c);
			}

			if (tile.isEmpty(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r, c - 1);
			} else if (tile.isGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r, c - 1);
			} else if (tile.isBox(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r, c - 1);

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r, c - 2);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r, c - 2);
				}
			} else if (tile.isBoxOnGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r, c - 1);

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r, c - 2);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r, c - 2);
				}
			}
			c--;
		}
	}

	public void moveRight() {
		if (isAcceptedMove(0, 1)) {
			if (tile.isPlayer(currentTile)) {
				board.setTileAt(Tiles.EMPTY, r, c);

			} else if (tile.isPlayerOnGoal(currentTile)) {
				board.setTileAt(Tiles.GOAL, r, c);
			}

			if (tile.isEmpty(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r, c + 1);
			} else if (tile.isGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r, c + 1);
			} else if (tile.isBox(nextTile)) {
				board.setTileAt(Tiles.PLAYER, r, c + 1);

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r, c + 2);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r, c + 2);
				}
			} else if (tile.isBoxOnGoal(nextTile)) {
				board.setTileAt(Tiles.PLAYER_ON_GOAL, r, c + 1);

				if (tile.isEmpty(beyondNextTile)) {
					board.setTileAt(Tiles.BOX, r, c + 2);
				}
				if (tile.isGoal(beyondNextTile)) {
					board.setTileAt(Tiles.BOX_ON_GOAL, r, c + 2);
				}
			}
			c++;
		}
	}

	private boolean isAcceptedMove(int rowIncr, int colIncr) {

		// Spilleren st�r intil en vegg
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

		// Pr�ver � g� i en vegg
		if (tile.isWall(nextTile)) {
			return false;
		}
		// Pr�ver � dytte to bokser
		if ((tile.isBox(nextTile) && tile.isBox(beyondNextTile))
				|| (tile.isBoxOnGoal(nextTile) && tile
						.isBoxOnGoal(beyondNextTile))) {
			return false;
		}
		if ((tile.isBox(nextTile) && tile.isBoxOnGoal(beyondNextTile))
				|| (tile.isBox(beyondNextTile) && tile.isBoxOnGoal(nextTile))) {
			return false;
		}
		// Pr�ver � dytte en boks gjennom vegg
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
		return level.getLevelNumber() == level.getFinalLevelNumber();
	}
}
