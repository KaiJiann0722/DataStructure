package adt;

/**
 *
 * @author tankj
 */
public interface firstClause<T> {
    /**
     * Checks if the given object matches a specified condition.
     *
     * @param obj The object to check.
     * @return true if the object matches the condition; false otherwise.
     */
    boolean match(T obj);
}
