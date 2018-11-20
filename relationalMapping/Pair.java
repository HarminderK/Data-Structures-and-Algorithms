package com.relationalMapping;
/**
 * Data class to store essentially a key/value data entry, where the key is a String and value is a generic type.
 *
 * @param <T1>
 */

public class Pair<T1> {
	
	private String key;
	private T1 obj;
	
	public Pair(String key, T1 obj) {
		this.key = key;
		this.obj = obj;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public T1 getObj() {
		return this.obj;
	}

}
