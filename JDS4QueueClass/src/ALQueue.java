import java.util.NoSuchElementException;

public class ALQueue<T> implements IQueue<T> {
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private int capacity = 3;
    private T[] arrayQueue;

    public ALQueue(){
        arrayQueue = (T[]) new Object[capacity];
    }
    public T peek() {
        if(isEmpty()) throw new NoSuchElementException();
        return arrayQueue[head];
    }

    public T dequeue() {
        if(isEmpty()) throw new NoSuchElementException();
        if(size < (capacity/2)&& capacity < 5) trimArray(capacity);       //too many elements have been erased cut memory usage
        T frontItem = arrayQueue[head];
        arrayQueue[head] = null;
        head++;
        head %= capacity;   //circular
        size--;
        return frontItem;
    }
    private void trimArray(int oldCap) {
        capacity /= 2;
        T[] newArray = (T[]) new Object[capacity];
        int i,j=0;
        for (i= head; (i % oldCap) != tail; i++) {
            newArray[j] = arrayQueue[i];     //Save old values beginning on head and ending when counter reaches tail
            j++;
        }
        arrayQueue = newArray;      //update Queue
        head = 0;   //correct the head to fill the elimination of items
        tail = j;   //update tail
    }

    public void enqueue(T element) {
        if(size == capacity-1) {            //the tail is one step from the head
            reallocateArray(capacity);
        }
        arrayQueue[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
    }

    private void reallocateArray(int oldCap) {
        capacity *= 2;
        T[] newArray = (T[]) new Object[capacity];
        int i;
        for (i= head; (i % oldCap) != tail; i++) {
            newArray[i] = arrayQueue[i];     //Save old values beginning on head and ending when counter reaches tail
        }
        arrayQueue = newArray;      //update Queue
        tail = i;   //update tail
    }

    public boolean isEmpty() {
        return size < 1;
    }

    public int size(){
        return size;
    }
}
