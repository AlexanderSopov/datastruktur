package lab2;

/**
 * Created by Alex on 16-02-09.
 */
public class TestDLL {

    public static void main(String[] args){

        DLList<String> test = new DLList<String>();
        test.addFirst("Hej");
        test.addLast("Alex");
        test.insertAfter(" där!", test.first);
        test.insertBefore(".\n Mvh, ", test.last);

        DLList.Node node = test.first;
        while(node!=null) {
            System.out.print((String) node.elt);
            node = node.next;
        }

    }
}
