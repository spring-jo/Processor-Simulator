public interface Queue<E> { // Assuming to code will be extended and might use the queue in an array somewhere else
    public void enqueue(E item, String s);
    public E dequeue();
    public int size();
}
