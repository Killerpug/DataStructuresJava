import java.util.EmptyStackException;

public class ALStack<T> implements IStack<T>{
    private T []arrayStack;
    private int capacity = 5;
    private int size = 0;
    public ALStack(){
        this.arrayStack = (T[]) new Object[capacity]; //Cannot be directly initialized, thus create object type and cast it to T
    }
    public ALStack(int ensureCapacity){
        this.arrayStack = (T[]) new Object[ensureCapacity]; //Create ALStack ensuring a minimum capacity
    }

    @Override
    public boolean isEmpty(){
         return size < 1;
    }

    @Override
    public T peek() {
        if(this.isEmpty()) throw new EmptyStackException();
        return arrayStack[size-1];
    }

    @Override
    public T pop() {
        if(this.isEmpty()) throw new EmptyStackException();
        if (size < capacity/2) capacity = capacity/2;       //if the stack
        T top = this.arrayStack[size-1];
        this.size--;
        return top;
    }

    @Override
    public int size(){
        return this.size;
    }

    @Override
    public void push(T element){
        if(this.size == this.capacity) {            //Capacity is at maximum
            this.capacity *= 2;                     //Increase capacity
            reallocateArray(this.arrayStack);       //Reallocate values in a new Array
        }
        this.arrayStack[size] = element;            //Push new element
        this.size++;
    }

    private void  reallocateArray(T []oldStack){
        T[] newArray = (T[]) new Object[capacity];
        for (int i=0; i<size; i++) newArray[i] = oldStack[i];     //Save old values
        this.arrayStack = newArray;                               //Change memory block of the stackArray global
    }
}
