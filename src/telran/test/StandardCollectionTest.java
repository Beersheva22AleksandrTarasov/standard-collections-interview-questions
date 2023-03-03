package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

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
	@Disabled
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

	@Test
	void maxNumberWithNegativeImageTest() {
		int ar[] = { 10000000, 3, -2, -200, 200, -3, 2 };
		int ar1[] = { 1000000, -1000000000, 3, -4 };
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));

	}

	int maxNumberWithNegativeImage(int array[]) {
		// return maximal positive number having it negative image or -1 if none such
		// numbers

		HashSet<Integer> setNumbers = new HashSet<>();

		int max = -1;
		for (int number : array) {
			if (setNumbers.contains(-number)) {
				if (Math.abs(number) > max) {
					max = Math.abs(number);
				}
			}
			setNumbers.add(number);
		}
		return max;
	}

	@Test
	void treeIteratingTest() {
		Integer array[] = { 1, 11, 111, 32, 9, 1234, 99, 992 };
		assertArrayEquals(array, createAndIterateTreeInOrder(array));
	}

	private Integer[] createAndIterateTreeInOrder(Integer[] array) {
		// create tree, add in tree numbers from a given array
		// and iterate in the order of array defined in 69

		TreeSet<Integer> tree = new TreeSet<>((x, y) -> {
			ToIntFunction<Integer> sumOfDigits = num -> Arrays.stream(Integer.toString(num).split(""))
					.mapToInt(Integer::valueOf).sum();

			return Integer.compare(sumOfDigits.applyAsInt(x), sumOfDigits.applyAsInt(y));
		});

		tree.addAll(Arrays.asList(array));
		return tree.toArray(new Integer[0]);
	}
}
