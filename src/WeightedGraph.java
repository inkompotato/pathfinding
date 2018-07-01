import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
    
    public String findShortestPath(GraphVertex v, GraphVertex w){
        paths.clear();
        markedV.clear();
        pathfinder2(v,w,v.toString(), new HashSet<>(),0, 0);
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
    
/*    private void pathfinder(GraphVertex sourceVertex, GraphVertex destinationVertex){
        int src = sourceVertex.getKey();
        int dest = destinationVertex.getKey();
        int[] dist = new int[vertexSet.size()]; //output array
        boolean[] sptSet = new boolean[vertexSet.size()];

        PriorityQueue<GraphVertex> queue = new PriorityQueue<>();
        queue.add(sourceVertex);

        while(!queue.isEmpty()){
            GraphVertex u = queue.poll();
            for (Adjacency a : adjList.get(u.getKey())){

            }
        }



    }

    private int minDistance(int[] dist, boolean[] sptSet){
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v<vertexSet.size(); v++){
            if (sptSet[v] == false && dist[v] <= min){
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }*/

    private ArrayList<String[]> paths = new ArrayList<>();
    private HashSet<GraphVertex> markedV = new HashSet<>();

    /**
     * brute force pathfinding method that uses recursion
     */
    public void pathfinder2 (GraphVertex source, GraphVertex destination, String path, HashSet<GraphVertex> marked, int step, int cost){
        HashSet<GraphVertex> marked2 = (HashSet<GraphVertex>)marked.clone();
        markedV.add(source);
        marked2.addAll(markedV);
        step = step + 1;
        LinkedList<Adjacency> li = adjList.get(source.getKey());
        for (Adjacency adj : li){
                if (adj.getVertex().equals(destination)) {
                    //String info = mode == 1 ? " | Lenght: "+step : " | Cost: "+(cost+adj.getWeight());
                    cost = cost+adj.getWeight();
                    String[] strarr = {path+" "+adj.getVertex(), String.valueOf(step), String.valueOf(cost)};
                    paths.add(strarr);
                } else if (paths.size()>0 && Integer.parseInt(paths.get(0)[1])<step){
                    return;
                } else if (!marked2.contains(adj.getVertex())){
                   /* for (Adjacency adj2:li) {
                        marked2.add(adj2.getVertex());
                    }*/
                    marked2.add(adj.getVertex());
                    pathfinder2(adj.getVertex(), destination, path + " " + adj.getVertex(), marked2, step, cost + adj.getWeight());
                }
        }

    }
    
    public String findCheapestPath(GraphVertex v, GraphVertex w){
        //pathfinder2(v,w,v.toString(), new HashSet<>(),0, 0);
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
            int w1 = getWeight();
            int w2 = adj.getWeight();
            return k1 == k2 && w1 == w2;
        }

    }
}
