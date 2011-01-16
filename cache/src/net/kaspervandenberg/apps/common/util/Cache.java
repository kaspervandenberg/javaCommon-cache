// © Kasper van den Berg, 2011

/**
 * 
 */
package net.kaspervandenberg.apps.common.util;

import java.lang.ref.SoftReference;

public abstract class Cache<V> {
	private SoftReference<V> data = null;
	public final V get() {
		V resultaat = null;
		if (data != null) {
			resultaat = data.get();
		}
		if (resultaat == null) {
			resultaat = bereken();
			data = new SoftReference<V>(resultaat);
		}
		return resultaat;
	}
	
	protected abstract V bereken();		
}