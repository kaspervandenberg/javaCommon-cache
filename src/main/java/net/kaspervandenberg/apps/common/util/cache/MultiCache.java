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
		V resultaat = peek(key);
		if(resultaat == null) {
			resultaat = bereken(key);
			data.put(key, resultaat);
		}
		return resultaat;
	}

	public final void invalidate(K key) {
		data.remove(key);
	}
	
	public final void invalidateAll() {
		data.clear();
	}
	
	public final V peek(K key) {
		V resultaat = null;
		if(data.containsKey(key)) {
			resultaat = data.get(key);
		}
		return resultaat;		
	}
	
	protected abstract V bereken(K key);
}