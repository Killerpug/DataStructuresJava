class TreeNode<Value> {
    int nodeKey;
    TreeNode<Value> leftChild;
    TreeNode<Value> rightChild;
    TreeNode<Value> parentNode;
     Value nodeValue;

    public TreeNode(int keyValue, Value value){
       nodeKey = keyValue;
       nodeValue = value;
       parentNode = null;
       leftChild = null;
       rightChild = null;
    }
    public TreeNode( int keyValue){
        nodeKey = keyValue;
        nodeValue = null;
        parentNode = null;
        leftChild = null;
        rightChild = null;
    }
}
