import java.util.EmptyStackException;

public class LLStack<T> implements IStack<T> {
    private Node<T> front = null;
    private int size = 0;

    @Override
    public boolean isEmpty(){
        return size < 1;
    }

    @Override
    public T peek() {
        if(this.isEmpty()) throw new EmptyStackException();
        return front.nodeValue;
    }

    @Override
    public T pop() {
        if(this.isEmpty()) throw new EmptyStackException();
        Node<T> temporal = front;
        front = front.nextNode;
        size--;
        return temporal.nodeValue;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public void push(T element) {
        Node<T> temp = front;
        front = new Node<>(element);
        front.nextNode = temp;
        size++;
    }
}
