/** ArrayDeque.
 * @author guialong
 * @param <T>
 * */
public class ArrayDeque<T> {
    private T[] t;
    private int size;
    private int front;
    private int rear;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        T[] a = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 1;
        t = a;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Expand the size of array. */
    private void upSize(int capacity) {
        T[] a =(T[]) new Object[capacity];
        int index = 0;
        for (int i = 0;i < size;i ++) {
            a[i] = t[index];
            index = plusOne(index);
        }
        front = capacity - 1;
        rear = size ;
        t = a;
    }

    /** Reduce the size of array. */
    private void downSize() {
         upSize(t.length / 2);
    }

    /** Returns true if deque is full, false otherwise. */
    public boolean isFull() {
        return size == t.length;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the true index in the array. */
    public  int plusOne(int index) {
        return   (index + 1) % t.length;
    }

    /** Return the true index in the array. */
    public  int minusOne(int index) {
      return   (index - 1 + t.length) % t.length;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (this.isFull()) {
            upSize(size * 2);
        }
        t[front] = item;// out of index
        front = minusOne(front);
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (this.isFull()) {
            upSize(size * 2);
        }
        t[rear] = item;
        rear = plusOne(rear);
        size += 1;
    }

    /**  Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        if (t.length >= 16 && size <= (0.25 * t.length)) {
            downSize();
        }
        front = plusOne(front);
        T i = t[front];
        t[front] = null;
        size -= 1;
        return i;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        if (t.length >= 16 && size <= (0.25 * t.length)) {
            downSize();
        }
        rear = minusOne(rear);
        T i = t[rear];
        t[rear] = null;
        size -= 1;
        return i;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        return t[index];
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        int[] a = new int[size];
        for (int i = 0;i < t.length;i += 1) {
            System.out.println(" " + t[i]);
        }
    }

    public static void main(String[] args) {
        ArrayDeque arrd = new ArrayDeque();
        arrd.addFirst(9);
        arrd.addFirst(8);
        arrd.addFirst(7);
        arrd.addFirst(6);
        arrd.addFirst(5);
        arrd.addLast(4);
        System.out.println(arrd.removeFirst());
        System.out.println();
        arrd.printDeque();
    }
}
