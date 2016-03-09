package lab4.src;


import java.util.Comparator;

public class CompDijkstraPath implements Comparator<DijkstraElement> {

    public int compare(DijkstraElement a, DijkstraElement b){
        if(a == b){
            return 0;
        }
        else if(a == null){
            return -1;
        }
        else if(b == null){
            return 1;
        }
        return Double.compare(a.getWeight(), b.getWeight());
    }
}