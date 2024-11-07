package adt;

/**
 *
 * @author tankj
 */
public interface LComparator<T> {
     /**
     * Compares two objects and returns an integer value indicating their relationship.
     *
     * @param obj1 The first object to compare.
     * @param obj2 The second object to compare.
     * @return A negative integer if obj1 is less than obj2, a positive integer if obj1 is greater than obj2, or zero if they are equal.
     */
    int compare(T obj1, T obj2);
}
