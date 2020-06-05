/** LinkedListDeque (circular sentinel topology)
 * @author guialong
 * @param <T>
 */
public class LinkedListDeque<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;
        /* constructor function. */
        public Node(Node p, T i, Node n) {
            this.prev = p;
            this.next = n;
            this.item = i;
        }
    }

    private int size;    // the size of LinkedListDeque.
    private Node sentinel; // the sentinel node.

    /** Create an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0; // using if else; so ugly.
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node LLD = new Node(sentinel, item, sentinel.next);//next?
        sentinel.next.prev = LLD;//顺序不能错
        sentinel.next = LLD;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (this.isEmpty()) {
            this.addFirst(item);
            size+= 1;
        }
        Node LLD = new Node(sentinel.prev, item, sentinel);//prev?
        sentinel.prev.next = LLD; //顺序不能错
        sentinel.prev = LLD;
        size += 1;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("this linked list deque is empty.");
        }
        int count = size;
        Node LLD = sentinel.next; //very important.please understand the meaning of sentinel.
        while (LLD != null) {
            System.out.print(LLD.item + " ");
            LLD = LLD.next;
            count -= 1;
            if (count == 0) {
                break;
            }
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T toRemoveItem = sentinel.next.item;
        /* important */
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return toRemoveItem;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T toRemoveLast = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return toRemoveLast;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        Node LLD = sentinel.next;
        while (index != 0) {
            LLD = LLD.next;
            index -= 1;
        }
        return LLD.item;
    }

    private T getRecursive(int index,Node LLD) {
        if (index == 0) {
            return LLD.item;
        }
        return getRecursive(index - 1, LLD.next);
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    /** Main method.*/
    public static void main(String[] args) {
        LinkedListDeque x = new LinkedListDeque();
        x.addFirst(3);
        x.addFirst(5);
        x.addFirst(6);
        x.addLast(2);
        x.removeLast();
        x.printDeque();
        System.out.println(x.getRecursive(1));
    }
}
