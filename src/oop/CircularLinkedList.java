package oop;
import java.util.Optional;

/**
 * In this exercise, you will implement some methods for a circular linked-list.
 * A circular linked-list is a linked-list for which the last element has a successor: the
 * first element.
 * For example, if the list is 4 -> 5 -> 2, then 4 is the first element, and 2 points towards 4.
 *                             ^         |
 *                             |         |
 *                             -----------
 *
 * We ask you to implement two methods; enqueue and remove which, respectively, add an element at the end of the queue, and
 * removes an element at a given index. The time complexity of each method is note in their specifications.
 */
public class CircularLinkedList {

    public static class Node {
        public int value;
        public Optional<Node> next;

        public Node(int value) {
            this.value = value;
            this.next = Optional.empty();
        }

        public void setNext(Node next) {
            this.next = Optional.of(next);
        }

        public boolean hasNext() {
            return this.next.isPresent();
        }
    }

    public Optional<Node> first;
    public Optional<Node> last;
    public int size;

    public CircularLinkedList() {
        this.first = Optional.empty();
        this.last = Optional.empty();
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Optional<Node> getFirst() {
        return this.first;
    }

    public Optional<Node> getLast() {
        return this.last;
    }

    public void enqueue(int value) {
        Node node = new Node(value);
        if (this.isEmpty()){
            this.first = Optional.of(node);
            this.last = Optional.of(node);
            this.size = 1;
        }else{
            if (this.last.isPresent()){
                Node lastNode = this.last.get();
                lastNode.setNext(node);
            }
            this.last = Optional.of(node);
            first.ifPresent(node::setNext);
            this.size++;
        }
    }

    public int remove(int index) {
        if (!this.isEmpty()) {
            if (this.first.isPresent()) {
                if (index == 0) {
                    // Special case: Remove the first element
                    int removedValue = this.first.get().value;
                    this.first = this.first.get().next;
                    this.size--;

                    // If the list becomes empty after removal, also update last
                    if (this.isEmpty()) {
                        this.last = Optional.empty();
                    }
                    return removedValue;
                } else {
                    Node current = this.first.get();
                    int count = 1;

                    while (count < index && current.next != this.last && current.next.isPresent()) {
                        current = current.next.get();
                        count++;
                    }

                    if (count == index && current.next.isPresent()) {
                        int removedValue = current.next.get().value;
                        Node next = current.next.get();
                        if (next.next.isPresent()){
                            current.setNext(next.next.get());
                        }

                        this.size--;

                        // If the last element was removed, update last
                        if (!current.hasNext()) {
                            this.last = Optional.of(current);
                        }
                        return removedValue;
                    }
                }
            }
        }
        return -1; // Index out of bounds or list is empty
    }

}