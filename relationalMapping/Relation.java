package com.relationalMapping;

/**
 * Data Class to store two Pairs of different generic types.
 *
 * @param <T1>
 * @param <T2>
 */
public class Relation<T1, T2> {

	private Pair<T1> first;
	private Pair<T2> second;
	
	public Relation(T1 obj1, T2 obj2, String obj1_id, String obj2_id) {
		
		this.first = new Pair<T1>(obj1_id, obj1);
		this.second = new Pair<T2>(obj2_id, obj2);
		
	}
	
	public Relation(Pair<T1> pair1, Pair<T2> pair2) {
		this.first = pair1;
		this.second = pair2;
		
	}

	public String getFirstKey() {
		return this.first.getKey();
	}
	
	public String getSecondKey() {
		return this.second.getKey();
	}
	
	public T1 getFirstObj() {
		return this.first.getObj();
	}
	
	public T2 getSecondObj() {
		return this.second.getObj();
	}
	
	public Relation<T2, T1> reverseRelation() {		
		Relation<T2, T1> reversePair = new Relation<T2, T1>(this.second.getObj(), this.first.getObj(), this.second.getKey(),this.first.getKey());
		return reversePair;
	}
	
	public boolean equals(Relation<T1, T2> other) {
		if (this == other) {
			return true;
		}

		return ((this.first.getKey()).equals(other.first.getKey()) && (this.second.getKey()).equals(other.second.getKey())) || ((this.first.getKey()).equals(other.second.getKey()) && (this.second.getKey()).equals(other.first.getKey()));
	}

}
