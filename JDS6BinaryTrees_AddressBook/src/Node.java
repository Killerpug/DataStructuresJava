public class Node<Key, Value> {
    public Value nodeValue;
    public Key hashKey;
    public Node<Key, Value> nextNode;    //stores link to the following NODE (value + reference)

    public Node(){
        nodeValue = null;
        nextNode = null;
        hashKey = null;
    }
    public Node(Value item){
        nodeValue = item;
    }
    public Node(Value item, Node <Key, Value> nextReference){
        nextNode = nextReference;
        nodeValue = item;
    }
    public Node(Key hashKey, Value item, Node <Key, Value> nextReference){
        nextNode = nextReference;
        nodeValue = item;
        this.hashKey = hashKey;
    }
}