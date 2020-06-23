package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    /** return size of the buffer. */
    public int capacity() {
        return capacity;
    }

    @Override
    /** Return number of items currently in the buffer*/
    public int fillCount() {
        return fillCount;
    }

    @Override
    /** Is the buffer empty (fillCount equals zero)? */
    public boolean isEmpty() {
        return BoundedQueue.super.isEmpty();
    }

    @Override
    /** Is the buffer full (fillCount is same as capacity)?*/
    public boolean isFull() {
        return BoundedQueue.super.isFull();
    }

    @Override
    public abstract T peek();
    @Override
    public abstract T dequeue();
    @Override
    public abstract void enqueue(T x);
}
