public class Node<T> {
    public T nodeValue;         //Nodes are rarely seen since linkedList is the implementation, TF nodes can be public
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
