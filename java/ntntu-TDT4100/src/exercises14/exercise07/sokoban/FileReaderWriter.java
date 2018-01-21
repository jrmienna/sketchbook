package exercises14.exercise07.sokoban;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReaderWriter {

	public void writeFile(String currentBoard, int currentLevel) {
		try {
			PrintWriter file = new PrintWriter("sokoban_game.txt");
			file.println(currentLevel);
			file.print(currentBoard);
			file.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("Error: file sokoban_game.txt could not be opened for writing.");
			System.exit(1);
		}
	}

	public String readFile() {
		String board = "";
		try {
			Scanner in = new Scanner(new FileReader("sokoban_game.txt"));
			while (in.hasNext()) {
				board += in.nextLine();
				board += "\n";
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err
					.println("Error: file 'test.txt' could not be opened. Does it exist?");
			System.exit(1);
		}
		return board;
	}
}
