package com.relationalMapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Simple implementation of a graphical database kind of mapping.
 * 
 * Essentially, for the positive relations, if A is related to B and B is
 * related to C, then A is also related to C, and so on. For the negative
 * relations, it is simply 1 to 1. In other words, A is not related to B, and B
 * is not related to C (This means, A could potentially still be related to C).
 *
 * Additionally, relations are created using the keys (key1 and key2) in the
 * Relation object. So, you are responsible to ensure the keys are unique for
 * each new object.
 * 
 *
 * @param <T1>
 * @param <T2>
 */

public class RelationController<T1, T2> {

	// Two containers to hold the positive and negative relations
	private RelationalSetPositive positive_relation;
	private RelationalSetNegative negative_relation;

	private Map<String, T1> mapT1;
	private Map<String, T2> mapT2;

	// Initialization
	public RelationController() {

		positive_relation = new RelationalSetPositive();
		negative_relation = new RelationalSetNegative();

		mapT1 = new HashMap<>();
		mapT2 = new HashMap<>();

	}

	/**
	 * Method to retrieve objects of the first generic type
	 * 
	 * @param key
	 *            is the the same key used in the Relation Data Structure to
	 *            associate the object it is stored with.
	 * @param status
	 *            is to indicate whether to retrieve the positive relations (true)
	 *            or negative relations (false)
	 * @return a set of the objects associated with key and have a relation of type
	 *         status.
	 */
	public Set<T1> getType1(String key, boolean status) {

		Set<String> set;

		if (status)
			set = getPositiveRelationKeys(key);
		else
			set = getNegativeRelationKeys(key);

		Set<T1> positive_t1 = new HashSet<>();

		for (String str : set) {

			positive_t1.add(mapT1.get(str));

		}

		return positive_t1;
	}

	/**
	 * Method to retrieve objects of the second generic type
	 * 
	 * @param key
	 *            is the the same key used in the Relation Data Structure to
	 *            associate the object it is stored with.
	 * @param status
	 *            is to indicate whether to retrieve the positive relations (true)
	 *            or negative relations (false)
	 * @return a set of the objects associated with key and have a relation of type
	 *         status.
	 */
	public Set<T2> getType2(String key, boolean status) {

		Set<String> set;

		if (status)
			set = getPositiveRelationKeys(key);
		else
			set = getNegativeRelationKeys(key);

		Set<T2> positive_t2 = new HashSet<>();

		for (String str : set) {

			positive_t2.add(mapT2.get(str));

		}

		return positive_t2;
	}

	public Set<String> getPositiveRelationKeys(String key) {

		return positive_relation.getRelations(key);
	}

	public Set<String> getNegativeRelationKeys(String key) {

		return negative_relation.getRelations(key);
	}

	/**
	 * 
	 * Adds a relation of type status and makes the appropriate changes. For
	 * example, if the positive you wish to add already exists as a negative
	 * relation, it is removed from the negative and added as a positive and vice
	 * versa.
	 * 
	 * Additionally, for the positive relations, it will link it with other related
	 * relations previously added.
	 * 
	 * Also, this method will store pointers to the objects in the Relation passed
	 * as parameter.
	 * 
	 * @param relation
	 *            is the relation you wish to store in this "graphical" mapping
	 * @param status
	 *            is the type of relation. If status is true, the relation is
	 *            positive, else it is a negative relation.
	 */
	public void addPair(Relation<T1, T2> relation, boolean status) {

		mapT1.put(relation.getFirstKey(), relation.getFirstObj());
		mapT2.put(relation.getSecondKey(), relation.getSecondObj());

		if (status) { // status == true is a positive relation

			if (negative_relation.contains(relation)) {

				negative_relation.removeRelation(relation);

			}

			positive_relation.addRelation(relation);

		} else { // status == false is a negative relation

			if (positive_relation.contains(relation)) {

				negative_relation.removeRelation(relation);

			}

			negative_relation.addRelation(relation);

		}

	}

	/**
	 * Simply removes the relation from the corresponding status type relation.
	 * 
	 * @param relation
	 *            is the relation you wish to remove.
	 * @param status
	 *            is the type relation you wish to remove from (true is a positive
	 *            relation and false is a negative relation).
	 */
	public void removePair(Relation<T1, T2> relation, boolean status) {

		if (status) { // status == true is a positive relation

			positive_relation.removeRelation(relation);

		} else { // status == false is a negative relation

			negative_relation.removeRelation(relation);

		}
	}

}
