package telran.util;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class StackInt {
	LinkedList<Integer> numbers = new LinkedList<>();

	// Write the following methods
	// All methods should have complexity O[1]
	public void push(int num) {
		// adds num into top of stack
		numbers.add(num);

	}

	public int pop() throws Exception {
		// returns a number from top of stack or throws NoSuchElementException
		// if the stack is empty
		try {
			return numbers.getLast();
		} catch (Exception e) {
			throw new EmptyStackException();
		}
	}

	public boolean isEmpty() {

		return numbers.isEmpty();
	}

	public int getMax() {
		// returns maximal value of the stack or throws NoSuchElementException
		// if the stack is empty
		try {
			
			int res = numbers.stream().collect(Collectors.maxBy(Comparator.naturalOrder())).get();
			return res;
		} catch (Exception e) {
			throw new EmptyStackException();
		}
	}
}
