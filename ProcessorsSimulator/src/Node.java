public class Node<E> { // make it more usable
    private E item;
    private Node<E> next;
    private boolean highPriority = false;

    public Node(E item, String s){
        this.item = item;
        if (s.equals("high"))
            highPriority = true;
        next = null;
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public boolean getPriority() {
        return highPriority;
    }

}
