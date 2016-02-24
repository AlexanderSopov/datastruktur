package lab3;

/**
 * Created by Alex on 16-02-24.
 */
public class BinaryTree <E extends Comparable<? super E>>{


    /** Class to encapsulate a Tree Node */
    protected class Node<E extends Comparable<? super E>> {
        // Data Fields
        /** The information stored */
        protected E data;
        /** reference to left child*/
        Node<E> left;
        /** reference to right child */
        Node<E> right;

        /** Construct an node with given Data and no children.
            @param data The data stored in node
         *
         * */
        public Node(E data){
            this.data = data;
            left = null;
            right = null;
        }

        public Node(Node n) {
            data = (E)n.data;
            left = n.left;
            right = n.right;
        }
    }

    protected Node<E> root;
    public Node<E> parent;

    public BinaryTree(){
        this(null);
    }

    protected BinaryTree(Node<E> root){
        this.root = root;
        parent = null;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree){
        root = new Node(data);
        if (leftTree != null)
            root.left = leftTree.root;
        else
            root.left = null;


        if(rightTree != null)
            root.right = leftTree.root;
        else
            root.right = null;
    }


    public BinaryTree(Node root, Node parent) {
        this.root = root;
        this.parent = parent;
    }

    public BinaryTree<E> getLeftSubTree(){
        if (root != null)
            return new BinaryTree(root.left);
        else
            return null;
    }
    public BinaryTree<E> getRightSubTree(){
        if (root != null)
            return new BinaryTree(root.right);
        else
            return null;
    }


    public boolean isEmpty() {
        return (root == null || (root.left != null && root.right == null));
    }

    public boolean isLeftChild() {
        if(parent == null)
            return false;

        return parent.left == root;
    }

    public boolean isLeaf(){
        return (root.left == null && root.right == null);
    }



}
