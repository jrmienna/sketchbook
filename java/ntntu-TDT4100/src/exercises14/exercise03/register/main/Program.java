package exercises14.exercise03.register.main;

import javax.swing.JFrame;


public class Program {
		
	private JFrame frame;
	
	private void run() {
		frame = new Frame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Person registration");
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Program main = new Program();
		main.run();
	}

}
