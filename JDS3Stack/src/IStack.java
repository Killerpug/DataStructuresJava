import java.util.EmptyStackException;

public interface IStack <T>{
    T peek() throws EmptyStackException;
    T pop() throws EmptyStackException;
    void push(T element);
    boolean isEmpty();
    int size();
}