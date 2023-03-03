package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.structure.MultiCountersMap;

class MultiCountersTest {
	MultiCountersMap map;
	String[] items = { "a", "a", "a", "b", "b", "cc" };
	String[] empty = new String[0];

	@BeforeEach
	void setUp() throws Exception {
		map = new MultiCountersMap();
		for (String str : items) {
			map.addItem(str);
		}
	}

	@Test
	void addTest() {
		assertEquals(4, map.addItem("a"));
		assertEquals(3, map.addItem("b"));
		assertEquals(2, map.addItem("cc"));
		assertEquals(1, map.addItem("d"));
	}

	@Test
	void getValueTest() {
		assertEquals(3, map.getValue("a"));
		assertEquals(2, map.getValue("b"));
		assertEquals(1, map.getValue("cc"));
		assertNull(map.getValue("d"));
	}

	@Test
	void removeTest() {
		assertTrue(map.remove("a"));
		assertNull(map.getValue("a"));
		assertFalse(map.remove("a"));
	}

	@Test
	void getMaxItems() {
		String[] expected1 = { "a" };
		String[] expected2 = { "a", "b" };
		String[] expected3 = { "cc" };
		assertArrayEquals(expected1, map.getMaxItems().toArray(empty));
		map.addItem("b");
		assertArrayEquals(expected2, map.getMaxItems().toArray(empty));
		map.remove("a");
		map.remove("b");
		assertArrayEquals(expected3, map.getMaxItems().toArray(empty));
	}
}