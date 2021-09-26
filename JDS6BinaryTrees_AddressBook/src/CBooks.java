import java.util.Random;

public class CBooks<Key, Tree> implements Map<Key, Tree> {
    private int size = 0;
    public int tableSize = 5;
    private Node<Key, Tree>[] map = (Node<Key, Tree>[]) new Node[tableSize];
    private float tableLoad = 0;
    private HashingMethod hashingMethod = HashingMethod.Weighted;             //pickHashMethod();         -->universal hashing is not perfect


    private int[][] randomBitMatrix;
    public CBooks(){
        initializeRandomMatrix(32);
    }

    private enum HashingMethod{
        Weighted,
        Universal
    }

    public static HashingMethod pickHashMethod() {  //Chooses a random Hashing method from HashingMethod enum
        Random random = new Random();
        return HashingMethod.values()[random.nextInt(HashingMethod.values().length)];
    }

    public void add(Key key, Tree value) {
        size++;
        tableLoad = (float) size/tableSize;
        if(tableLoad >= 2) rehash(tableSize * 2 + 1);                              //increase size of Hashtable if it is holding too much data
        int hashPosition =  hashingMethod == HashingMethod.Weighted ?
                getHashValueW(key):getChrisUniversalHashing(key);                         //Assign a number in the table according to the hashingMethod
        Node<Key, Tree> newTableEntry = new Node<>(key, value,null);          //create the node to be added (value, key, nextReference)
        if (map[hashPosition] != null)  addNextNode(newTableEntry, hashPosition);         //add node following the previous node of the chaining list
        else map[hashPosition] = newTableEntry;                                            //Create a new chaining list

    }

    private void rehash(int newSize) {
        Node<Key, Tree>[] oldMap =  map;
        tableSize = newSize;
        map =  (Node<Key, Tree>[]) new Node[tableSize];                                                   //new map
        for(int i = 0; i < oldMap.length; i++){
            if(oldMap[i] != null){                                                                       //if position on oldTable have something, transfer it to new table
                Node<Key, Tree> rehash = oldMap[i];
                while (rehash != null){                                                                  //rehash all nodes in a table entry (bucket)
                    int hashPosition = hashingMethod == HashingMethod.Weighted ?
                            getHashValueW(rehash.hashKey):getChrisUniversalHashing(rehash.hashKey);      //Use a hashing method to Assign a number in the table
                    Node<Key,Tree> newEntryPosition = new Node<>(rehash.hashKey, rehash.nodeValue,null);
                    if (map[hashPosition] != null)  addNextNode(newEntryPosition, hashPosition);         //add node following the previous node
                    else map[hashPosition] = newEntryPosition;                                          //Create a new chaining list
                    rehash = rehash.nextNode;
                }
            }
        }
        oldMap = null;
    }

    private void addNextNode(Node<Key,Tree> entry, int mapPosition) {             //insert previous nodes on the new node to CHAIN them
        entry.nextNode = map[mapPosition];
        map[mapPosition] = entry;
    }

    public Tree get(Key key) {
        int hashPosition = hashingMethod == HashingMethod.Weighted ?
                getHashValueW(key):getChrisUniversalHashing(key);       //Assign a number in the table
        Node<Key, Tree> search = map[hashPosition];
        while (search != null){
            if(key.equals(search.hashKey) ) return  search.nodeValue;
            search = search.nextNode;
        }
        return null;
    }

    public void clear(){
        for (int i = 0; i < map.length ; i++) {
            map[i] = null;
        }
        size = 0;
        tableSize = 5;
        tableLoad = 0;
    }

    public boolean containsKey(Key key) {
        int hashPosition = hashingMethod == HashingMethod.Weighted ?
                getHashValueW(key):getChrisUniversalHashing(key);       //Assign a number in the table
        Node<Key, Tree> search = map[hashPosition];
        while (search != null){
            if(key.equals(search.hashKey) ) return true;
            search = search.nextNode;
        }
        return false;
    }

