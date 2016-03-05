package lab3;

/**
 * Created by oskar on 2016-02-17.
 */
public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    private int size;
    private SplayNode root = null;


    private class SplayNode{
        private E value;
        private SplayNode left, right;

        public SplayNode(E value){
            this.value = value;
        }
    }

    public SplayTreeSet(){
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean add(E x){
        SplayNode newNode = new SplayNode(x);
        if(root == null){
            root = newNode;
            size++;
            return true;
        }

        //splay(root, x);

        if(this.contains(x)) {
            return false;
        }
        else{
            if(x.compareTo(root.value) < 0){
                newNode.right = root;
                newNode.left = root.left;
                root.left = null;
                root = newNode;
            }
            else{
                newNode.left = root;
                newNode.right = root.right;
                root.right = null;
                root = newNode;
            }
        }
        size++;
        return true;
    }

    public boolean remove(E x){

        if(root == null) return false;

        root = splay(root, x);
        if(root.value.compareTo(x) != 0) return false;

        if(root.right == null && root.left == null)
            root = null;

        else if(root.right == null){
            root = root.left;
        }
        else if(root.left == null) {
            root = root.right;
        }
        else{
            SplayNode temp = root.right;
            root = root.left;
            root = splay(root, x);
            root.right = temp;
        }
        size--;
        return true;
    }

    public boolean contains(E x){

        if(root == null) return false;
        root = splay(root, x);
        return(root.value.compareTo(x) == 0);
    }

    private SplayNode splay(SplayNode node, E value){


        if(node == null){
            return null;
        }

        if(value.compareTo(node.value) < 0){

            if(node.left == null){

                return node;
            }
            if(value.compareTo(node.left.value) < 0){
                node.left.left = splay(node.left.left, value);
                node = rotateRight(node);
            }
            else if(value.compareTo(node.left.value) > 0){
                node.left.right = splay(node.left.right, value);
                if(node.left.right != null){
                    node.left = rotateLeft(node.left);
                }
            }

            if(node.left == null) return node;
            else                  return rotateRight(node);
        }
        else if(value.compareTo(node.value) > 0){

            if(node.right == null){

                return node;
            }
            if(value.compareTo(node.right.value) < 0){
                node.right.left = splay(node.right.left, value);
                if(node.right.left != null)
                    node.right = rotateRight(node.right);
            }
            else if(value.compareTo(node.right.value) > 0){
                node.right.right = splay(node.right.right, value);
                node = rotateLeft(node);
            }

            if(node.right == null){
                return node;
            }
            return rotateLeft(node);
        }

        else {

            return node;

        }
    }

    private SplayNode rotateRight(SplayNode node) {
        if (node.left == null){
            return node;

        }
            else{
            SplayNode newNode = node.left;
            node.left = newNode.right;
            newNode.right = node;
            return newNode;
        }
    }

    private SplayNode rotateLeft(SplayNode node){
        if(node.right == null){
            return node;
        }

        else {
            SplayNode newNode = node.right;
            node.right = newNode.left;
            newNode.left = node;
            return newNode;
        }
    }
}
