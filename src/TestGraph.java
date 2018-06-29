public class TestGraph {

    public static void main (String[] args){
       /* WeightedGraph g = new WeightedGraph(4);
        GraphVertex v0 = new GraphVertex(0);
        GraphVertex v1 = new GraphVertex(1, "Vertex 1");
        GraphVertex v2 = new GraphVertex(2, "Vertex 2");
        GraphVertex v3 = new GraphVertex(3);

        System.out.println(v1+"\n"+v2+"\n"+v3);

        GraphEdge e10 = new GraphEdge(v1, v0, 2);
        GraphEdge e12 = new GraphEdge(v1, v2, 3);
        GraphEdge e21 = new GraphEdge(v2, v1, 3);
        GraphEdge e13 = new GraphEdge(v1, v3, 7);
        GraphEdge e23 = new GraphEdge(v2, v3, 9);
        GraphEdge e20 = new GraphEdge(v2, v0, 4);

        System.out.println(e10+"\n"+e12+"\n"+e13);

       g.addEdge(e12);
       g.addEdge(e13);
       g.addEdge(e10);
       g.addEdge(e23);
       g.addEdge(e20);

        System.out.println(g);*/

        //System.out.println(g.containsEdge(e21)+"\n");

        //g.removeEdge(e12);

        //System.out.println(g);
        //System.out.println(g.containsEdge(e12)+"\n");

        RandomWeightedGraph rg = new RandomWeightedGraph();
        rg.generateGraph(20,40);

        System.out.println(rg);

    }
}
