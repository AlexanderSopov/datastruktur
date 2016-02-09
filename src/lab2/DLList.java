package lab2;

/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
  public class Node {
    /** The contents of the node is public */
    public E elt;

    protected Node prev, next;

    Node() {
      this(null);
    }
    
    Node(E elt) {
      this.elt = elt;
      prev = next = null;
      size++;
    }


    public Node getNext() {
      return next;
    }

    public Node getPrev() {
      return prev;
    }
  }
  
  /** first and last nodes in list, null when list is empty */
  Node first, last;
  public int size; 
  
  DLList() {
      first = last = null;
      size = 0;
  }

  /** inserts an element at the beginning of the list
   * @param e   the new element value
   * @return    the node holding the added element
   */
  public Node addFirst(E e) {
      return insertBefore(e, first);
  }

  /** inserts an element at then end of the list
   * @param e   the new element
   * @return    the node holding the added element
   */
  public Node addLast(E e) {
      return insertAfter(e, last);
  }
  
  /**
   * @return    the node of the list's first element, null if list is empty
   */
  public Node getFirst() {
      return first;
  }
  
  /**
   * @return    the node of the list's last element, null if list is empty
   */
  public Node getLast() {
      return last;
  }
  
  /** inserts a new element after a specified node
    * @param e   the new element
    * @param after   the node after which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertAfter(E e, Node after) {
      Node newNode;

      if(after != null) {
          newNode = new Node(e);
          newNode.next = after.next;
          newNode.prev = after;
          if (after != last)
              after.next.prev = newNode;
          after.next = newNode;
      }else if (size == 0)
          newNode = new Node(e);
      else{
          newNode = new Node(e);
          first.next = newNode;
          newNode.prev = first;
      }
      if (after == last)
          last = newNode;


      size++;
      return newNode;
  }

  /** inserts a new element before a specified node
    * @param e   the new element
    * @param before   the node before which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertBefore(E e, Node before) {
      Node newNode;
      if (before != null) {
          newNode = new Node(e);
          newNode.prev = before.prev;
          newNode.next = before;
          if (before != first)
              before.prev.next = newNode;
          before.prev = newNode;
      }else if (size == 0){
          newNode = new Node(e);
      }else {
          newNode = new Node(e);
          newNode.next = last;
          first = newNode;
      }
      if (before == first)
          first = newNode;
      size++;


      return newNode;
  }


    /** removes an element
    * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
    */
  public void remove(Node l) {
      if(l == last) {
          l.prev.next = null;
          last = l.prev;
      }else if (l == first ) {
          l.next.prev = null;
          first = l.next;
      }else {
          l.next.prev = l.prev;
          l.prev.next = l.next;
      }
  }

}
