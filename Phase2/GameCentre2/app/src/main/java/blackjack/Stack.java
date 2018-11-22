package blackjack;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A stack class.
 *
 * @param <Integer> A stack that holds integers.
 */
// Source:https://introcs.cs.princeton.edu/java/43stack/Stack.java.html
public class Stack<Integer> implements Iterable<Integer>, Serializable {
    private int n;          // size of the stack
    private Node first;     // top of stack

    // helper linked list class

    /**
     * A node class.
     */
    private class Node implements Serializable {
        private Integer item;
        private Node next;
    }

    /**
     * Initializes an empty stack.
     */
    Stack() {
        first = null;
        n = 0;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    void push(Integer item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    Integer pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Integer item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
        return item;                   // return the saved item
    }


    Stack poplasttwo(int size) {
        if (this.size() > size) {
            Stack<Integer> temp = new Stack();
            while (!this.isEmpty()) {
                temp.push(this.pop());
            }
            temp.pop();
            temp.pop();
            while (!temp.isEmpty()) {
                this.push(temp.pop());
            }
        }
        return this;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Integer item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    @NonNull
    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Integer> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer item = current.item;
            current = current.next;
            return item;
        }
    }

}