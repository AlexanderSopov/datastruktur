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
        TreeSet<Integer> refSet = new TreeSet<>();

        if(n1 == 1){
            set = new SortedLinkedListSet<>();
        }
        else if(n1 == 2){
            set = new SplayTreeSet<>();
        }
        else{
            throw new MyException("Not a valid n1.");
        }
        for(int i = 1; i <= n2; i++) {
            System.out.println("Iteration nbr: " + i);
            for (int k = 1; k <= n3; k++) {

                int randomInt = rand.nextInt(3);
                if (randomInt == 0) {
                    //System.out.println("Running size methods:        Set Size = " + set.size() + "            refSet Size = " + refSet.size());
                    if (set.size() != refSet.size()) {
                        throw new MyException("Bug in size method.");
                    }
                } else if (randomInt == 1) {
                    int n4Rand = rand.nextInt(n4 - 1);
                    //System.out.println("Running add methods:         Set returns " + set.add(n4Rand) + "           refSet returns " + refSet.add(n4Rand));
                    if (set.add(n4Rand) != refSet.add(n4Rand)) {
                        throw new MyException("Bug in add method.");
                    }
                } else if (randomInt == 2) {
                    int n4Rand = rand.nextInt(n4 - 1);
                    //System.out.println("Running remove methods:         Set returns " + set.remove(n4Rand) + "           refSet returns " + refSet.remove(n4Rand));
                    if (set.remove(n4Rand) != refSet.remove(n4Rand)) {
                        throw new MyException("Bug in remove method.");
                    }
                } else if (randomInt == 3) {
                    int n4Rand = rand.nextInt(n4 - 1);
                    //System.out.println("Running contains methods:         Set returns " + set.contains(n4Rand) + "           refSet returns " + refSet.contains(n4Rand));
                    if (set.contains(n4Rand) != refSet.contains(n4Rand)) {
                        throw new MyException("Bug in contains method.");
                    }
                } else {
                    throw new MyException("Not a valid random Integer.");
                }

                //Iterator iter = refSet.iterator();
                //Integer refSetCurrent = refSet.first();

                //while (iter.hasNext()) {

                //}
            }
        }
    }

    public static void main(String[] args) {
        try {
            testSet(1, 10000, 100, 20);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
