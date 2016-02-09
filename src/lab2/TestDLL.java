package lab2;

/**
 * Created by Alex on 16-02-09.
 */
public class TestDLL {

    public static void main(String[] args){

        DLList<String> test = new DLList<String>();


        //Test 1
        test.addFirst("Hej");
        test.addLast("Alex");
        DLList.Node node = test.first;
        System.out.println("Test1:\nlast = " + (String) test.last.elt);
        while(node!=null) {
            System.out.print((String) node.elt);
            node = node.next;
        }
        System.out.println("\n\n");



        // Test 2
        test.insertAfter(" där!", test.first);
        node = test.first;
        System.out.println("Test2:\nlast = " + (String) test.last.elt);
        while(node!=null) {
            System.out.print((String) node.elt);
            node = node.next;
        }
        System.out.println("\n\n");



        //Test 3
        test.insertBefore(".\n Mvh, ", test.last);
        node = test.first;
        System.out.println("Test3:\nlast = " + (String) test.last.elt);
        while(node!=null) {
            System.out.print((String) node.elt);
            node = node.next;
        }


    }
}
