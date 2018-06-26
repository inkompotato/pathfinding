import java.util.*;

public class WeightedGraph implements Graph <GraphVertex,GraphEdge> {
    private HashSet<GraphVertex>vertexSet;
    private HashSet<GraphEdge>edgeSet;
    private ArrayList<LinkedList<Adjacency>> adjList;

    WeightedGraph(){
        vertexSet = new HashSet<>();
        edgeSet = new HashSet<>();
        adjList = new ArrayList<>();
    }
    @Override
    public void addEdge(GraphVertex sourceVertex, GraphVertex targetVertex, int weight) {
        GraphEdge e = new GraphEdge(sourceVertex, targetVertex, weight);
        addEdge(e);
    }

    @Override
    public void addEdge(GraphEdge edge) {
        GraphEdge e = edge;
        Adjacency a = new Adjacency(e.getDestinationVertex(), e.getWeight());
        int key = e.getSourceVertex().getKey();
        //System.out.println(key);
        //there are no adjacent vertices for this vertex yet
        try{
            adjList.get(key);
        } catch(IndexOutOfBoundsException ex){
            //System.out.println("hi");
            LinkedList<Adjacency> l = new LinkedList<>();
            l.addFirst(a);
            adjList.add(key, l);
            return;
        }
        //add the adjacency to an existing vertex
            LinkedList<Adjacency> l = adjList.get(key);
            l.add(a);
        //add to edge set
            edgeSet.add(e);

    }

/*    @Override
    public void addVertex(GraphVertex vertex) {

    }*/

    @Override
    public void removeEdge(GraphEdge edge) {
        int key =edge.getSourceVertex().getKey();
        Adjacency adj = new Adjacency(edge.getDestinationVertex(), edge.getWeight());
        LinkedList<Adjacency> l = adjList.get(key);
        for (Adjacency a : l
             ) {
            if(a.equals(adj)) {
                l.remove(a);
                edgeSet.remove(edge);
            }
        }
    }

/*    @Override
    public void removeVertex(GraphVertex vertex) {

    }*/

    @Override
    public boolean containsEdge(GraphEdge edge) {
        int key = edge.getSourceVertex().getKey();
        Adjacency adj = new Adjacency(edge.getDestinationVertex(), edge.getWeight());
        LinkedList<Adjacency> l = adjList.get(key);
            for (Adjacency a : l
                 ) {
                if (a.equals(adj)) return true;
            }
            return false;
    }

    @Override
    public HashSet<GraphVertex> getVertexSet() {
        return vertexSet;
    }

    @Override
    public HashSet<GraphEdge> getEdgeSet() {
        return edgeSet;
    }
    
    public int findShortestPath(GraphVertex v, GraphVertex w){
        int steps = 0;
        int sourceKey = v.getKey();
        int destinationKey = w.getKey();
        if (sourceKey == destinationKey) return steps;
        return steps;
    }
    
    private int pathfinder(int sourceKey, int destinationKey, int step){
        int steps = step;
        if (sourceKey == destinationKey) return steps;
        else {
            LinkedList<Adjacency> l = adjList.get(sourceKey)
            for (Adjacency a : l
                 ) {
                
            }
        }

    }
    
    public int findCheapestPath(GraphVertex v, GraphVertex v){
        int cost = 0;
        
        return cost;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (LinkedList<Adjacency> l: adjList
             ) {
            s.append(adjList.indexOf(l));
            for (Adjacency e : l) {
                s.append(e.toString()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private class Adjacency {
        Vertex destination;
        int weight;

        Adjacency(Vertex destination, int weight){
            addDest(destination);
            addWeight(weight);
        }

        void addDest(Vertex destination){
            this.destination = destination;
        }
        void addWeight(int weight){
            this.weight = weight;
        }

        Vertex getVertex(){
            return destination;
        }

        int getWeight(){
            return weight;
        }

        public String toString(){
            return "["+destination+"/"+weight+"]";
        }

        boolean equals(Adjacency adj){
            return getVertex() == adj.getVertex() && getWeight() == adj.getWeight();
        }

    }
}
