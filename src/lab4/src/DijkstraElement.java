package lab4.src;


import java.util.LinkedList;

/**
 * Created by oskar on 2016-03-08.
 */
public class DijkstraElement<E extends Edge> {

    int node;
    double weight;
    LinkedList<E> path;

    public DijkstraElement(int node, double weight, LinkedList<E> path){
        this.node = node;
        this.weight = weight;
        this.path = path;
    }

    public int getNode(){
        return node;
    }

    public double getWeight(){
        return weight;
    }

    public LinkedList<E> getPath(){
        return path;
    }

}
