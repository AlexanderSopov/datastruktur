package lab3;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Alex and Oscar on 16-02-23.
 */
public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet,
        Iterable<E>{
    public int size;
    public Node<E> smallest;

    @Override
    public Iterator<E> iterator() {
        return new SLLSIterator<E>();
    }


    public class Node<E extends Comparable<? super E>> {
        /**
         * The contents of the node is public
         */
        public E elt;

        protected Node next;

        Node() {
            this(null);
        }

        Node(E elt) {
            this.elt = elt;
            next = null;
            size++;
        }


        public Node getNext() {
            return next;
        }


    }



    SortedLinkedListSet() {
        smallest = null;
        size = 0;
    }


    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean add(Comparable x) {
        if (smallest == null){
            //System.out.println("adding smallest, x = " + x);
            smallest = new Node(x);
            return true;
        }else if (x.compareTo(smallest.elt) < 0 ){
            //System.out.println("inserting before smallest, x = " + x);
            smallest = insertBefore((E)x, smallest);
            //size++;
            return true;
        }else if (x.compareTo(smallest.elt) == 0)
            return false;
        else if(smallest.getNext() == null) {
            if (x.compareTo(smallest.elt)>0) {
                smallest.next = new Node(x);
                return true;
            }
        }
        else
            return iterateOverList(x);

        return false;
    }


    @Override
    public boolean remove(Comparable x) {
        if (smallest == null)
            return false;
        if (x.compareTo(smallest.elt)==0) {
            smallest = smallest.getNext();
            size--;
            return true;
        }
        Node currentNode = smallest;
        while (currentNode.getNext() != null) {
            Node nextNode = currentNode.getNext();
            if (x.compareTo(nextNode.elt) == 0) {
                if (currentNode.getNext().getNext() == null) // => currentNode.next == largest elt
                    currentNode.next = null;
                else
                    currentNode.next = currentNode.getNext().getNext();
                size--;
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public boolean contains(Comparable x) {
        if (smallest == null)
            return false;
        if (x.compareTo(smallest.elt)==0)
            return true;
        if(smallest.getNext() == null)
            return false;
        Node currentNode = smallest.getNext();
        while (currentNode != null){
            if (x.compareTo(currentNode.elt) == 0)
                return true;
            currentNode = currentNode.getNext();
        }
        return false;
    }



    private Boolean iterateOverList(Comparable x) {
        Node currentNode = smallest;
        while (currentNode.getNext() != null){
            int cmpt = x.compareTo(currentNode.getNext().elt);  //compare with currentNode.next
            if (cmpt == 0) // x is equal to nextNode, do not insert
                return false;
            else if (cmpt < 0) { //x is less than nextNode, insert x before it
                currentNode.next = insertBefore((E)x, currentNode.getNext());
                //size++;
                return true;
            }
            //x is larger than nextNode by this stage, and if nextNode is largest, well then X is the new Largest
            else if (currentNode.getNext().getNext() == null) {
                Node largest = new Node(x);
                currentNode.getNext().next = largest;
                //size++;
                return true;
            }
            //keep iterating over list
            else
                currentNode = currentNode.getNext();
        }
        return false;//*/
    }


    private Node insertBefore(E x, Node larger) {
        Node newNode = new Node(x);
        newNode.next = larger;
        return newNode;
    }





    private class SLLSIterator<E extends Comparable<? super E>> implements Iterator<E>{
        private Node<E> nextItem;
        private Node<E> lastItemReturned;

        public SLLSIterator(){
            nextItem =(Node) smallest;
            lastItemReturned = null;
        }

        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        @Override
        public E next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            return lastItemReturned.elt;
        }

        @Override
        public void remove() {

        }
    }

}
