import java.util.*;

public class WeightedGraph implements Graph <GraphVertex,GraphEdge> {
    private HashSet<GraphVertex>vertexSet;
    private HashSet<GraphEdge>edgeSet;
    private ArrayList<LinkedList<Adjacency>> adjList;

    WeightedGraph(int vertices){
        vertexSet = new HashSet<>();
        edgeSet = new HashSet<>();
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(i, new LinkedList<>());
        }
    }
    @Override
    public void addEdge(GraphVertex sourceVertex, GraphVertex targetVertex, int weight) {
        GraphEdge e = new GraphEdge(sourceVertex, targetVertex, weight);
        addEdge(e);
    }

    @Override
    public void addEdge(GraphEdge e) {
        Adjacency a = new Adjacency(e.getDestinationVertex(), e.getWeight());
        Adjacency b = new Adjacency(e.getSourceVertex(), e.getWeight());
        int key = e.getSourceVertex().getKey();
        int key2 = e.getDestinationVertex().getKey();
            
            adjList.get(key).add(a);
            adjList.get(key2).add(b);

            //add edge to edge set
            edgeHandler(e, true);
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
                edgeHandler(edge, false);
            }
        }
    }

    private void edgeHandler(GraphEdge edge, boolean keep) {
        GraphVertex v = edge.getSourceVertex();
        GraphVertex w = edge.getDestinationVertex();
        if (!keep) {
            edgeSet.remove(edge);

            v.removeEdge(edge);
            w.removeEdge(edge);
            if (edge.getSourceVertex().getEdges().isEmpty()) {
                vertexSet.remove(edge.getSourceVertex());
            }
            if (edge.getDestinationVertex().getEdges().isEmpty()) {
                vertexSet.remove(edge.getDestinationVertex());
            }
        } else {
            //add edge to edge set
            edgeSet.add(edge);
            //add edges to the vertices
            v.addEdge(edge);
            w.addEdge(edge);
            //add vertices to the vertex set
            vertexSet.add(v);
            vertexSet.add(w);
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
            LinkedList<Adjacency> l = adjList.get(sourceKey);
            for (Adjacency a : l
                 ) {
                
            }
        }
        return steps;
    }
    
    public int findCheapestPath(GraphVertex v, GraphVertex w){
        int cost = 0;
        
        return cost;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (LinkedList<Adjacency> li: adjList
             ) {
            s.append(adjList.indexOf(li));
            for (Adjacency e : li) {
                s.append(e.toString());
                s.append(" ");
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
