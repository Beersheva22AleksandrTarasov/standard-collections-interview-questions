package telran.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class MultiCountersMap implements MultiCounters {

	private HashMap<Object, Integer> items = new HashMap<>();
	private TreeMap<Integer, Set<Object>> itemsValue = new TreeMap<>();

	@Override
	public Integer addItem(Object item) {
		int count = items.merge(item, 1, (a, b) -> a + b);
		itemsValue.computeIfAbsent(count, key -> new HashSet<>()).add(item);
		if (count > 1) {
			removeItemFromValueSets(item, count - 1);
		}
		return count;
	}

	@Override
	public Integer getValue(Object item) {
		return items.get(item);
	}

	@Override
	public boolean remove(Object item) {
		Integer count = items.remove(item);
		if (count != null) {
			removeItemFromValueSets(item, count);
		}
		return count != null;
	}

	@Override
	public Set<Object> getMaxItems() {
		return itemsValue.lastEntry().getValue();
	}

	private void removeItemFromValueSets(Object item, int count) {
		Set<Object> itemsSet = itemsValue.get(count);
		itemsSet.remove(item);
		if (itemsSet.isEmpty()) {
			itemsValue.remove(count);
		}
	}
}