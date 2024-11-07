package adt;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Tan Kai Jiann
 */
public class DoublyLinkedList<T> implements ListInterface<T>, Serializable {

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    @Override
    public boolean add(T newElement) {
        if (newElement != null) {
            Node newNode = new Node(newElement);
            if (isEmpty()) {
                firstNode = newNode;
                lastNode = newNode;
            } else {
                newNode.prev = lastNode;
                lastNode.next = newNode;
                lastNode = newNode;
            }
            numberOfEntries++;
            return true;
        }
        return false;
    }

    @Override
    public boolean add(int givenIndex, T newElement) {
        if (!isValidAddRange(givenIndex) || newElement == null) {
            return false;
        }
        Node newNode = new Node(newElement);
        if (givenIndex == 0) {
            if (isEmpty()) {
                firstNode = newNode;
                lastNode = newNode;
            } else {
                newNode.next = firstNode;
                firstNode.prev = newNode;
                firstNode = newNode;
            }
        } else if (givenIndex >= numberOfEntries) {
            return add(newElement);
        } else {
            Node currentNode = travel(givenIndex);
            currentNode.prev.next = newNode;
            newNode.prev = currentNode.prev;
            newNode.next = currentNode;
            currentNode.prev = newNode;
        }
        numberOfEntries++;
        return true;
    }

    @Override
    public boolean addAll(ListInterface elementsList) {
        if (!isElementsValid(elementsList)) {
            return false;
        }
        T[] elementsArray = (T[]) elementsList.toArray();
        for (int i = 0; i < elementsArray.length; i++) {
            T currentElement = (T) elementsArray[i];
            add(currentElement);
        }
        return true;
    }

    @Override
    public T remove(int removedIndex) {
        T removedElement = null;
        if (isValidRange(removedIndex)) {
            Node removedNode = travel(removedIndex);
            removedElement = removedNode.data;
            fastRemove(removedNode);
        }
        return removedElement;
    }

    @Override
    public boolean remove(T element) {
        if (element != null && isEmpty()) {
            return false;
        }
        Node removedNode = travel(element);
        if (removedNode != null) {
            fastRemove(removedNode);
            return true;
        }
        return false; // not found
    }

    private void fastRemove(Node currentNode) {
        if (currentNode == firstNode && currentNode == lastNode) {
            firstNode = null;
            lastNode = null;
        } else if (currentNode == firstNode) {
            firstNode = firstNode.next;
            firstNode.prev = null;
        } else if (currentNode == lastNode) {
            lastNode = lastNode.prev;
            lastNode.next = null;
        } else {
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
        }
        numberOfEntries--;
    }

    @Override
    public boolean removeAll(ListInterface elementsList) {
        if (!isElementsValid(elementsList)) {
            return false;
        }
        boolean isRemoved = false;
        T[] elementsArray = (T[]) elementsList.toArray();
        for (int i = 0; i < elementsArray.length; i++) {
            T currentElement = (T) elementsArray[i];
            int index;
            while ((index = indexOf(currentElement)) != -1) {
                remove(index);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean replace(int givenIndex, T element) {
        if (isValidRange(givenIndex) && element != null) {
            Node node = travel(givenIndex);
            node.data = element;
            return true;
        }
        return false;
    }

    @Override
    public T get(int givenIndex) {
        if (isValidRange(givenIndex)) {
            Node node = travel(givenIndex);
            return node.data;
        }
        return null;
    }

    @Override
    public int indexOf(T element) {
        if (element != null && !isEmpty()) {
            Node currentNode = firstNode;
            int currentIndex = 0;
            while (currentNode != null) {
                if (currentNode.data.equals(element)) {
                    return currentIndex;
                }
                currentNode = currentNode.next;
                currentIndex++;
            }
        }
        return -1; // not found
    }

    @Override
    public boolean contains(T element) {
        if (!isEmpty() && element != null) {
            return travel(element) != null;
        }
        return false;
    }

    @Override
    public ListInterface clone() {
        ListInterface<T> cloneList = new DoublyLinkedList<>();
        if (!isEmpty()) {
            Node currentNode = firstNode;
            while (currentNode != null) {
                cloneList.add(currentNode.data);
                currentNode = currentNode.next;
            }
        }
        return cloneList;
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        firstNode = lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[numberOfEntries];
        Node currentNode = firstNode;
        int index = 0;
        while (currentNode != null) {
            array[index++] = currentNode.data;
            currentNode = currentNode.next;
        }
        return array;
    }

    @Override
    public void sort(LComparator<T> comparator) {
        if (!isEmpty()) {
            boolean swapped;
            do {
                swapped = false;
                Node current = firstNode;
                while (current != null && current.next != null) {
                    if (comparator.compare(current.data,
                            current.next.data) > 0) {
                        T temp = current.data;
                        current.data = current.next.data;
                        current.next.data = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);
        }
    }

    @Override
    public ListInterface filter(filterClause<T> list) {
        ListInterface<T> filterList = new DoublyLinkedList<>();
        if (!isEmpty()) {
            for (Node currentNode = firstNode;
                    currentNode != null; currentNode = currentNode.next) {
                if (list.match(currentNode.data)) {
                    filterList.add(currentNode.data);
                }
            }
            return filterList;
        }
        return null;
    }

    @Override
    public T first(firstClause<T> list) {
        if (!isEmpty()) {
            for (Node currentNode = firstNode; currentNode != null
                    ; currentNode = currentNode.next) {
                if (list.match(currentNode.data)) {
                    return currentNode.data;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            str += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return str;
    }

    @Override
    public Iterator<T> getForwardIterator() {
        return new forwardIterator();
    }

    @Override
    public Iterator<T> getBackwardIterator() {
        return new backwardIterator();
    }

    @Override
    public T removeFromEnd() {
        T removedElement = null;
        if (!isEmpty()) {
            removedElement = lastNode.data;
            if (numberOfEntries == 1) {
                firstNode = null;
                lastNode = null;
            } else {
                lastNode = lastNode.prev;
                lastNode.next = null;
            }
            numberOfEntries--;
        }
        return removedElement;
    }

    @Override
    public T getLast() {
        return !isEmpty() ? lastNode.data : null;
    }

    private class forwardIterator implements Iterator<T> {

        Node currentNode = firstNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T data = null;
            if (hasNext()) {
                data = currentNode.data;
                currentNode = currentNode.next;
            }
            return data;
        }
    }

    private class backwardIterator implements Iterator<T> {

        Node currentNode = lastNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T data = null;
            if (hasNext()) {
                data = currentNode.data;
                currentNode = currentNode.prev;
            }
            return data;
        }
    }

    private Node travel(T element) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    private Node travel(int index) {
        Node currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private boolean isValidRange(int index) {
        return index >= 0 && index < size();
    }

    private boolean isValidAddRange(int index) {
        return index >= 0 && index <= size();
    }

    private boolean isElementsValid(ListInterface elements) {
        return elements != null && !elements.isEmpty();
    }

    private class Node implements Serializable {

        private T data;
        private Node next;
        private Node prev;

        private Node(T data) {
            this.data = data;
        }
    }
}
