public class SetBag<T> implements ISet<T> {
    private int capacity;
    private int bagSize = 0;
    private T[] bagArray;

    public SetBag(int capacity) {
        this.capacity = capacity;
        this.bagArray = (T[]) new Object[capacity]; //Cannot be directly initialized, thus create object type and cast it to T
    }

    public T getArrayElement(int i) {
        return bagArray[i];
    }

    @Override
    public ISet<T> union(ISet<T> set) {
        SetBag<T> bagAB =new SetBag<>(this.bagSize + set.size() ); //create a new bag that can store both bags
        for(int i=0; i < this.bagSize; i++){ //get first bag
            bagAB.add(this.bagArray[i]);
        }
        for(int i=0; i< set.size(); i++) {  //get second bag
            bagAB.add(set.getArrayElement(i) );
        }
        return bagAB;
    }

    @Override
    public ISet<T> intersection(ISet<T> set) {
        SetBag<T> intersectionBag =new SetBag<>(Util.min(this.bagSize, set.size())); //create a new bag that can store both bags with minimum size
            for(int i=0; i< this.bagSize; i++){ //get
                if(set.hasSetItem(this.getArrayElement(i)) ) {
                    intersectionBag.add(this.getArrayElement(i) );
                }
            }
        return intersectionBag;
    }

    @Override
    public ISet<T> difference(ISet<T> set) {
        SetBag<T> differenceBag =new SetBag<>(this.bagSize + set.size() ); //create a new bag that can store both bags
        for(int i=0; i< this.bagSize; i++) { //get first bag
            differenceBag.add(this.bagArray[i]);
        }
        for(int i=0; i < set.size(); i++) {
            if(!differenceBag.add(set.getArrayElement(i)) ) {
                differenceBag.remove(set.getArrayElement(i) );   //get second bag and remove elements that appear in both bags
            }
        }
        return differenceBag;
    }


    //bag collection starts
    @Override
    public boolean remove(T item) { //localizes repeated element
        for(int i=0; i < this.bagSize; i++) {
            if(this.bagArray[i].equals(item) ) {
                removeTail(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(T item) {    //adds elements that are not yet in the bag
        if (this.bagSize == this.capacity || this.hasSetItem(item) ) return false;
        this.bagArray[bagSize] = item;
        this.bagSize++;
        return true;
    }

    @Override
    public T grab() {   //grabs and erases an item of the class
        int randomNumber = (int) Util.randomToRange(bagSize);
        T grabbedItem = this.bagArray[randomNumber];
        this.removeTail(randomNumber);
        return grabbedItem;
    }

    @Override
    public String toString() {  //creates a list of comma separated elements
        StringBuilder elementList= new StringBuilder();
        for(int i=0; i < this.bagSize; i++) {
            elementList.append(String.valueOf(this.bagArray[i]))
                    .append(", ");
        }
        return elementList.toString();
    }

    @Override
    public int size() { return this.bagSize; }

    private void removeTail(int index) {  //shift the tail to reposition the items filling up the gaps
        for(int beginShift = index; beginShift < this.bagSize; beginShift++) {
            this.bagArray[beginShift] = this.bagArray[beginShift + 1];
        }
        this.bagSize--;
    }
    public boolean hasSetItem(T item) { //checks if an item is contained in a set
        for(int i=0; i < this.bagSize; i++) {
            if(this.bagArray[i] == null) return true;   //when adding the first element to a bag, all elements inside array are null, so only this way that element can be added
            if(this.bagArray[i].equals(item)) {
                return true;
            }
        }
        return false;
    }
}
