package lab3;

/**
 * Created by oskar on 2016-02-17.
 */
public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    private int size;
    private SplayNode root;

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
        size++;
        return false;
    }

    public boolean remove(E x){

        size--;
        return false;
    }

    public boolean contains(E x){
        return false;
    }

    private SplayNode splay(SplayNode node, E value){
        if(node == null) return null;

        if(value.compareTo(node.value) < 0){
            if(node.left == null) return node;
            if(value.compareTo(node.left.value) < 0){
                node.left.left = splay(node.left.left, value);
                node = rotateRight(node);
            }
            else if(value.compareTo(node.left.value) > 0){
                node.left.right = splay(node.left.right, value);
            }
        }

        return node;
    }

    private SplayNode rotateRight(SplayNode node){
        SplayNode newNode = node.left;
        node.left = newNode.right;
        newNode.right = node;
        return newNode;
    }

    private SplayNode rotateLeft(SplayNode node){
        SplayNode newNode = node.right;
        node.right = newNode.left;
        newNode.left = node;
        return newNode;
    }

}
