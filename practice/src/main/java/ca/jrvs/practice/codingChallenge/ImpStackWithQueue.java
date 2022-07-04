package ca.jrvs.practice.codingChallenge;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Queue;

public class ImpStackWithQueue<E>{
    private Queue<E> queue;
    private E tail = null;

    /**
     * Big-O: O(1)
     * Justification:
     * Only a single easily accessible element is examined without making any changes.
     */
    public boolean empty() {
        return queue.peek() == null;
    }

    /**
     * Big-O: O(1)
     * Justification:
     * Normally we would need to iterate through the entire Queue to find the "tail" element. However, my
     * implementation tracks and updates the tail with each insertion and removal, thus making the "tail" easily
     * accessible and eliminating the need to iterate through the Queue. Since we are only examining the "tail" and
     * not making any changes, the 'peek' method can be run in constant time.
     */
    public E peek() {
        // Check if the queue is empty
        if (tail == null) {
            throw new EmptyStackException();
        }
        else {
            return tail;
        }
    }

    /**
     * Big-O: O(n)
     * Justification:
     * Finding and removing the "tail" element can be done in constant time. However, we will need to iterate through
     * the queue to determine which element is the new "tail". Hence, removing the "tail" element will take linear
     * time.
     */
    public E pop() {
        // Check if the queue is empty
        if (tail == null) {
            throw new EmptyStackException();
        }
        // Remove the "top" of the queue
        E t = tail;
        queue.remove(t);
        // Update tail
        Iterator<E> iter = queue.iterator();
        E e = null;
        while (iter.hasNext()) {
            e = iter.next();
        }
        tail = e;

        return t;
    }

    /**
     * Big-O: O(1)
     * Justification:
     * Adding an element to the "tail" of the queue does not require iteration or the shifting of any elements. Thus,
     * adding an element and updating the "tail" can be done in constant time.
     */
    public E push(E e) {
        // Add 'e' to the queue if possible
        if (queue.add(e)) {
            tail = e;
            return e;
        }
        else {
            return null;
        }
    }

    /**
     * Big-O: O(n)
     * Justification:
     * In the worst case, we are searching for an element that is not the "head" or "tail" of the queue. Hence, we will
     * need to iterate through the queue to find it, which will take linear time.
     */
    public int search(E e) {
        Iterator<E> iter = queue.iterator();
        E t;
        int count = 1;
        while (iter.hasNext()) {
            t = iter.next();
            if (t == e) {
                return count;
            }
            else {
                count++;
            }
        }
        return -1;
    }
}
