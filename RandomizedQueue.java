import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
   
    private Item[] q;
    private int N;
    
    private class RandomArrayIterator implements Iterator<Item> {

        private int i;
        private Item[] a;

        public RandomArrayIterator() {

            i = N;
            a = (Item[]) new Object[N];
            for (int j = 0; j < N; j++) {
                a[j] = q[j];
            }
            StdRandom.shuffle(a);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {

            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }
            return a[--i]; 
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        N = 0;
    }
 
    private void resize(int capacity) {
        
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (N == q.length) resize(2 * q.length);
        q[N++] = item;
    }

    // delete and return a random item
    public Item dequeue() {
        int copysize;
        int m;

        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        if ((N-1) > 0 && (N-1) == q.length/4) {
            copysize = q.length/2;
        } else {
            copysize = q.length;
        }

        Item[] copy = (Item[]) new Object[copysize];
        m = StdRandom.uniform(N);
        Item item = q[m];
        
        int j = 0;
        for (int i = 0; i < N; i++) {
            if (i != m) {
                copy[j] = q[i];
                ++j;
            }
        }
        q = copy;
        --N;
        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return q[StdRandom.uniform(N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
                   
        for (int c = 0; c < 10; c++) {
            queue.enqueue(c);
        }
        for (int i : queue) {
            StdOut.println(i);
        }
    }
}
