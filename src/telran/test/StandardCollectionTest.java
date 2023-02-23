package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void SubListtest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);

		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);

	}

	@Test
	@Disabled
	void displayOccurrencesCount() {
		String[] strings = { "lmn", "abc", "abc", "lmn", "a", "lmn" };
		Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));

	}

	@Test
	void displayDigitStatistics() {
		// Generate 1000000 random numbers [1-Integer.MAX_VALUE)
		// Display digits and counts of their occurrences in descending order of the
		// counts
		// consider using flatMap for getting many from one

		new Random().ints(1_000_000, 1, Integer.MAX_VALUE).flatMap(digit -> Integer.toString(digit).chars()).boxed()
				.collect(Collectors.groupingBy(digit -> digit, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%c: %d\n", e.getKey(), e.getValue()));

	}

	@Test
	void stackIntTest() throws Exception {
		StackInt numbers = new StackInt();
		assertTrue(numbers.isEmpty());
		assertThrowsExactly(NoSuchElementException.class, () -> numbers.pop());
		numbers.push(0);
		numbers.push(100);
		numbers.push(500);
		numbers.push(50);
		numbers.push(-100);
		assertEquals(-100, numbers.pop());
		assertEquals(500, numbers.getMax());
	}
}
