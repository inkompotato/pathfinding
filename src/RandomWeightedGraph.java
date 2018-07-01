import java.util.HashSet;
import java.util.Random;


public class RandomWeightedGraph {

    private GraphVertex[] vertArray;
    private WeightedGraph g;
    private static int MAXWEIGHT = 20;
    Random rnd = new Random();

    /*
     * take edges and vertices as input
     * generate randomly chosen edges between the vertices
     * given them a random weight as well
     * */
    public WeightedGraph generateGraph(int vertices, int edges){
        vertArray = new GraphVertex[vertices];
        g = new WeightedGraph(vertices);

        for(int i = 0; i<vertices; i++){
            GraphVertex a = new GraphVertex(i);
            // we now have an array with all the vertices from 0 to "vertices"
            vertArray[i] = a;
        }

        while(g.edgeCount() < edges){
            int[] vertPos = twoRandomInt(vertices);
            int weight = rnd.nextInt(MAXWEIGHT)+1;

            /*choose two random vertices in the array and create an edge for them with a random weight
            doesn't avoid duplicates edges yet, duplicates probably have a different weight so it's not
            too much of a problem anyway*/
            g.addEdge(vertArray[vertPos[0]], vertArray[vertPos[1]], weight);
        }
        /*System.out.println(g);*/
        return g;
    }

    /*
    *  take a graph and print out the shortest path in readable format
    */
    public String printPathOperations(){
        //generate two random vertices as the input for the shortest path algorithm
        int[] vertPos = twoRandomInt(vertArray.length);
        //use the values to access two vertices
        GraphVertex a = vertArray[vertPos[0]];
        GraphVertex b = vertArray[vertPos[1]];

        //use shortestPath method from WeightedGraph class to get the shortest graph
        return g.findShortestPath(a,b)+"\n"+g.findCheapestPath(a,b);
        //print out nicely
    }

    public int[] twoRandomInt(int limit){
        int[] a =new int[2];
        int pos1 = rnd.nextInt(limit);
        int pos2 = rnd.nextInt(limit);
        //ensure no duplicates
        while(pos1 == pos2){
            pos2 = rnd.nextInt(limit);
        }
        a[0] = pos1;
        a[1] = pos2;

        return a;
    }

    public String toString(){
        return g.toString();
    }
}
