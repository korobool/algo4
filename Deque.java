import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
 
    private int size;
    private Node first, last;
     
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }
     
    private class ListIterator implements Iterator<Item> {
    
        private Node current = first;
        
        public boolean hasNext() 
        {
            return current != null;
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.previous;
            return item;
        }

    }

    public Deque() {
        size = 0; 
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return size;
    }
    
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.next = first;
            first.previous = oldfirst;
        }
        ++size;
    }
    
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = oldlast;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.previous = last;
        }
        ++size;
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        Node oldfirst = first.previous;
        first.previous = null;
        if (oldfirst != null) {
            oldfirst.next = null;
        }
        first = oldfirst;
        --size;
        return item;  
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Item item = last.item;
        Node newlast = last.next;
        if (newlast != null) {
            newlast.previous = null;
        }
        last.next = null;
        last = newlast;
        --size;
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        
        for (int c = 0; c < 10; c++) {
            deque.addFirst(c);
        }
        for (int i : deque) {
            StdOut.println(deque.removeLast());
        }
    }
}
