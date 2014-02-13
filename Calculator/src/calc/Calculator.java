package calc;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

	// constants for frame dimensions
	final int WIDTH = 380;
	final int HEIGHT = 250;

	// Sets number of rows in panel
	JPanel[] row = new JPanel[5];

	// Sets number on buttons to be used
	JButton[] button = new JButton[19];
	// Creates an array of strings for buttons
	String[] buttonString = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2",
			"3", "*", ".", "/", "C", "sqrt", "+/-", "=", "0" };
	// Sets the width and height for components
	int[] dimW = { 300, 45, 100, 90 };
	int[] dimH = { 35, 40 };

	// Declares and initialises dimensions
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
	Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);

	// declare booleans for use in functions (add, subtract, multiply, divide)
	boolean[] function = new boolean[4];

	// declare and initialise temporary doubles for calculations
	double[] temporary = { 0, 0 };

	// create a display using JTextArea
	JTextArea display = new JTextArea(1, 20);

	// declares font, style and pt.
	Font font = new Font("Times new Roman", Font.BOLD, 14);

	public static void main(String[] args) {
		Calculator c = new Calculator();

	}

	Calculator() {
		super("Calculator");

		// calls the set design method
		setDesign();
		setSize(WIDTH, HEIGHT);

		// disallows resizing of the frame
		setResizable(false);
		// exits the frame when closed
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// creates a new grid of size 5x5
		GridLayout grid = new GridLayout(5, 5);
		setLayout(grid);

		// initialises our function booleans
		for (int i = 0; i < 4; i++) {
			function[i] = false;
		}
		// Sets up row 1
		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);

		// Sets up rows 2-5
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);

		// initialises JPanel rows
		for (int i = 0; i < 5; i++) {
			row[i] = new JPanel();
		}
		// sets first row to layout f1
		row[0].setLayout(f1);

		// for loop to set remaining row layouts
		for (int i = 1; i < 5; i++) {
			row[i].setLayout(f2);
		}

		// Sets button text, font and size.
		for (int i = 0; i < 19; i++) {
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}

		// Sets displays properties
		display.setFont(font);
		display.setEditable(false);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		display.setPreferredSize(displayDimension);

		// sets regular buttons and right column buttons properties
		for (int i = 0; i < 14; i++) {
			button[i].setPreferredSize(regularDimension);
		}
		for (int i = 14; i < 18; i++) {
			button[i].setPreferredSize(rColumnDimension);
		}
		button[18].setPreferredSize(zeroButDimension);

		// Add components for the panel and panel to the frame
		// adds display to row 1 and row 1 to frame
		row[0].add(display);
		add(row[0]);
		// adds display to row 2
		for (int i = 0; i < 4; i++) {
			row[1].add(button[i]);
		}
		row[1].add(button[14]);
		add(row[1]);
		// adds display to row 3
		for (int i = 4; i < 8; i++) {
			row[2].add(button[i]);
		}
		row[2].add(button[15]);
		add(row[2]);
		// adds display to row 4
		for (int i = 8; i < 12; i++) {
			row[3].add(button[i]);
		}
		row[3].add(button[16]);
		add(row[3]);
		// adds display to row 5
		for (int i = 12; i < 14; i++) {
			row[4].add(button[i]);
		}
		row[4].add(button[17]);
		add(row[4]);

		setVisible(true);
	}

	public final void setDesign() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {

		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == button[0])
			display.append("7");
		if (ae.getSource() == button[1])
			display.append("8");
		if (ae.getSource() == button[2])
			display.append("9");
		if (ae.getSource() == button[3]) {
			// add function[0]
			temporary[0] = Double.parseDouble(display.getText());
			function[0] = true;
			display.setText("");
		}
		if (ae.getSource() == button[4])
			display.append("4");
		if (ae.getSource() == button[5])
			display.append("5");
		if (ae.getSource() == button[6])
			display.append("6");
		if (ae.getSource() == button[7]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[1] = true;
			display.setText("");
		}
		if (ae.getSource() == button[8])
			display.append("1");
		if (ae.getSource() == button[9])
			display.append("2");
		if (ae.getSource() == button[10])
			display.append("3");
		if (ae.getSource() == button[11]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		if (ae.getSource() == button[12])
			display.append(".");
		if (ae.getSource() == button[13]) {
			temporary[0] = Double.parseDouble(display.getText());
			function[3] = true;
			display.setText("");
		}
		if (ae.getSource() == button[14])
			clear();
		if (ae.getSource() == button[15])
			getSqrt();
		if (ae.getSource() == button[16])
			getPosNeg();
		if (ae.getSource() == button[17])
			getResult();
		if (ae.getSource() == button[18])
			display.append("0");

	}

	// Method to clear calculator
	public void clear() {
		try {
			display.setText(""); // Sets the display blank
			for (int i = 0; i < 4; i++) {
				function[i] = false; // sets function back to false
			}
			for (int i = 0; i < 2; i++) {
				temporary[i] = 0; // Sets temp variables back to 0
			}
		} catch (NullPointerException e) {
		}
	}

	// Square Root Calculation Method
	public void getSqrt() {
		try {
			double value = Math.sqrt(Double.parseDouble(display.getText()));
			display.setText(Double.toString(value)); // Sets display to new
														// value
		} catch (NumberFormatException e) {
		}
	}

	// positive to negative method
	public void getPosNeg() {
		try {
			double value = Double.parseDouble(display.getText());
			if (value != 0) { // if value isn't 0
				value = value * (-1);
				display.setText(Double.toString(value));
			} else {
			}
		} catch (NumberFormatException e) {
		}
	}

	// Method to get results
	public void getResult() {
		double result = 0; // variable for result
		temporary[1] = Double.parseDouble(display.getText());
		String temp0 = Double.toString(temporary[0]);
		String temp1 = Double.toString(temporary[1]);
		try {
			if (temp0.contains("-")) {
				String[] temp00 = temp0.split("-", 2);
				temporary[0] = (Double.parseDouble(temp00[1]) * -1);
			}
			if (temp1.contains("-")) {
				String[] temp11 = temp1.split("-", 2);
				temporary[1] = (Double.parseDouble(temp11[1]) * -1);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (function[2] == true) {
				result = temporary[0] * temporary[1];
			} else if (function[3] == true) {
				result = temporary[0] / temporary[1];
			} else if (function[0] == true) {
				result = temporary[0] + temporary[1];
			} else if (function[1] == true) {
				result = temporary[0] - temporary[1];
			}
			display.setText(Double.toString(result));
			for (int i = 0; i < 4; i++) {
				function[i] = false;
			}
		} catch (NumberFormatException e) {
		}
	}
}
