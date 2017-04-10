import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class LRUCache implements LRU {

    private Map<Integer, Integer> cache = new HashMap<>();
    private LinkedList<Integer> queue = new LinkedList<>();
    private final int N;

    public LRUCache(int N) {
        this.N = N;
    }

    @Override
    public Integer get(Integer key) {
	if (queue.contains(key)) {
		queue.remove(key);
		queue.addFirst(key);
		return cache.get(key);
	}
	return null;
    }

    @Override
    public void put(Integer key, Integer value) {
	if (queue.size() == N) {
		queue.removeLast();
	}
	queue.addFirst(key);
	cache.put(key, value);
    }
}
