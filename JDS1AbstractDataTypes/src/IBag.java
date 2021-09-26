import java.util.Collection;

public interface IBag<T> extends ICollection<T> {

    public boolean remove(T item);
    public boolean add(T item);
    public T grab();
    public String toString();
    public T getArrayElement(int i);

}
