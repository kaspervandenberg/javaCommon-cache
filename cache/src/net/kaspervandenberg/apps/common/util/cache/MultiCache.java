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
		V resultaat;
		if(data.containsKey(key)) {
			resultaat = data.get(key);
		} else {
			resultaat = bereken(key);
			data.put(key, resultaat);
		}
		return resultaat;
	}
	
	protected abstract V bereken(K key);
}