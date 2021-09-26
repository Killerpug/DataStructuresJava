import java.time.chrono.IsoChronology;

public interface ISet<T> extends IBag<T> {

    public ISet<T> union(ISet<T> set);

    public ISet<T> intersection(ISet<T> set);

    public ISet<T> difference(ISet<T> set);

    public boolean  hasSetItem(T item);

}
