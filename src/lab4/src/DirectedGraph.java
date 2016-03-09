package lab4.src;

import java.util.*;

public class DirectedGraph<E extends Edge> {

    //private List<E> edgeList;
    private ArrayList<ArrayList<E>> edgeList;
    private int noOfNodes;

    public DirectedGraph(int noOfNodes) {
        edgeList = new ArrayList<>();
        for(int i = 0; i < noOfNodes; i++){
            edgeList.add(new ArrayList<>());
        }
        //edgeList = new ArrayList<>();
        this.noOfNodes = noOfNodes;
    }

    public void addEdge(E e) {
        //edgeList.add(e);
        edgeList.get(e.getSource()).add(e);
    }

    public Iterator<E> shortestPath(int from, int to) {

        ArrayList<Integer> knownNodes = new ArrayList<>();
        PriorityQueue<DijkstraElement<E>> pq = new PriorityQueue<>(new CompDijkstraPath ());
        LinkedList<E> path = new LinkedList<>();
        DijkstraElement<E> ele = new DijkstraElement<>(from, 0.0, path);
        pq.add(ele);
        while(!pq.isEmpty()){
            DijkstraElement<E> temp = pq.poll();
            int node = temp.getNode();
            if(!knownNodes.contains(node)){
                if(node == to){
                    return temp.getPath().iterator();
                }
                knownNodes.add(node);
                for(E e: edgeList.get(node)){
                    if(!knownNodes.contains(e.getDest())){
                        LinkedList<E> clone = (LinkedList<E>) temp.getPath().clone();
                        clone.add(e);
                        DijkstraElement<E> newEle = new DijkstraElement<>(e.getDest(), temp.getWeight()+e.getWeight(), clone);
                        pq.add(newEle);
                        /*temp.getPath().add(e);
                        temp.weight += e.getWeight();
                        pq.add(temp);*/
                    }
                }
            }
        }
        return null;
    }

    public Iterator<E> minimumSpanningTree() {
        ArrayList<ArrayList<E>> cc = new ArrayList<>(noOfNodes);
        int ccSize = 0;
        for(int i = 0; i<noOfNodes; i++){
            cc.add(new ArrayList<>());
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(new CompKruskalEdge<>());
        for (ArrayList<E> edges: edgeList) {
            pq.addAll(edges);
        }
        System.out.println("Size = " + edgeList.size());
        while(!pq.isEmpty() && ccSize < noOfNodes){
            Edge e = pq.poll();
            int origin = e.getSource();
            int destination = e.getDest();
            if(!(cc.get(origin) == cc.get(destination))){
                ArrayList<E> smallList;
                ArrayList<E> bigList;
                int small;
                if(cc.get(origin).size() > cc.get(destination).size()){
                    bigList = cc.get(origin);
                    smallList = cc.get(destination);
                    small = destination;
                }
                else{
                    bigList = cc.get(destination);
                    smallList = cc.get(origin);
                    small = origin;
                }
                for(E edge: smallList){
                    bigList.add(edge);
                    cc.set(edge.getSource(), bigList);
                    cc.set(edge.getDest(), bigList);
                }

                cc.set(small, bigList);
                ccSize++;

                bigList.add((E)e);
            }
        }
        return cc.get(0).iterator();
    }

    private DijkstraElement<E> updateElement(DijkstraElement<E> ele){

    }


}



