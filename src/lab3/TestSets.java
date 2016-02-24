package lab3;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by Alex and Oscar on 16-02-23.
 */
public class TestSets {
    static SortedLinkedListSet set;

    public static void main(String[] args){
        set = new SortedLinkedListSet<Integer>();
        Random rand = new Random();
        int[] testElts = new int[20];

        for (int i =0; i<20; i++){
            int nextInt = rand.nextInt(40);
            testElts[i]=nextInt;
            set.add(nextInt);
        }
        System.out.println("printing after add");
        print(testElts);


        System.out.println("\nbeginning removals...");//*
        int[] eraseInt = new int[5];
        for(int i = 0; i<5;i++){
            int nextInt = testElts[rand.nextInt(20)];
            eraseInt[i] = nextInt;
            set.remove(nextInt);
        }

        System.out.println("\nprinting after remove");
        print(eraseInt);//*/
/*
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(4);
        set.add(5);
        int[] arr = {1,1,1,2,2,3,3,4,5};
        print(arr);//*/
    }


    private static void print(int[] arr) {
        System.out.println("Array to be used:");
        for(int i=0; i<arr.length; i++)
            System.out.print("[" + i + "]=>>  " + arr[i] + ",  ");

        int element = 0;
        System.out.println("\n\nlinkedList size = " + set.size());

        Iterator<Integer> iter = set.iterator();
        while(iter.hasNext()){
            int nextInt = iter.next();
            System.out.print("[" + element + "]= " + nextInt + ", ");
            element++;
        }//*/
    }
}
