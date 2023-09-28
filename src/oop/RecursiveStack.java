package oop;

import java.util.*;

/**
 * A Recursive Stack is a stack (LIFO)
 * that is immutable.
 * @param <E>
 */
public class RecursiveStack<E>  implements Iterable<E> {

    final E e;
    final RecursiveStack<E> next;

    /**
     * Creates an empty stack
     */
    public RecursiveStack() {
        this.e = null;
        this.next = null;
    }

    /**
     * Create a stack with e on top and next as the next stack.
     * The next is unchanged.
     *
     * @param e the element to put on top
     * @param next the following stack
     */
    private RecursiveStack(E e, RecursiveStack<E> next) {
        this.e = e;
        this.next = next;
    }

    /**
     * Creates and return a new stack with e on top and the
     * current stack at the bottom.
     * The current stack is unchanged.
     *
     * @param e the element to place on top
     * @return the new stack
     */
    public RecursiveStack<E> add(E e) {
        return new RecursiveStack<>(e,this);
    }

    /**
     * Returns the element on top of the stack
     *
     * @throws EmptyStackException if the stack is empty
     * @return the element on top of the stack
     */
    public E top() {
        RecursiveStack<E> current = this;
        if (this.next == null){
            throw new EmptyStackException();
        }
        while (current.next != null){
            current = current.next;
        }

        return this.e;
    }

    /**
     * Return the stack with element on top removed.
     * The current stack is unchanged.
     *
     * @return the stack with the top element removed
     */
    public RecursiveStack<E> removeTop() {
        if (this.next == null){
            throw new EmptyStackException();
        }
        return this.next;
    }

    /**
     * Computes the number of elements in the stack
     *
     * @return the number of element in the stack
     */
    public int size() {
        int count=0;
        RecursiveStack<E> current = this;
        while (current.next != null){
            current = current.next;
            count++;
        }
        return count;
    }

    /**
     * Reverse the stack.
     * The current stack is unchanged.
     *
     * @return a reversed version of the current stack (the top element becomes the bottom one)
     */
    public RecursiveStack<E> reverse() {
        RecursiveStack<E> current = this;
        RecursiveStack<E> reversed = new RecursiveStack<>();
        while (current.next != null){
            reversed = reversed.add(current.e);
            current = current.next;

        }
        return reversed;
    }

    /**
     * Creates a top-down iterator on the stack
     * The delete is not implemented
     *
     * @return the top-down iterator.
     */
    @Override
    public Iterator<E> iterator() {
        RecursiveStack<E> currentStack = this;
        Iterator<E> iterator = new Iterator<E>() {
            private RecursiveStack<E> stack = currentStack;
            @Override
            public boolean hasNext() {
                return stack.next != null;
            }

            @Override
            public E next() {
                if (this.hasNext()){
                    E res = stack.e;
                    stack = stack.next;
                return res;}
                return null;
            }
        };
        return iterator;
    }



}