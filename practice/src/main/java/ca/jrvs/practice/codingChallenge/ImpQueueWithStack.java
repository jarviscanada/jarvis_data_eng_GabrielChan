package ca.jrvs.practice.codingChallenge;

import java.util.NoSuchElementException;
import java.util.Stack;

public class ImpQueueWithStack<E>{
    private Stack<E> stack;

    public boolean add(E e) {
        if (stack.size() < stack.capacity()) {
            stack.insertElementAt(e, 0);
            return true;
        }
        else {
            throw new IllegalStateException();
        }
    }

    public E element() {
        if (stack.empty()) {
            throw new NoSuchElementException();
        }
        else {
            return stack.firstElement();
        }
    }

    public boolean offer(E e) {
        if (stack.size() < stack.capacity()) {
            stack.insertElementAt(e, 0);
            return true;
        }
        else {
            return false;
        }
    }

    public E peek() {
        if (stack.empty()) {
            return null;
        }
        else {
            return stack.firstElement();
        }
    }

    public E poll() {
        if (stack.empty()) {
            return null;
        }
        else {
            E e = stack.firstElement();
            stack.removeElementAt(0);
            return e;
        }
    }

    public E remove() {
        if (stack.empty()) {
            throw new NoSuchElementException();
        }
        else {
            E e = stack.firstElement();
            stack.removeElementAt(0);
            return e;
        }
    }
}
