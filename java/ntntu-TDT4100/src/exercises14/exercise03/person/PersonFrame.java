package exercises14.exercise03.person;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PersonFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Person person;
	
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 350;
	private static final int TEXTFIELD_WIDTH = 20;

	private String name;
	private String email;
	private char gender;
	private Date date;
	private String sSN;
	
	private int day;
	private int month;
	private int year;
	
	private JButton finishButton;

	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextField sSNTextField;

	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private ButtonGroup radioButtons;

	private JComboBox<Object> dayComboBox;
	private JComboBox<Object> monthComboBox;
	private JComboBox<Object> yearComboBox;


	public PersonFrame() {
		person = new Person();
		
		createControlPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(4, 1));
		
		controlPanel.add(createTextFields());
		controlPanel.add(createRadioButtons());
		controlPanel.add(createComboBoxes());
		controlPanel.add(createButtons());
		
		add(controlPanel, BorderLayout.CENTER);
	}

	public JPanel createTextFields() {
		nameTextField = new JTextField(TEXTFIELD_WIDTH);
		nameTextField.setText("Full name");
		nameTextField.addActionListener(this);

		emailTextField = new JTextField(TEXTFIELD_WIDTH);
		emailTextField.setText("E-mail");
		emailTextField.addActionListener(this);
		
		sSNTextField = new JTextField(TEXTFIELD_WIDTH);
		sSNTextField.setText("SSN (11 digits)");
		sSNTextField.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(nameTextField);
		panel.add(emailTextField);
		panel.add(sSNTextField);

		return panel;
	}

	public JPanel createRadioButtons() {
		maleRadioButton = new JRadioButton("Male",true);
		maleRadioButton.addActionListener(this);

		femaleRadioButton = new JRadioButton("Female");
		maleRadioButton.addActionListener(this);

		radioButtons = new ButtonGroup();
		radioButtons.add(maleRadioButton);
		radioButtons.add(femaleRadioButton);
		
		JPanel panel = new JPanel();
		panel.add(maleRadioButton);
		panel.add(femaleRadioButton);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Gender"));

		return panel;
	}

	public JPanel createComboBoxes() {
		dayComboBox = new JComboBox<Object>();

		for (int i = 1; i < 32; i++) {
			dayComboBox.addItem("" + i);
		}
		dayComboBox.addActionListener(this);

		monthComboBox = new JComboBox<Object>();
		for (int i = 1; i < 13; i++) {
			monthComboBox.addItem("" + i);
		}
		monthComboBox.addActionListener(this);

		yearComboBox = new JComboBox<Object>();
		for (int i = 2014; i > 1900; i--) {
			yearComboBox.addItem("" + i);
		}
		yearComboBox.addActionListener(this);

		JPanel panel = new JPanel();
		panel.add(dayComboBox);
		panel.add(monthComboBox);
		panel.add(yearComboBox);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Birthday"));
		
		return panel;
	}

	public JPanel createButtons() {
		JPanel panel = new JPanel();
		
		finishButton = new JButton("Finish");
		finishButton.addActionListener(this);
		
		panel.add(finishButton);

		return panel;
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) { 	
		if (e.getSource() == finishButton) {
			name = nameTextField.getText();
			email = emailTextField.getText();
			sSN = sSNTextField.getText();
			
			day = Integer.parseInt(dayComboBox.getSelectedItem().toString());
			month = Integer.parseInt(monthComboBox.getSelectedItem().toString()) - 1;
			year = Integer.parseInt(yearComboBox.getSelectedItem().toString()) - 1900;
					
			date = new Date(year, month, day);
			
			if(maleRadioButton.isSelected()) {
				gender = 'M';
			}
			else if (femaleRadioButton.isSelected()) {
				gender = 'F';
			}
			
			if(!person.isValidName(name, false)) {
				JOptionPane.showMessageDialog(null, "Name not valid");
			}
			else if(!person.isValidEmail(email, name, false)) {
				JOptionPane.showMessageDialog(null, "Email not valid");
			}
			else if(!person.isValidGender(gender, false)) {
				JOptionPane.showMessageDialog(null, "Gender not valid");
			}
			else if(!person.isValidSSN(sSN, date, gender, false)) {
				JOptionPane.showMessageDialog(null, "SSN not valid");
			}
			else if(!person.isValidDate(date, false)) {
				JOptionPane.showMessageDialog(null, "Date not valid");
			}
			else {
				person.setName(name);
				person.setEmail(email);
				person.setGender(gender);
				person.setBirthday(date);
				person.setSSN(sSN);
				dispose();
				JOptionPane.showMessageDialog(null, "Registration successful!");
				System.out.println(person.toString());
			}
		}
	}
}