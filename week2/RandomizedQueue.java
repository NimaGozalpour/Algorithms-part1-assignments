import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;



public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randQueue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randQueue = (Item[]) new Object[2];
        size = 0;
    }

    private void resize(int newSize) {
        Item[] tempRandQueue = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            tempRandQueue[i] = randQueue[i];
        }
        randQueue = tempRandQueue;
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }




        randQueue[size] = item;

        size++;
        needResize();
    }


    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int index =  StdRandom.uniform(size);
        Item temp = randQueue[index];
        randQueue[index] = randQueue[size - 1];
        size--;

        needResize();

        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int index =  StdRandom.uniform(size);

        return randQueue[index];
    }

    private void needResize() {
        if (randQueue.length == size) {
            resize(randQueue.length * 2);
        }
        else if (randQueue.length >= 4 * size) {
            resize(randQueue.length / 2);
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] indexes = new int[size];
        private int currentIndex = 0;

        public RandomizedQueueIterator() {
            for (int i = 0; i < size; i++) {
                indexes[i] = i;
            }

            StdRandom.shuffle(indexes);
        }

        public boolean hasNext() {  return currentIndex < indexes.length;  }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            return randQueue[indexes[currentIndex++]];
        }
    }

    // unit testing (required)
     public static void main(String[] args) {
        RandomizedQueue<String> s1 = new RandomizedQueue<>();

        s1.enqueue("dog");
        s1.enqueue("likes");
        s1.enqueue("to");
        s1.enqueue("eat");
        s1.enqueue("beef");

        for (String i : s1)
            edu.princeton.cs.algs4.StdOut.println(i);
        edu.princeton.cs.algs4.StdOut.println();
        for (String i : s1)
            edu.princeton.cs.algs4.StdOut.println(i);
    }


}