    public boolean containsValue(Tree value) {
        for (int i = 0; i < map.length ; i++) {
            if(map[i] != null){                                           //if i position on table have something look for value
                Node<Key, Tree> search = map[i];
                while (search != null){                                  //search all nodes in a table entry
                    if(search.nodeValue.equals(value) ) return true;
                    search = search.nextNode;
                }
            }
        }
        return  false;
    }

    public Tree delete(Key key) {
        int hashPosition = hashingMethod == HashingMethod.Weighted ?
                getHashValueW(key):getChrisUniversalHashing(key);       //Assign a number in the table
        Node<Key, Tree> front = map[hashPosition];
        Node<Key, Tree> previous = null;
        while (front != null) {
            if(key.equals(front.hashKey) ){ // delete this node
                size --;
                if(tableLoad <= 0.5 && tableSize > 5 ) rehash((tableSize - 1) /2 );     //table is wasting space
                if(previous == null) {
                    map[hashPosition] = front.nextNode;
                }else previous.nextNode = front.nextNode;
                return  front.nodeValue;
            }
            previous = front;
            front = front.nextNode;
        }
        return null;
    }

    public Tree replace(Key key, Tree newValue) {
        int hashPosition = hashingMethod == HashingMethod.Weighted?
                getHashValueW(key):getChrisUniversalHashing(key);       //Assign a number in the table
        Node<Key, Tree> replace = map[hashPosition];
        while (replace != null) {
            if (key.equals(replace.hashKey)) { // replace this node value
                Tree oldValue =  replace.nodeValue;
                replace.nodeValue = newValue;
                return oldValue;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    //Hashing Methods
    private int getHashValueW(Key key) {     //Calculates hash value position based on char position weight
        String hashKey = key.toString();
        int hashFunction = 0;
        for (int i = 0; i < hashKey.length(); i++)  hashFunction = hashFunction *5  + hashKey.charAt(i);
        return hashFunction % tableSize;
    }
    private int getHashValueId(Key key) {       //"naive/identity" most basic hashing method
        String hashKey = key.toString();
        int hashFunction = 0;
        for (int i = 0; i < hashKey.length(); i++)  hashFunction += hashKey.charAt(i);
        return hashFunction % tableSize;
    }

    private int getChrisUniversalHashing (Key key) {
        //THIS HASHING METHOD consist on multiply a binary vector by a random generated matrix and return back to decimal the
        //resultant vector in order to generate uniformly hashing position
        int hashKey = key.hashCode();
        int []bitVector = toBooleanVector(hashKey);
        int []fuseBitVector = vectorTimesMatrix(bitVector);
        return binaryToDecimalVector(fuseBitVector) % tableSize;
    }
    private int[] toBooleanVector(int hashKey){
        int n = (int) ((Math.log(hashKey) / Math.log(2)) +1);   //number of bits in the vector
        int []booleanKeyVector = new int[n];
        for(int i = 0; i < booleanKeyVector.length; i++ ){
            booleanKeyVector[i] =  hashKey % 2;
            hashKey /= 2;
        }
        return booleanKeyVector;
    }
    private void initializeRandomMatrix(int matrixSize){
        int len = matrixSize;
        randomBitMatrix = new int[len][len];
        for (int i = 0; i < len; i++ ){
            for (int j = 0; j < len; j++ ){
                randomBitMatrix[i][j] =(int) Util.randomToRange(2);
            }
        }
    }
    private int[] vectorTimesMatrix(int []bitVector){
        int len = bitVector.length;
        int []bitOperation = new int[bitVector.length];
        for (int i = 0; i < len; i++ ){
            for (int j = 0; j < len; j++ ){
                if(bitOperation[i] <= 1) bitOperation[i] += (bitVector[j] * randomBitMatrix[i][j]);
                if(bitOperation[i] == 2 ) bitOperation[i] = 0;
            }
        }
        return bitOperation;
    }
    private int binaryToDecimalVector(int []bitVector ){
        int hashPosition = 0;
        for (int i = 0; i < bitVector.length; i++){
            hashPosition += (bitVector[bitVector.length - i - 1] * Math.pow(2 ,i) );
        }
        return hashPosition;
    }
    public Node<Key, Tree>[] viewMap(){
        return map;
    }
}
