import java.util.NoSuchElementException;

public class NodeQueue<E> implements Queue<E> {

    private Node<E> front, back, pivot;
    private int n;

    public NodeQueue(){
        n = 0;
        front = null;
        back = null;
        pivot = null;
    }


    @Override
    public void enqueue(E item, String s) {
        if(item == null)
            throw new IllegalArgumentException();
        Node<E> newNode = new Node<E>(item, s);
        if(back==null){
            back = newNode;
            front = pivot = back;
        }else{
            if (!(newNode.getPriority())){
                Node<E> oldBack = back;
                back = newNode;
                oldBack.setNext(back);
            }
            else if (pivot.getPriority()){
                newNode.setNext(pivot.getNext()); // if high priority then add it as the last entered high priority node
                pivot.setNext(newNode);  // after the last node with high priority, a node with low priority will be
                pivot = pivot.getNext();
            }
            else {
                newNode.setNext(pivot);
                front = pivot = newNode;
            }
        }
        n++;

    }

    @Override
    public E dequeue() {
        if(n==0){
            throw new NoSuchElementException();
        }

        E tmp = front.getItem();
        front = front.getNext();
        if(front == null){
            back = null;
            pivot = null;
        }
        else {
            if (pivot.getItem() == tmp)
                pivot = front;
        }
        n--;

        return tmp;
    }

    public int getFirstNodePriority(){
        if (n == 0)
            throw new NoSuchElementException();
        if (front.getPriority())
            return 1;
        else return 0;
    }

    @Override
    public int size() {
        return n;
    }
}
