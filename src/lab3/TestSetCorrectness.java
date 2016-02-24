package lab3;

import java.util.Iterator;
import java.util.Set;
import java.util.Random;
import java.util.TreeSet;

/**
 * Created by oskar on 2016-02-23.
 */
public class TestSetCorrectness {

    public static void testSet(int n1, int n2, int n3, int n4) throws MyException{
        Random rand = new Random();
        SimpleSet<Integer> set;
        boolean setBool, refSetBool;


        for(int i = 1; i <= n2; i++) {
            System.out.println("Iteration nbr: " + i);
            if(n1 == 1){
                set = new SortedLinkedListSet<>();
            }
            else if(n1 == 2){
                set = new SplayTreeSet<>();
            }
            else{
                throw new MyException("\nNot a valid n1.");
            }
            TreeSet<Integer> refSet = new TreeSet<>();
            for (int k = 1; k <= n3; k++) {

                int randomInt = rand.nextInt(4);
                if (randomInt == 0) {
                    int setSize = set.size();
                    int refSetSize = refSet.size();
                    System.out.println("\nRunning size methods:        Set Size = " + setSize + "                refSet Size = " + refSetSize);
                    if (setSize != refSetSize) {
                        throw new MyException("\nBug in size method.");
                    }
                } else if (randomInt == 1) {
                    int n4Rand = rand.nextInt(n4 - 1);
                    System.out.println("\nRunning add methods:         Trying to add [" + n4Rand + "] to the sets.");
                    setBool = set.add(n4Rand);
                    refSetBool = refSet.add(n4Rand);

                    System.out.println("\nSet returns " + setBool + "            refSet returns " + refSetBool);
                    if (setBool != refSetBool) {
                        throw new MyException("\nBug in add method.");
                    }
                } else if (randomInt == 2) {
                    int n4Rand = rand.nextInt(n4 - 1);
                    System.out.println("\nRunning remove methods:     Trying to remove [" + n4Rand + "] from the sets.");
                    setBool = set.remove(n4Rand);
                    refSetBool = refSet.remove(n4Rand);

                    System.out.println("\nSet returns " + setBool + "            refSet returns " + refSetBool);
                    if (setBool != refSetBool) {
                        throw new MyException("\nBug in remove method.");
                    }
                } else if (randomInt == 3) {
                    int n4Rand = rand.nextInt(n4 - 1);
                    System.out.println("\nRunning contains methods:     Trying to check if [" + n4Rand + "] is part of the sets.");
                    setBool = set.contains(n4Rand);
                    refSetBool = refSet.contains(n4Rand);

                    System.out.println("\nSet returns " + setBool + "            refSet returns " + refSetBool);
                    if (setBool != refSetBool) {
                        throw new MyException("\nBug in contains method.");
                    }
                } else {
                    throw new MyException("\nNot a valid random Integer.");
                }

            }
        }
    }

    public static void main(String[] args) {

        try {
            testSet(2, 10000, 100, 20);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
