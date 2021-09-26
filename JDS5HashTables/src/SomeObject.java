public class SomeObject <T>{
    private int number;
    private SomeObject []someObjects;
    private Integer key = this.hashCode();
    private int count = 0;

    public SomeObject( int numberOfObjects){
        someObjects = new SomeObject[numberOfObjects];
    }

    public void addObject (int number){
        if (count == someObjects.length) throw new IndexOutOfBoundsException("this list can hold up to " + someObjects.length + " objects");
        someObjects[count] = new SomeObject(1);
        someObjects[count].number = number;
        count ++;
    }

    public String getObjectKey(int position){ return  someObjects[position].key.toString(); }

    public SomeObject getObjectAtPosition(int position) { return someObjects[position]; }
}
