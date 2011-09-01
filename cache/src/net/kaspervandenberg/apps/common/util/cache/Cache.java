// © Kasper van den Berg, 2011

/**
 * 
 */
package net.kaspervandenberg.apps.common.util.cache;

import java.lang.ref.SoftReference;

public abstract class Cache<V> {
	private SoftReference<V> data = null;
	public final V get() {
		V resultaat = peek();
		if (resultaat == null) {
			resultaat = calc();
			data = new SoftReference<V>(resultaat);
		}
		return resultaat;
	}
	
	public final V peek() {
		V resultaat = null;
		if (data != null) {
			resultaat = data.get();
		}
		return resultaat;		
	}
	
	public final void invalidate() {
		data = null;
	}
	
	protected abstract V calc();		
}