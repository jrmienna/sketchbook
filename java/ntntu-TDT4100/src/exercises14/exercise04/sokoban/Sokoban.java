package exercises14.exercise04.sokoban;

import java.util.Scanner;

public class Sokoban {

	private Engine engine;

	public void init() {
		engine = new Engine();
		System.out.println(engine.printGameBoard());
	}

	public void run() {
		Scanner in = new Scanner(System.in);
		System.out.println("Chose Level (1,2,3,4 or 5): ");
		int gameLevel = in.nextInt();

		engine.start(gameLevel);
		System.out.println("Level " + gameLevel);
		System.out.println();
		System.out.println(engine.printGameBoard());

		// GameLoop
		while (true) {
			String command = in.next();
			if (command.equals("w")) {
				engine.moveUp();
				System.out.println(engine.printGameBoard());
			} else if (command.equals("s")) {
				engine.moveDown();
				System.out.println(engine.printGameBoard());
			} else if (command.equals("a")) {
				engine.moveLeft();
				System.out.println(engine.printGameBoard());
			} else if (command.equals("d")) {
				engine.moveRight();
				System.out.println(engine.printGameBoard());
			} else if (command.equals("e")) {
				System.out.println("You suck!");
				break;
			} else if (command.equals("r")) {
				engine.restart();
				System.out.println(engine.printGameBoard());
			}

			if (engine.isFinished()) {
				gameLevel++;

				if (engine.isGameCompleted()) {
					System.out.println("Game completed!");
					break;
				}
				System.out.println("Congrats!");
				System.out.println();
				System.out.println("Level " + gameLevel);
				System.out.println();
				engine.start(gameLevel);
				System.out.println(engine.printGameBoard());
			}
		}
		in.close();
	}

	public static void main(String[] args) {
		Sokoban game = new Sokoban();
		game.init();
		game.run();
	}
}
