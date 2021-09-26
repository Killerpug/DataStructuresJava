public class Main {
    public static void main(String []args){
        SomeObject objectList = new SomeObject(100);
        for (int i = 0;i < 100; i++ ) objectList.addObject(i);

        HashTable<String, SomeObject> myTable = new HashTable<>();
        for (int i = 0;i < 9; i++ )
        myTable.add(objectList.getObjectKey(i) , objectList.getObjectAtPosition(i) );  //add key-Value pairs
        myTable.add(objectList.getObjectKey(0) , objectList.getObjectAtPosition(5) );
        boolean value = myTable.containsKey("1134712904");   //returns true if contains key
        SomeObject value1 = myTable.get("1134712904");   //returns the value of the key
        boolean containValue = myTable.containsValue(objectList.getObjectAtPosition(99) );
        SomeObject deleted = myTable.delete("1135");
        SomeObject replaced = myTable.replace("1209271652" , objectList.getObjectAtPosition(5) );
        int size = myTable.size();
        //myTable.clear();
    }
}
