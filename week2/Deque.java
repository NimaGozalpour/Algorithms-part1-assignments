import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {


    private Node first;
    private Node last;
    private int size = 0;


    private class Node {
        Item item;
        Node perv;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node node = new Node();
        node.item = item;
        size++;
        if (size == 1) {

            first = node;
            last = node;
        }
        else {
            node.next = first;
            first.perv = node;
            first = node;


        }

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node node = new Node();
        node.item = item;
        size++;
        if (size == 1) {

            first = node;
            last = node;
        } else {
            node.perv = last;
            last.next = node;
            last = node;


        }

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        size--;
        if (size == 0) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.perv = null;

            if (size == 1) {
                last = first;
            }
        }

        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        size--;
        if (size == 0) {
            first = null;
            last = null;
        } else {
            last = last.perv;
            last.next = null;

            if (size == 1) {
                first = last;
            }
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item> {
        private Node current = last;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.perv;
            return item;
        }
    }


    // unit testing (required)
     public static void main(String[] args) {
        Deque<String> s = new Deque<>();

        s.addFirst("dog");
        s.addFirst("goes");
        s.addFirst("woof");

        for (String i : s) edu.princeton.cs.algs4.StdOut.println(i);

    }



}
