package com.relationalMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Container class to hold and add the negative Relations.
 *
 */

public class RelationalSetNegative {
	
	// HashMap that connects the key String with all of its negative relations's key.
	private Map<String, Set<String>> map; 

	// Initialization
	public RelationalSetNegative() {

		map = new HashMap<String, Set<String>>();
	}

	public boolean contains(Relation<?, ?> relation) {

		if (map.containsKey(relation.getFirstKey()) && map.containsKey(relation.getSecondKey()))
			return map.get(relation.getFirstKey()).contains(relation.getSecondKey())
					&& map.get(relation.getSecondKey()).contains(relation.getFirstKey());
		else
			return false;

	}

	/**
	 * Adds a relation into the list/map. 
	 * 
	 * @param relation
	 *            is the Relation you wish to add to the container.
	 */
	public void addRelation(Relation<?, ?> relation) {

		if (relation == null || relation.getFirstKey().equals(relation.getSecondKey()))
			return;

		// Checking if both of the keys are within the map and making the
		if (map.containsKey(relation.getFirstKey()) && map.containsKey(relation.getSecondKey())) {

			Set<String> set1 = map.get(relation.getFirstKey());
			Set<String> set2 = map.get(relation.getSecondKey());

			set1.add(new String(relation.getSecondKey()));
			set2.add(new String(relation.getFirstKey()));

			// Checking if only the firstKey is within the map
		} else if (map.containsKey(relation.getFirstKey()) && !map.containsKey(relation.getSecondKey())) {

			Set<String> set1 = map.get(relation.getFirstKey());
			Set<String> set2 = new HashSet<String>();

			set1.add(new String(relation.getSecondKey()));
			set2.add(new String(relation.getFirstKey()));

			map.put(relation.getSecondKey(), set2);

			// Checking if only the secondKey is within the map
		} else if (map.containsKey(relation.getSecondKey()) && !map.containsKey(relation.getFirstKey())) {

			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = map.get(relation.getSecondKey());

			set1.add(new String(relation.getSecondKey()));
			set2.add(new String(relation.getFirstKey()));

			map.put(relation.getFirstKey(), set1);

			// Else, neither of the keys are in the map...
		} else {

			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = new HashSet<String>();

			set1.add(new String(relation.getSecondKey()));
			set2.add(new String(relation.getFirstKey()));

			map.put(relation.getFirstKey(), set1);
			map.put(relation.getSecondKey(), set2);

		}

	}

	public void removeRelation(Relation<?, ?> relation) {

		if (map.containsKey(relation.getFirstKey()) && map.containsKey(relation.getSecondKey())) {

			Set<String> set1 = map.get(relation.getFirstKey());
			Set<String> set2 = map.get(relation.getSecondKey());

			set1.remove(relation.getFirstKey());
			set2.remove(relation.getSecondKey());

		}

	}

	public Set<String> getRelations(String key) {

		return new HashSet<String>(map.get(key));
	}

}
