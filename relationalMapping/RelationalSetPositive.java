package com.relationalMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Container class to hold and add the positive Relations.
 *
 */

public class RelationalSetPositive {

	// HashMap that connects the key String with all of its positive relations's key
	private Map<String, Set<String>> map;

	// Initialization
	public RelationalSetPositive() {

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
	 * Adds a relation into the list/map. Also, it forms the relation similar to a
	 * graphical database. For example, if A is related to B, B is related to C,
	 * then A is also related to C.
	 * 
	 * @param relation
	 */
	public void addRelation(Relation<?, ?> relation) {

		if (relation == null || relation.getFirstKey().equals(relation.getSecondKey()))
			return;

		// Checking if both of the keys are already within the container.
		if (map.containsKey(relation.getFirstKey()) && map.containsKey(relation.getSecondKey())) {

			Set<String> set1 = map.get(relation.getFirstKey());
			Set<String> set2 = map.get(relation.getSecondKey());

			// Combining the two relations
			set1.addAll(set2);

			// Setting them all to point to the same shared set of relations
			for (String key : set2) {

				map.put(key, set1);

			}

			// Checking if only the firstKey is within the map
		} else if (map.containsKey(relation.getFirstKey()) && !map.containsKey(relation.getSecondKey())) {

			
			Set<String> set = map.get(relation.getFirstKey());
			set.add(new String(relation.getSecondKey()));
			
			map.put(relation.getSecondKey(), set);
			

			// Checking if only the secondKey is within the map
		} else if (map.containsKey(relation.getSecondKey()) && !map.containsKey(relation.getFirstKey())) {

			Set<String> set = map.get(relation.getSecondKey());
			set.add(new String(relation.getFirstKey()));

			map.put(relation.getFirstKey(), set);
			
			// Else, neither of the keys are in the map...
		} else {

			Set<String> set = new HashSet<String>();
			set.add(new String(relation.getFirstKey()));
			set.add(new String(relation.getSecondKey()));

			map.put(relation.getFirstKey(), set);
			map.put(relation.getSecondKey(), set);

		}

	}

	public void removeRelation(Relation<?, ?> relation) {

		if(map.containsKey(relation.getFirstKey()) && map.containsKey(relation.getSecondKey())) {
			
			Set<String> set1 = map.get(relation.getFirstKey());
			Set<String> set2 = map.get(relation.getSecondKey());

			if(set1 == set2) {
			
				set1.remove(relation.getFirstKey());
				set2.remove(relation.getSecondKey());
				
				map.remove(relation.getFirstKey());
				map.remove(relation.getSecondKey());
		
			}
		}
		
	}
	
	public Set<String> getRelations(String key) {	
		
		return new HashSet<String>(map.get(key));
	}

}
