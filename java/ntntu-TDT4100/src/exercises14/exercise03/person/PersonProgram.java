package exercises14.exercise03.person;

import javax.swing.JFrame;


public class PersonProgram {
		
	private JFrame frame;
	
	private void run() {
		frame = new PersonFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Person registration");
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		PersonProgram main = new PersonProgram();
		main.run();
	}

}
