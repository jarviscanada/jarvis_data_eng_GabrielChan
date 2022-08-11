package ca.jrvs.practice.codingChallenge;

import java.util.NoSuchElementException;
import java.util.Stack;

public class ImpQueueWithStack<E>{
    private Stack<E> stack;

    /**
     * Big-O: O(n)
     * Justification:
     * The Stack class is a subclass of the Vector class which can directly access elements with an index. Thus, 'e'
     * can be inserted at the "head" in constant time. However, the initial element at the inserted position and every
     * element after it needs to be shifted forward one space to make room for the new element. Since we are inserting
     * at index '0', we will need to shift 'n' elements. Thus, adding an element will take linear time.
     */
    public boolean add(E e) {
        if (stack.size() < stack.capacity()) {
            stack.insertElementAt(e, 0);
            return true;
        }
        else {
            throw new IllegalStateException();
        }
    }

    /**
     * Big-O: O(1)
     * Justification:
     * The Stack class is a subclass of the Vector class which can directly access elements with an index. Hence, the
     * "head" of the stack can be accessed in constant time with an integer index of '0'.
     */
    public E element() {
        return stack.firstElement();
    }

    /**
     * Big-O: O(n)
     * Justification:
     * Explained in 'add' method
     */
    public boolean offer(E e) {
        if (stack.size() < stack.capacity()) {
            stack.insertElementAt(e, 0);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Big-O: O(1)
     * Justification:
     * Explained in 'element' method
     */
    public E peek() {
        try {
            return stack.firstElement();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Big-O: O(n)
     * Justification:
     * The Stack class is a subclass of the Vector class which can directly access elements with an index. Hence, the
     * "head" of the stack can be accessed in constant time with an integer index of '0'. However, once the element
     * has been removed, every element after it will shift backward one space. Since we are removing the "head", 'n'
     * elements will need to be shifted after removal. Thus, removing an element will take linear time.
     */
    public E poll() {
        E element;
        try {
            element = stack.firstElement();
        } catch (NoSuchElementException e) {
            return null;
        }
        stack.removeElementAt(0);
        return element;
    }

    /**
     * Big-O: O(n)
     * Justification:
     * Explained in 'poll' method
     */
    public E remove() {
        E e = stack.firstElement();
        stack.removeElementAt(0);
        return e;
    }
}
