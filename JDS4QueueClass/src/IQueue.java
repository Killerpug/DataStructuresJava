import java.util.NoSuchElementException;

public interface IQueue <T>{
    T peek() throws NoSuchElementException;
    T dequeue() throws NoSuchElementException;
    void enqueue(T element);
    boolean isEmpty();
    int size();
}