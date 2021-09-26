public interface BST< Value> {
    public void insertNode( TreeNode<Value> insertionNode);
    public void removeNode( TreeNode<Value> replacedNode);
    public TreeNode<Value> searchNode(TreeNode<Value> searchOn, TreeNode<Value> objectiveNode);
}