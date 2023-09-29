package oop;


/**
 * Question:
 *
 * You are asked to clean a increasing sorted linked List (see the TODO below)
 * Cleaning the linkedList means keeping only one occurrence of each value.
 *
 * For instance cleaning: 3,3,3,4,5,5,6,6,6,7,9,9,9,9,10,10
 * Gives: 3,4,5,6,7,9,10
 *
 * Your algorithm should execute in Theta(n)
 * where n are the number of elements in the original list
 *
 */
public class CleanLinkedList {

    public Node first = null;
    Node last = null;

    public void add(int v) {
        if(first == null){
            first = new Node(v,null);
            return;
        }
        if (last == null){
            last = new Node(v,null);
            first.setNext(last);
            return;
        }
        if (v >= last.v){
            Node temp = new Node(v,null);
            last.setNext(temp);
            last = temp;
            return;
        }
        Node current = first;
        while (current != last){
            if (v <= current.getNext().v){
                current.setNext(new Node(v,current.getNext()));
                break;
            }
            current = current.getNext();
        }
    }
    @Override
    public String toString(){
        StringBuilder build = new StringBuilder();
        if(first == null){
            return "";
        }
        if (last == null){
            return String.valueOf(first.v);
        }
        Node current = first;
        while (current != last){
            build.append(current.v);
            current = current.getNext();
        }
        build.append(current.v);
        return build.toString();
    }
    public void add(int ... values) {
        for (int v: values) {
            add(v);
        }
    }


    /**
     * Given the increasingly sorted list, it removes the duplicates
     * @return an increasingly sorted list containing the same set
     *         of elements as list but without duplicates.
     */
    public CleanLinkedList clean() {
        Node current = first;
        while(current.getNext()!=last) {
            if (current.v == current.getNext().v) {
                while (current.v == current.getNext().v && current.getNext().getNext() != last){
                    current.setNext(current.getNext().getNext());
                }
                current = current.getNext();
                if (current.v == current.getNext().v && current.getNext() == last){
                    current.setNext(null);
                    break;
                }
            }else{
                current = current.getNext();
            }

        }
        return this;
    }


    public class Node {
        public int v;
        public Node next;
        Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
        public void setNext(Node n){
            this.next = n;
        }
        public Node getNext(){
           return this.next;
        }
        @Override
        public String toString(){
            return "Node : "+this.v;
        }
    }


}
