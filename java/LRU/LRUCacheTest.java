
class LRUCacheTest {

    public static void main(String[] args) {

        LRU cache = new LRUCache(2);

	cache.put(1, 1);
	cache.put(2, 2);

	assert 1 == cache.get(1);
	cache.put(3, 3);

	assert null == cache.get(2);

	cache.put(4, 4);
	assert null == cache.get(1);

	assert 3 == cache.get(3);
	assert 4 == cache.get(4);

	System.out.println("Unit test successful !");
    }
}
