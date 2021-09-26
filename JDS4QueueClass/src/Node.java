public class Node<T> {
    public T nodeValue;
    public Node<T> nextNode;    //stores link to the following NODE (value + reference)
    public Node(){
        nodeValue = null;
        nextNode = null;
    }
    public Node(T item){
        nodeValue = item;
    }
    public Node(T item, Node <T> nextReference){
        nextNode = nextReference;
        nodeValue = item;
    }

}
