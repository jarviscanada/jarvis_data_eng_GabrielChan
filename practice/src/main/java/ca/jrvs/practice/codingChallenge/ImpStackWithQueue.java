package ca.jrvs.practice.codingChallenge;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Queue;

public class ImpStackWithQueue<E>{
    private Queue<E> queue;
    private E tail = null;

    public boolean empty() {
        return queue.peek() == null;
    }

    public E peek() {
        // Check if the queue is empty
        if (tail == null) {
            throw new EmptyStackException();
        }
        else {
            return tail;
        }
    }

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
