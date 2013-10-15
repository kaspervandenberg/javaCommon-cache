// © Kasper van den Berg, 2011

/**
 * 
 */
package net.kaspervandenberg.apps.common.util.cache;

import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MultiCache<K, V> {
	private final ConcurrentHashMap<K, SoftReference<V>> data = new ConcurrentHashMap<>();
	
	public final V get(K key) {
		V result = peek(key);
		if(result == null) {
			result = calc(key);
			data.put(key, new SoftReference<>(result));
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
			SoftReference<V> ref = data.get(key);
			result = ref.get();
		}
		return result;		
	}

	public final void optimize() {
		Iterator<SoftReference<V>> iter = data.values().iterator();
		while (iter.hasNext()) {
			SoftReference<V> ref = iter.next();
			if(ref.get() == null) {
				iter.remove();
			}
		}
	}
	
	protected abstract V calc(K key);
}