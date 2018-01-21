package exercises14.exercise01.lineeditor;

import java.util.Scanner;

public class Main {
	
	private LineEditor myEditor;
	private Scanner in;
	
	public void init() {
		myEditor = new LineEditor();
		in = new Scanner(System.in);
	}
	public void run() {
		System.out.println("Start typing!");
		while (true) {
			String inText = in.next();
			if (inText.equals("1")){
				myEditor.left();
			}
			else if (inText.equals("2")){
				myEditor.right();
			}
			else if(inText.equals("3")){
				myEditor.insertString(" ");
			}
			else if(inText.equals("4")){
				myEditor.deleteLeft();
			}
			else if(inText.equals("5")){
				myEditor.deleteRight();
			}
			else if(inText.equals("bye")) {
				break;
			}
			else{
				myEditor.insertString(inText);
			}
			System.out.println(myEditor.toString());
		}
	}
	public static void main(String[] args) {
		Main program = new Main();
		program.init();
		program.run();
	}
}
