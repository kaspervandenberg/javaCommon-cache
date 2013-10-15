// © Kasper van den Berg, 2011

/**
 * 
 */
package net.kaspervandenberg.apps.common.util.cache;

import java.util.WeakHashMap;

public abstract class MultiCache<K, V> {
	private final WeakHashMap<K, V> data =
		new WeakHashMap<K, V>();
	
	public final V get(K key) {
		V result = peek(key);
		if(result == null) {
			result = calc(key);
			data.put(key, result);
		}
		return result;
	}

	public final void invalidate(K key) {
		data.remove(key);
	}
	
	public final void invalidateAll() {
		data.clear();
	}
	
	public final V peek(K key) {
		V result = null;
		if(data.containsKey(key)) {
			result = data.get(key);
		}
		return result;		
	}
	
	protected abstract V calc(K key);
}