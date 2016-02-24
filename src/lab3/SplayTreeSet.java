package lab3;

import java.util.Iterator;

/**
 * Created by oskar on 2016-02-17.
 */
public class SplayTreeSet<E extends Comparable<? super E>> extends BinaryTree
        implements SimpleSet<E>, Iterable<E> {


    protected class SplayNode<E extends Comparable<? super E>> extends Node{

        public SplayNode(Node n){
            super(n);
            size++;
        }


        /**
         * Construct an node with given Data and no children.
         *
         * @param data The data stored in node
         */
        public SplayNode(E data) {
            super(data);
            size++;
        }
    }

    protected int size;



    public SplayTreeSet(){
        super();
    }

    public SplayTreeSet(Node root, Node parent) {
        super(root, parent);
    }

    protected SplayTreeSet(SplayNode<E> root){
        super(root);
    }



    public SplayTreeSet(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        super(data, leftTree, rightTree);
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {

        SplayNode newNode;
        int c;

        if (root == null) {
            root = new SplayNode(x);
            return true;
        }

        splay(x);


        c = x.compareTo((E) root.data);
        if (c == 0) {
            return false;
        }


        newNode = new SplayNode(x);
        if (c < 0) {
            newNode.left = root.left;
            newNode.right = root;
            root.left = null;
        } else {
            newNode.right = root.right;
            newNode.left = root;
            root.right = null;
        }
        root = newNode;
        return true;
    }

    @Override
    public boolean remove(E x) {
        SplayNode node;
        splay(x);
        if (x.compareTo((E)root.data) != 0) {
            //            throw new ItemNotFoundException(x.toString());
            return false;
        }
        // Now delete the root
        if (root.left == null) {
            root = root.right;
        } else {
            node = (SplayNode) root.right;
            root = root.left;
            splay(x);
            root.right = node;
        }
        size--;
        return true;
    }


    @Override
    public boolean contains(E x) {
        if (root == null) return false;
        splay(x);
        if(root.data.compareTo(x) != 0) return false;
        return true;
    }


    @Override
    public Iterator<E> iterator() {
        return new STSIterator();
    }


    /**
     * Internal method to perform a top-down splay.
     *
     *   splay(key) does the splay operation on the given key.
     *   If key is in the tree, then the BinaryNode containing
     *   that key becomes the root.  If key is not in the tree,
     *   then after the splay, key.root is either the greatest key
     *   < key in the tree, or the lest key > key in the tree.
     *
     *   This means, among other things, that if you splay with
     *   a key that's larger than any in the tree, the rightmost
     *   node of the tree becomes the root.  This property is used
     *   in the delete() method.
     */

    private void splay(E x) {
        Node l, r, t, y;
        l = r = root;
        t = root;
        root.left = root.right = null;
        for (;;) {
            if (x.compareTo((E)t.data) < 0) {
                if (t.left == null) break;
                if (x.compareTo((E)t.left.data) < 0) {
                    y = t.left;                            /* rotate right */
                    t.left = y.right;
                    y.right = t;
                    t = y;
                    if (t.left == null) break;
                }
                r.left = t;                                 /* link right */
                r = t;
                t = t.left;
            } else if (x.compareTo((E)t.data) > 0) {
                if (t.right == null) break;
                if (x.compareTo((E)t.right.data) > 0) {
                    y = t.right;                            /* rotate left */
                    t.right = y.left;
                    y.left = t;
                    t = y;
                    if (t.right == null) break;
                }
                l.right = t;                                /* link left */
                l = t;
                t = t.right;
            } else {
                break;
            }
        }
        l.right = t.left;                                   /* assemble */
        r.left = t.right;
        t.left = root.right;
        t.right = root.left;
        root = t;
    }


    private class STSIterator<E extends Comparable<? super E>> implements Iterator<E>{
        /**
         * A reference to the root of a splay tree.
         */
        protected SplayTreeSet<E> tree; // node of splay tree, root computed
        /**
         * The current node being considered in tree.
         */
        protected SplayTreeSet<E> current;


        /**
         * Construct an iterator that traverses the binary search
         * tree based at the root.
         *
         */
        public STSIterator()
        {
            tree = new SplayTreeSet<E>((SplayNode)root);
            reset();
        }

        /**
         * Reset the iterator to reference the root of the tree.
         *
         * @post resets iterator to smallest node in tree
         */
        public void reset()
        {
            current = tree;
            if (!current.isEmpty()) {
                current = new SplayTreeSet<E>((SplayNode)current.root);
                while (!(new SplayTreeSet<E>((SplayNode)current.root.left)).isEmpty())
                    current = new SplayTreeSet<E>((SplayNode)current.root.left);
            }
        }

        /**
         * Determine if the iterator has more nodes to be considered.
         *
         * @post returns true if there are unvisited nodes
         *
         * @return True iff the iterator has more nodes to be considered.
         */
        public boolean hasNext()
        {
            return !current.isEmpty();
        }

        /**
         * Returns reference to the current element, and increments iterator.
         *
         * @pre hasNext()
         * @post returns current element and increments iterator
         *
         * @return The reference to the current element before incrementing.
         */
        public E next()
        {
            E result = (E)current.root.data;
            SplayTreeSet<E> newCurrent = newCurrent = new SplayTreeSet<E>((SplayNode)current.root.right, current.root);
            if (!newCurrent.isEmpty()) {
                current = newCurrent;

                while (!(newCurrent = new SplayTreeSet<E>
                        ((SplayNode)current.root.left, current.root))
                        .isEmpty())
                    current = newCurrent;

            } else {
                // we're finished with current's subtree
                boolean lefty;
                do
                {
                    lefty = current.isLeftChild();
                    current = new SplayTreeSet<E>(new SplayNode(current.parent));
                } while (current != null && !lefty);

                if (current == null)
                    current = new SplayTreeSet<E>();
            }
            return result;
        }

        /**
         * Return a reference to the current value.
         *
         * @pre hasNext()
         * @post returns current value
         *
         * @return A reference to the current value.
         */
        public E get()
        {
            return (E) current.root.data;
        }
    }

}
