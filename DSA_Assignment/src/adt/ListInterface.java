package adt;

import java.util.Iterator;

/**
 *
 * @author Tan Kai Jiann
 */
public interface ListInterface<T> {

    boolean add(T newElement);

    boolean add(int givenIndex, T newElement);

    boolean addAll(ListInterface elementsList);

    T remove(int removedIndex);

    boolean remove(T element);

    boolean removeAll(ListInterface elementsList);

    boolean replace(int givenIndex, T element);

    T get(int givenIndex);

    int indexOf(T element);

    boolean contains(T element);

    ListInterface clone();

    int size();

    void clear();

    boolean isEmpty();

    T[] toArray();

    void sort(LComparator<T> comparator);
 
    ListInterface filter(filterClause<T> list);

    T first(firstClause<T> list);

    T removeFromEnd();

    T getLast();

    Iterator<T> getForwardIterator();
 
    Iterator<T> getBackwardIterator();

}
