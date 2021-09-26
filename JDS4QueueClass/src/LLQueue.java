import java.util.NoSuchElementException;

public class LLQueue<T> implements IQueue<T> {
    private Node<T> front = null;
    private Node<T> back = null;
    private int size = 0;

    public T peek() {
        if(isEmpty()) throw new NoSuchElementException();
        return front.nodeValue;
    }

    public T dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
        T firstElement = front.nodeValue;
        front = front.nextNode;
        size--;
        return firstElement;
    }

    public void enqueue(T element) {
        if(front == null){   //first element I thinck back and back.next node can be refactor to eliminate the need of enqueue
            back = new Node<>(element);
            front = back;
        } else {
            back.nextNode = new Node<>(element);
            back = back.nextNode;   //advance the list
        }
        size++;
    }

    public boolean isEmpty(){
        return size < 1;
    }
    public int size(){
        return size;
    }
}
