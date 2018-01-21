package exercises14.exercise02.stack;

import java.util.ArrayList;

/**
 * Exercise 2 TDT4100 Objectorientated programming NTNU, Spring 2014
 * 
 * @author John Rikard Mienna
 * 
 */

public class Stack {

	private ArrayList<String> stringList = new ArrayList<String>();

	/**
	 * Puts the argument on top of the stack.
	 * 
	 * @param str agrument to put in stack
	 */
	public void push(String str) {
		stringList.add(0, str);
	}

	/**
	 * Removes and returns the element at the top of the stack. Returns zero if
	 * the stack is empty
	 * 
	 * @return element at index 0
	 */
	public String pop() {
		if (stringList.isEmpty()) {
			return "0";
		} else {
			String temp = stringList.get(0);
			stringList.remove(0);
			return temp;
		}
	}

	/**
	 * Returns the element in the stack at the given index. Does not remove the
	 * element. Returns zero if input argrument is zero or out of reach.
	 * 
	 * @param index
	 * @return elemnt at index
	 */
	public String peek(int index) {
		if (0 <= index && index < getSize()) {
			return stringList.get(index).toString();
		} else {
			return null;
		}
	}

	/**
	 * Returns the size of the stack.
	 * 
	 * @return number of elemnents in stack
	 */
	public int getSize() {
		return stringList.size();
	}

	/**
	 * Empties the stack
	 */
	public void clear() {
		stringList.clear();
	}

	/**
	 * Converts the stack to a string
	 */
	public String toString() {
		return stringList.toString();

	}
}
