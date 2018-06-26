public class TestGraph {

    public static void main (String[] args){
        WeightedGraph g = new WeightedGraph();
        GraphVertex v0 = new GraphVertex(0);
        GraphVertex v1 = new GraphVertex(1, "Vertex 1");
        GraphVertex v2 = new GraphVertex(2, "Vertex 2");
        GraphVertex v3 = new GraphVertex(3);

        System.out.println(v1+"\n"+v2+"\n"+v3);

        GraphEdge e01 = new GraphEdge(v0, v1, 2);
        GraphEdge e12 = new GraphEdge(v1, v2, 5);
        GraphEdge e13 = new GraphEdge(v1, v3, 7);

        System.out.println(e01+"\n"+e12+"\n"+e13);

        g.addEdge(e01);
        g.addEdge(v0, v2, 8);
        g.addEdge(e12);
        g.addEdge(e13);

        System.out.println(g);

        System.out.println(g.containsEdge(e12)+"\n");

        g.removeEdge(e12);

        System.out.println(g);
        System.out.println(g.containsEdge(e12)+"\n");
    }
}
