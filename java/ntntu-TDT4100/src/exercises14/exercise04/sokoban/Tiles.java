package exercises14.exercise04.sokoban;

public class Tiles {

	public final static char WALL = '#';
	public final static char GOAL = '.';
	public final static char BOX = '$';
	public final static char BOX_ON_GOAL = '*';
	public final static char PLAYER = '@';
	public final static char PLAYER_ON_GOAL = '+';
	public final static char EMPTY = ' ';


	public boolean isWall(char tile) {
		return tile == WALL;
	}

	public boolean isGoal(char tile) {
		return tile == GOAL;
	}

	public boolean isBox(char tile) {
		return tile == BOX;
	}

	public boolean isBoxOnGoal(char tile) {
		return tile == BOX_ON_GOAL;
	}

	public boolean isPlayer(char tile) {
		return tile == PLAYER;
	}

	public boolean isPlayerOnGoal(char tile) {
		return tile == PLAYER_ON_GOAL;
	}

	public boolean isEmpty(char tile) {
		return tile == EMPTY;
	}
}
