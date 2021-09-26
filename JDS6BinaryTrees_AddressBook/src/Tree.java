public class Tree <Value> implements BST<Value>{
    public TreeNode<Value> rootNode = null;

    public void insertNode( TreeNode<Value> insertionNode){        //Insert a new Node in a free spot
        TreeNode<Value> parentNode = null;
        TreeNode<Value> actualNode = rootNode;
        while (actualNode != null) {                                //until find a free node
            parentNode = actualNode;                               //update parent
            if (insertionNode.nodeKey < actualNode.nodeKey) {      //decide descent path
                actualNode = actualNode.leftChild;
            }else actualNode = actualNode.rightChild;
        }
        insertionNode.parentNode = parentNode;
        if (parentNode == null) rootNode = insertionNode;           //tree is empty
        else if (insertionNode.nodeKey < parentNode.nodeKey)        //set new node as a left/right child
            parentNode.leftChild = insertionNode;
        else parentNode.rightChild = insertionNode;
    }

    public void removeNode( TreeNode<Value> replacedNode) {
        if(replacedNode.leftChild == null) transplant(replacedNode, replacedNode.rightChild);         //just one subtree
        else if (replacedNode.rightChild == null) transplant(replacedNode, replacedNode.leftChild);
        else{
            TreeNode<Value> successorNode = minimum(replacedNode.rightChild);
            if (successorNode.parentNode != replacedNode){                           //case rightChild has left subtree
                transplant( successorNode, successorNode.rightChild);
                successorNode.rightChild = replacedNode.rightChild;
                successorNode.rightChild.parentNode = successorNode;
            }
            transplant(replacedNode, successorNode);                   //set successor node as the new child of removed node parent
            successorNode.leftChild = replacedNode.leftChild;
            successorNode.leftChild.parentNode = successorNode;
        }
    }
    public void transplant(TreeNode<Value> replacedNode, TreeNode<Value> newNode) {   //replace a node with new one
        if (replacedNode.parentNode == null) rootNode = newNode;                                                  //replaced is root
        else if (replacedNode == replacedNode.parentNode.leftChild) replacedNode.parentNode.leftChild = newNode;  //replace a left node
        else  replacedNode.parentNode.rightChild = newNode;                                                        //replace a right node
        if (newNode != null) newNode.parentNode = replacedNode.parentNode;                                        //assign parent to new node
    }

    public TreeNode<Value> minimum(TreeNode<Value> treeNode){   //lowest node is the leftmost-child
        while (treeNode.leftChild != null){
            treeNode = treeNode.leftChild;
        }
        return treeNode;
    }
    public TreeNode<Value> maximum(TreeNode<Value> treeNode){
        while (treeNode.rightChild != null){
            treeNode = treeNode.rightChild;
        }
        return treeNode;
    }

    public TreeNode<Value> searchNode(TreeNode<Value> searchOn, TreeNode<Value> objectiveNode){
        while ( (searchOn != null) && (objectiveNode.nodeKey != searchOn.nodeKey)){   //stops when node found or null(no node found)
            if (objectiveNode.nodeKey < searchOn.nodeKey){
                searchOn = searchOn.leftChild;
            }else searchOn = searchOn.rightChild;
        }
        return searchOn;
    }

}
