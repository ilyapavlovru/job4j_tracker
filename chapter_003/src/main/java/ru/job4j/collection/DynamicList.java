package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<E> implements Iterable<E> {
    /**
     * The field contain all collection data.
     */
    private Object[] container;
    /**
     * The field specify position to insert index container array.
     * Iterator take that field to check modify collection or not.
     */
    private int position = 0;
    /**
     * Default constructor.
     */
    public DynamicList() {
        this.container = new Object[1];
    }
    /**
     * The method add element to collection, and copy all collection to new array that size = old array + 1 element.
     * @param value element to add.
     */
    public void add(E value) {
        if (this.container.length == this.position + 1) {
            this.container = Arrays.copyOf(this.container, this.container.length + 1);
        }
        this.container[position] = value;
        position++;
    }
    /**
     * The method return element from collection at position = index.
     * @param index position in array container.
     * @return Cast element from raw to <E> type, and return.
     * @throws ArrayIndexOutOfBoundsException Throw if index out area of array range.
     */
    public E get(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index > this.container.length) {
            throw new ArrayIndexOutOfBoundsException("Index out of range array!");
        }
        return (E) this.container[index];
    }
    /**
     * Returns an iterator over elements of type <E>.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * To check fail-fast, if not equals when collection modified.
             */
            int modCount = position;
            /**
             * The field position for iteration elements.
             */
            int itPosition = 0;
            /**
             * The method check iterator has next element.
             * @return Return true, if more left elements to iterate, otherwise return false.
             * @throws ConcurrentModificationException Throw when collection modified, after iterator create.
             */
            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                boolean result = false;
                if (itPosition != container.length - 1) {
                    result = true;
                }
                if (modCount != position) {
                    throw new ConcurrentModificationException("Collection has been modified!");
                }
                return result;
            }
            /**
             * The method return next element in iteration.
             * @return element.
             * @throws NoSuchElementException Throw if no more elements to iterate.
             */
            @Override
            public E next() throws NoSuchElementException {
                if (hasNext()) {
                    return (E) container[itPosition++];
                }
                throw new NoSuchElementException("No more left element in collection!");
            }
        };
    }
}