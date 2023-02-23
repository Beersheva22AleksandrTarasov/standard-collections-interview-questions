package telran.util;

import java.util.LinkedList;

public class StackInt {
	LinkedList<Integer> numbers = new LinkedList<>();
	LinkedList<Integer> maxNumbers = new LinkedList<>();

	// Write the following methods
	// All methods should have complexity O[1]
	public void push(int num) {
		// adds num into top of stack
		numbers.addLast(num);
		if (maxNumbers.isEmpty() || num >= maxNumbers.getLast()) {
			maxNumbers.addLast(num);
		}

	}

	public int pop() {
		// returns a number from top of stack or throws NoSuchElementException
		// if the stack is empty
		int num = numbers.removeLast();
		if (num == maxNumbers.getLast()) {
			maxNumbers.removeLast();
		}
		return num;
	}

	public boolean isEmpty() {

		return numbers.isEmpty();
	}

	public int getMax() {
		// returns maximal value of the stack or throws NoSuchElementException
		// if the stack is empty
		return maxNumbers.getLast();
	}
}
