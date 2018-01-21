package exercises14.exercise03.encapsulation.rpn;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exercises14.exercise02.rpn.RPNCalc;

public class MainRPN extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private RPNCalc calculator;

	private JPanel panel;
	private static JButton BUTTON_ADD, BUTTON_SUB, BUTTON_DIV, BUTTON_MULT,
			BUTTON_MOD, BUTTON_SWAP;
	private static JButton BUTTON_ZERO, BUTTON_ONE, BUTTON_TWO, BUTTON_THREE,
			BUTTON_FOUR, BUTTON_FIVE, BUTTON_SIX, BUTTON_SEVEN, BUTTON_EIGHT,
			BUTTON_NINE;
	private static JButton BUTTON_PLUS_MINUS;
	private static JButton BUTTON_CLEAR_FIELD;
	private static JButton BUTTON_DECIMAL;
	private static JButton BUTTON_PUSH;
	private static JTextField NUMB_FIELD;
	private static JTextField ANS_FIELD;
	private static int DECIMAL_PLACE = 0;
	private double ans;

	public MainRPN() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("RPN Calculator");
		setResizable(false);
		setSize(285, 370);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setSize(230, 370);
		panel.setLayout(null);

		BUTTON_ADD = new JButton("+");
		BUTTON_ADD.setSize(50, 50);
		BUTTON_ADD.setLocation(170, 175);
		BUTTON_SUB = new JButton("-");
		BUTTON_SUB.setSize(50, 50);
		BUTTON_SUB.setLocation(170, 120);
		BUTTON_MULT = new JButton("*");
		BUTTON_MULT.setSize(50, 50);
		BUTTON_MULT.setLocation(170, 65);
		BUTTON_DIV = new JButton("/");
		BUTTON_DIV.setSize(50, 50);
		BUTTON_DIV.setLocation(115, 65);
		BUTTON_MOD = new JButton("%");
		BUTTON_MOD.setSize(50, 50);
		BUTTON_MOD.setLocation(170, 230);
		BUTTON_SWAP = new JButton("~");
		BUTTON_SWAP.setSize(50, 50);
		BUTTON_SWAP.setLocation(170, 285);
		BUTTON_ZERO = new JButton("0");
		BUTTON_ZERO.setSize(105, 50);
		BUTTON_ZERO.setLocation(5, 285);
		BUTTON_ONE = new JButton("1");
		BUTTON_ONE.setSize(50, 50);
		BUTTON_ONE.setLocation(5, 230);
		BUTTON_TWO = new JButton("2");
		BUTTON_TWO.setSize(50, 50);
		BUTTON_TWO.setLocation(60, 230);
		BUTTON_THREE = new JButton("3");
		BUTTON_THREE.setSize(50, 50);
		BUTTON_THREE.setLocation(115, 230);
		BUTTON_FOUR = new JButton("4");
		BUTTON_FOUR.setSize(50, 50);
		BUTTON_FOUR.setLocation(5, 175);
		BUTTON_FIVE = new JButton("5");
		BUTTON_FIVE.setSize(50, 50);
		BUTTON_FIVE.setLocation(60, 175);
		BUTTON_SIX = new JButton("6");
		BUTTON_SIX.setSize(50, 50);
		BUTTON_SIX.setLocation(115, 175);
		BUTTON_SEVEN = new JButton("7");
		BUTTON_SEVEN.setSize(50, 50);
		BUTTON_SEVEN.setLocation(5, 120);
		BUTTON_EIGHT = new JButton("8");
		BUTTON_EIGHT.setSize(50, 50);
		BUTTON_EIGHT.setLocation(60, 120);
		BUTTON_NINE = new JButton("9");
		BUTTON_NINE.setSize(50, 50);
		BUTTON_NINE.setLocation(115, 120);
		BUTTON_CLEAR_FIELD = new JButton("C");
		BUTTON_CLEAR_FIELD.setSize(50, 50);
		BUTTON_CLEAR_FIELD.setLocation(5, 65);
		BUTTON_DECIMAL = new JButton(".");
		BUTTON_DECIMAL.setSize(50, 50);
		BUTTON_DECIMAL.setLocation(115, 285);
		BUTTON_PLUS_MINUS = new JButton("+/-");
		BUTTON_PLUS_MINUS.setSize(50, 50);
		BUTTON_PLUS_MINUS.setLocation(60, 65);
		BUTTON_PUSH = new JButton("^");
		BUTTON_PUSH.setSize(50, 270);
		BUTTON_PUSH.setLocation(225, 65);
		ANS_FIELD = new JTextField();
		ANS_FIELD.setSize(270, 50);
		ANS_FIELD.setLocation(5, 10);
		ANS_FIELD.setFont(new Font("Arial", Font.BOLD, 20));
		ANS_FIELD.setEditable(false);
		NUMB_FIELD = new JTextField();
		NUMB_FIELD.setSize(270, 15);
		NUMB_FIELD.setLocation(5, 10);
		NUMB_FIELD.setFont(new Font("Arial", Font.BOLD, 10));
		NUMB_FIELD.setEditable(false);

		panel.add(BUTTON_ADD);
		panel.add(BUTTON_SUB);
		panel.add(BUTTON_MULT);
		panel.add(BUTTON_DIV);
		panel.add(BUTTON_MOD);
		panel.add(BUTTON_SWAP);
		panel.add(BUTTON_ZERO);
		panel.add(BUTTON_ONE);
		panel.add(BUTTON_TWO);
		panel.add(BUTTON_THREE);
		panel.add(BUTTON_FOUR);
		panel.add(BUTTON_FIVE);
		panel.add(BUTTON_SIX);
		panel.add(BUTTON_SEVEN);
		panel.add(BUTTON_EIGHT);
		panel.add(BUTTON_NINE);
		panel.add(BUTTON_PLUS_MINUS);
		panel.add(BUTTON_PUSH);
		panel.add(BUTTON_DECIMAL);
		panel.add(BUTTON_CLEAR_FIELD);
		panel.add(ANS_FIELD);
		panel.add(NUMB_FIELD);
		add(panel);

		BUTTON_ZERO.addActionListener(this);
		BUTTON_ONE.addActionListener(this);
		BUTTON_TWO.addActionListener(this);
		BUTTON_THREE.addActionListener(this);
		BUTTON_FOUR.addActionListener(this);
		BUTTON_FIVE.addActionListener(this);
		BUTTON_SIX.addActionListener(this);
		BUTTON_SEVEN.addActionListener(this);
		BUTTON_EIGHT.addActionListener(this);
		BUTTON_NINE.addActionListener(this);
		BUTTON_ADD.addActionListener(this);
		BUTTON_SUB.addActionListener(this);
		BUTTON_MULT.addActionListener(this);
		BUTTON_DIV.addActionListener(this);
		BUTTON_MOD.addActionListener(this);
		BUTTON_SWAP.addActionListener(this);
		BUTTON_CLEAR_FIELD.addActionListener(this);
		BUTTON_DECIMAL.addActionListener(this);
		BUTTON_PLUS_MINUS.addActionListener(this);
		BUTTON_PUSH.addActionListener(this);
	}

	public void init() {
		setVisible(true);
		calculator = new RPNCalc();
	}

	public void run() {
		ANS_FIELD.setText(calculator.toString());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BUTTON_ZERO) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 0;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 0;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 0;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 0;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 0;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 0;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 0;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_ONE) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 1;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 1;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 1;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 1;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 1;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 1;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 1;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.1;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.01;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_TWO) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 2;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 2;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 2;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 2;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 2;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 2;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 2;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.2;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.02;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_THREE) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 3;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 3;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 3;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 3;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 3;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 3;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 3;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.3;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.03;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_FOUR) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 4;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 4;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 4;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 4;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 4;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 4;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 4;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.4;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.04;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_FIVE) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 5;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 5;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 5;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 5;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 5;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 5;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 5;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.5;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.05;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_SIX) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 6;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 6;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 6;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 6;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 6;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 6;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 6;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.6;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.06;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_SEVEN) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 7;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 7;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 7;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 7;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 7;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 7;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 7;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.7;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.07;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_EIGHT) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 8;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 8;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 8;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 8;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 8;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 8;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 8;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.8;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.08;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_NINE) {
			if (calculator.toString().length() < 25) {
				if (DECIMAL_PLACE == -6) {
					ans = ans*10 + 9;
					DECIMAL_PLACE = -7;
				} else if (DECIMAL_PLACE == -5) {
					ans = ans*10 + 9;
					DECIMAL_PLACE = -6;
				} else if (DECIMAL_PLACE == -4) {
					ans = ans*10 + 9;
					DECIMAL_PLACE = -5;
				} else if (DECIMAL_PLACE == -3) {
					ans = ans*10 + 9;
					DECIMAL_PLACE = -4;
				} else if (DECIMAL_PLACE == -2) {
					ans = ans*10 + 9;
					DECIMAL_PLACE = -3;
				} else if (DECIMAL_PLACE == -1) {
					ans = ans*10 + 9;
					DECIMAL_PLACE = -2;
				} else if (DECIMAL_PLACE == 0) {
					ans += 9;
					DECIMAL_PLACE = -1;
				} else if (DECIMAL_PLACE == 1) {
					ans += 0.9;
					DECIMAL_PLACE = 2;
				} else if (DECIMAL_PLACE == 2) {
					ans += 0.09;
					DECIMAL_PLACE = 3;
				}
				NUMB_FIELD.setText("" + ans);
			}
			else {
				NUMB_FIELD.setText("Stack is full");
			}
		}
		if (e.getSource() == BUTTON_CLEAR_FIELD) {
			DECIMAL_PLACE = 0;
			ans = 0;
			calculator.clear();
			NUMB_FIELD.setText("");
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_ADD) {
			DECIMAL_PLACE = 0;
			calculator.performOperation('+');
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_SUB) {
			DECIMAL_PLACE = 0;
			calculator.performOperation('-');
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_MULT) {
			DECIMAL_PLACE = 0;
			calculator.performOperation('*');
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_DIV) {
			calculator.performOperation('/');
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_MOD) {
			DECIMAL_PLACE = 0;
			calculator.performOperation('%');
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_SWAP) {
			DECIMAL_PLACE = 0;
			calculator.performOperation('~');
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_PLUS_MINUS) {
			DECIMAL_PLACE = 0;
			calculator.push(calculator.pop() * (-1));
			ANS_FIELD.setText(calculator.toString());
		}
		if (e.getSource() == BUTTON_DECIMAL) {
			if (!(DECIMAL_PLACE == -7 && DECIMAL_PLACE == 3)) {
				DECIMAL_PLACE = 1;
			}
		}
		if (e.getSource() == BUTTON_PUSH) {
			if(calculator.toString().length() >= 25) {
				NUMB_FIELD.setText("Stack is full");	
			}
			else {
				DECIMAL_PLACE = 0;
				calculator.push(ans);
				ans = 0;
				NUMB_FIELD.setText("" + ans);
				ANS_FIELD.setText(calculator.toString());				
			}
		}
	}

	public static void main(String[] args) {
		MainRPN program = new MainRPN();
		program.init();
		program.run();
	}
}
