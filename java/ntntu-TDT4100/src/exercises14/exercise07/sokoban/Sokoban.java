package exercises14.exercise07.sokoban;

import java.util.Scanner;

public class Sokoban {

	private Scanner in;
	private Engine engine;
	private int gameLevel;

	public void init() {
		engine = new Engine();
		in = new Scanner(System.in);
	}

	private void menu() {
		System.out.println("Select option:");
		System.out.println("- New game[n]");
		System.out.println("- Load game[l]");
		System.out.println("- Choose level[c]");
		// Menuloop
		while (true) {
			String menuOption = in.next();
			if (menuOption.equals("n")) {
				gameLevel = 1;
				engine.start(gameLevel);
				break;
			}
			if (menuOption.equals("c")) {
				System.out.println("Enter level:");
				gameLevel = in.nextInt();
				engine.start(gameLevel);

				break;
			}
			if (menuOption.equals("l")) {
				engine.loadGame();
				gameLevel = engine.getLevelNumber();
				break;
			}
		}
	}

	public void run() {
		menu();
		System.out.println("Level " + gameLevel);
		Integer result = null;
		// GameLoop
		while (result == null) {
			if (in.hasNextLine()) {
				String command = in.nextLine();
				result = doLine(command);
			} else {
				result = 0;
			}
			System.out.println(engine.printGameBoard());
		}
		in.close();
	}

	public Integer doLine(String cmd) {
		if (cmd.equals("w")) {
			engine.moveUp();
		} else if (cmd.equals("s")) {
			engine.moveDown();
		} else if (cmd.equals("a")) {
			engine.moveLeft();
		} else if (cmd.equals("d")) {
			engine.moveRight();
		} else if (cmd.equals("u")) {
			engine.undoMove();
		} else if (cmd.equals("y")) {
			engine.redoMove();
		} else if (cmd.equals("m")) {
			init();
			menu();
		} else if (cmd.equals("save")) {
			engine.saveGame();
			System.out.println("Your game has been succesfully saved");
		} else if (cmd.equals("load")) {
			engine.loadGame();
			System.out.println("Your game has been succesfully loaded");
		} else if (cmd.equals("e")) {
			System.out.println("You suck!");
			return 0;
		} else if (cmd.equals("r")) {
			engine.restart();
		}
		if (engine.isFinished()) {
			gameLevel++;

			if (engine.isGameCompleted()) {
				System.out.println("Game completed!");
				return 1;
			}
			System.out.println("Level " + gameLevel);
			System.out.println();
			engine.start(gameLevel);
		}
		return null;
	}

	public static void main(String[] args) {
		Sokoban game = new Sokoban();
		game.init();
		game.run();
	}

}