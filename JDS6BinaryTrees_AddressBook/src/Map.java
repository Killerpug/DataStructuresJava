public interface Map<Key, Value> {
    public void add(Key key, Value value);
    public Value get(Key key);
    public void clear();
    public boolean containsKey(Key key);
    public boolean containsValue(Value value);
    public Value delete(Key key);
    public Value replace(Key key, Value newValue);
    public int size();
}