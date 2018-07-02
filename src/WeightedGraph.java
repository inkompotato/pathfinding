import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class WeightedGraph implements Graph <GraphVertex,GraphEdge> {
    private HashSet<GraphVertex>vertexSet;
    private HashSet<GraphEdge>edgeSet;
    private ArrayList<LinkedList<Adjacency>> adjList;
    private boolean saved;

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
        boolean taken = false;
        Adjacency a = new Adjacency(e.getDestinationVertex(), e.getWeight());
        Adjacency b = new Adjacency(e.getSourceVertex(), e.getWeight());
        int key = e.getSourceVertex().getKey();
        int key2 = e.getDestinationVertex().getKey();

        for (Adjacency adj :adjList.get(key)) {
            if(adj.equals(a)|| adj.equals(b)) taken = true;
        }
        if (!taken) {
            adjList.get(key).add(a);
            adjList.get(key2).add(b);
            edgeHandler(e, true);
        }


    }

/*    @Override
    public void addVertex(GraphVertex vertex) {

    }*/

    int edgeCount(){
        return edgeSet.size();
    }

    @Override
    public void removeEdge(GraphEdge edge) {
        int key1 = edge.getSourceVertex().getKey();
        int key2 = edge.getDestinationVertex().getKey();
        Adjacency adj1 = new Adjacency(edge.getDestinationVertex(), edge.getWeight());
        Adjacency adj2 = new Adjacency(edge.getSourceVertex(), edge.getWeight());
        LinkedList<Adjacency> li1 = adjList.get(key1);
        for (Adjacency a : li1
             ) {
            if(a.equals(adj1)) {
                li1.remove(a);
                edgeHandler(edge, false);
            }
        }
        LinkedList<Adjacency> li2 = adjList.get(key2);
        for (Adjacency a : li2
                ) {
            if(a.equals(adj2)) {
                li2.remove(a);
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
    
    String findShortestPath(GraphVertex v, GraphVertex w){
        runPathfinding(v, w);
        String outputString ="";
        int shortest = Integer.MAX_VALUE;
        for(String[] s : paths){
            int length = Integer.parseInt(s[1]);
            if (length<shortest) {
                outputString = s[0]+" | Length: "+length;
                shortest = length;
            }
        }
        return outputString;
    }

    private void runPathfinding(GraphVertex v, GraphVertex w) {
        if (!saved){
            paths.clear();
            markedV.clear();
            pathfinder2(v,w,v.toString(), new HashSet<>(),0, 0);
            saved = true;
        }
    }

    private ArrayList<String[]> paths = new ArrayList<>();
    private HashSet<GraphVertex> markedV = new HashSet<>();

    /**
     * brute force pathfinding method that uses recursion
     */
    private void pathfinder2(GraphVertex source, GraphVertex destination, String path, HashSet<GraphVertex> marked, int step, int cost){
        HashSet<GraphVertex> marked2 = new HashSet<>(marked);
        markedV.add(source);
        marked2.addAll(markedV);
        step = step + 1;
        LinkedList<Adjacency> li = adjList.get(source.getKey());
        for (Adjacency adj : li){
                if (adj.getVertex().equals(destination)) {
                    cost = cost+adj.getWeight();
                    String[] strarr = {path+" "+adj.getVertex(), String.valueOf(step), String.valueOf(cost)};
                    paths.add(strarr);
                } else if (paths.size()>0 && Integer.parseInt(paths.get(0)[1])<step){
                    return;
                } else if (!marked2.contains(adj.getVertex())){
                    marked2.add(adj.getVertex());
                    pathfinder2(adj.getVertex(), destination, path + " " + adj.getVertex(), marked2, step, cost + adj.getWeight());
                }
        }

    }
    
    String findCheapestPath(GraphVertex v, GraphVertex w){
        runPathfinding(v, w);
        String outputString = "";
        int cost = Integer.MAX_VALUE;
        for(String[] s : paths){
            int pathcost = Integer.parseInt(s[2]);
            if (pathcost<cost){
                outputString = s[0]+" | Cost: "+pathcost;
                cost = pathcost;
            }
        }
        return outputString;
    }

    String getAllPaths(){
        StringBuilder outputString = new StringBuilder();
        for (String[] s : paths){
            outputString.append(s[0]).append(" | Steps:").append(s[1]).append(" | Cost:").append(s[2]).append("\n");
        }
        return outputString.toString();
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (LinkedList<Adjacency> li: adjList
             ) {
            s.append(adjList.indexOf(li)).append("\t");
            for (Adjacency e : li) {
                s.append(e.toString());
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private class Adjacency {
        GraphVertex destination;
        int weight;

        Adjacency(GraphVertex destination, int weight){
            addDest(destination);
            addWeight(weight);
        }

        void addDest(GraphVertex destination){
            this.destination = destination;
        }
        void addWeight(int weight){
            this.weight = weight;
        }

        GraphVertex getVertex(){
            return destination;
        }

        int getWeight(){
            return weight;
        }

        public String toString(){
            return "["+destination+"/"+weight+"]";
        }

        boolean equals(Adjacency adj){
            int k1 = getVertex().getKey();
            int k2 = adj.getVertex().getKey();
            //int w1 = getWeight();
            //int w2 = adj.getWeight();
            return k1 == k2;
        }

    }
}
