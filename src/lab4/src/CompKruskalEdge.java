

import java.util.Comparator;

/**
 * Created by oskar on 2016-03-08.
 */
public class CompKruskalEdge<E extends Edge> implements Comparator<E> {

    public int compare(E a, E b){
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
