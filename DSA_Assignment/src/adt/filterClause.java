package adt;

/**
 *
 * @author tankj
 */
public interface filterClause<T> {
    /**
     * Checks if the given element matches a specified condition.
     *
     * @param element The element to check.
     * @return true if the element matches the condition; false otherwise.
     */
    boolean match(T element);
}
