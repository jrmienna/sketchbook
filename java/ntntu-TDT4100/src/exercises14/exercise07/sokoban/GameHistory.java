package exercises14.exercise07.sokoban;

import java.util.Stack;

public class GameHistory {
	private Stack<String> history = new Stack<String>();
	private Stack<String> undone = new Stack<String>();
	
	public void restart() {
		history.clear();
		undone.clear();
	}
	
	public void setHistory(String board) {
		history.push(board);
	}
	
	public String getHistory() {
		if(!history.isEmpty()) {
			String board = history.pop();
			return board;
		}
		return "";
	}

	public void setUndone(String board) {
		undone.push(board);
	}
	public String getUndone() {
		if(!undone.isEmpty()) {
			String board = undone.pop();
			return board;
		}
		return "";
	}
	
	public boolean isEmpty() {
		return history.isEmpty();
	}
	
	public boolean hasUndoneMoves() {
		return !undone.isEmpty();
	}
	
	public String getLastBoard() {
		return history.lastElement();
	}
	
	public String toString() {
		return "history: " + history.toString() + "\nundone: " + undone.toString();
	}
}
