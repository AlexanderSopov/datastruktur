

import java.util.*;

public class DirectedGraph<E extends Edge> {


    private List<E>[] edges;
    private int nrOfNodes;

    public DirectedGraph(int noOfNodes) {
        nrOfNodes = noOfNodes;
        edges = new List[nrOfNodes];
        for (int i = 0; i < nrOfNodes; i++)
            edges[i] = new LinkedList<>();
    }

    public void addEdge(E e) {
        edges[e.getSource()].add(e);
    }

    public Iterator<E> shortestPath(int from, int to) {

        ArrayList<Integer> visitedNodes = new ArrayList<>(nrOfNodes);
        PriorityQueue<DijkstraElement<E>> pq = new PriorityQueue<>(new CompDijkstraPath());
        pq.add(new DijkstraElement<>(from, 0, new LinkedList<>()));
        while (!pq.isEmpty()) {
            DijkstraElement<E> temp = pq.poll();
            if (!visitedNodes.contains(temp.getNode())) {
                if (temp.getNode() == to) {
                    return temp.getPath().iterator();
                } else {
                    visitedNodes.add(temp.getNode());
                    for (E edge : edges[temp.getNode()]) {
                        if (!visitedNodes.contains(edge.getDest())) {
                            LinkedList<E> clone = (LinkedList<E>) temp.getPath().clone();
                            clone.add(edge);
                            pq.add(new DijkstraElement<>(edge.getDest(), temp.getWeight() + edge.getWeight(), clone));
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public Iterator<E> minimumSpanningTree() {

        ArrayList<E>[] cc = new ArrayList[nrOfNodes];
        int ccSize = 0;
        PriorityQueue<E> pq = new PriorityQueue<>(new CompKruskalEdge<>());
        for(int i = 0; i < nrOfNodes; i++) {
            cc[i] = new ArrayList<>();
            pq.addAll(edges[i]);
        }
        while(!pq.isEmpty() && ccSize < nrOfNodes) {
            E temp = pq.poll();
            if(cc[temp.getDest()] != cc[temp.getSource()]) {
                ArrayList<E> bigList, smallList;
                int small;
                if(cc[temp.getSource()].size() <= cc[temp.getDest()].size()) {
                    bigList = cc[temp.getDest()];
                    smallList = cc[temp.getSource()];
                    small = temp.getSource();
                }
                else {
                    bigList = cc[temp.getSource()];
                    smallList = cc[temp.getDest()];
                    small = temp.getDest();
                }
                for(E edge: smallList) {
                    bigList.add(edge);
                    cc[edge.getSource()] = bigList;
                    cc[edge.getDest()] = bigList;
                }

                bigList.add(temp);
                cc[small] = bigList;
                ccSize++;
            }
        }
        
        return cc[0].iterator();
    }
}
