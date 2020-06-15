public class ArrayDeque<T> implements Deque<T> {
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
    @Override
    public int size() {
        return size;
    }

    /** Expand the size of array.
     * Notice the size is full.
     * This section has the bug.
     * May be this fun
     */
    private void upSize(int capacity) {
        T[] temp = t;
        int begin = plusOne(front);
        int end = minusOne(rear);
        t = (T[]) new Object[capacity];
        front = 0;
        rear = 1;
        for (int i=begin; i != end; i = plusOne(i, temp.length)) {
            t[rear] = temp[i];
            rear = plusOne(rear);
        }
        t[rear] = temp[end];
        rear = plusOne(rear);
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
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the true index in the array.
     * and the second function is the helper function when we need resize function.
     */
    public  int plusOne(int index) {
        return   (index + 1) % t.length;
    }
    private int plusOne(int index, int length) {
        return (index + 1) % length;
    }

    /** Return the true index in the array.
     * and the second function is the helper function when we need resize function.
     */
    public  int minusOne(int index) {
        return   (index - 1 + t.length) % t.length;
    }
    private int minusOne(int index, int length) {
        return (index - 1 + length) % length;
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        if (this.isFull()) {
            upSize(size * 2);
        }
        t[front] = item;// out of index
        front = minusOne(front);
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (this.isFull()) {
            upSize(size * 2);
        }
        t[rear] = item;
        rear = plusOne(rear);
        size += 1;
    }

    /**  Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    @Override
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
    @Override
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
    @Override
    public T get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        return t[index];
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        for (int index = plusOne(front);index != rear;index = plusOne(index)) {
            System.out.print(t[index]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque arrd = new ArrayDeque();
        arrd.addFirst(9);
        arrd.addFirst(8);
        arrd.addFirst(7);
        arrd.addFirst(6);
        arrd.addFirst(5);
        arrd.addFirst(4);
        arrd.addFirst(3);
        arrd.addFirst(2);
        arrd.addFirst(1);
        arrd.addFirst(0);
//        arrd.addLast(9);
//        arrd.addLast(8);
//        arrd.addLast(7);
//        arrd.addLast(6);
//        arrd.addLast(5);
//        arrd.addLast(4);
//        arrd.addLast(3);
//        arrd.addLast(2);
//        arrd.addLast(1);
//        arrd.addLast(0);
        arrd.printDeque();
    }
}
