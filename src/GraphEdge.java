public class GraphEdge implements Edge<GraphVertex> {

    private GraphVertex source;
    private GraphVertex destination;
    private int weight;

    private static final int DEFAULT_WEIGHT = 1;

    public GraphEdge(){

    }

    public GraphEdge(GraphVertex source, GraphVertex destination, int weight){
        setSourceVertex(source);
        setDestinationVertex(destination);
        setWeight(weight);
    }
    @Override
    public void setSourceVertex(GraphVertex sourceVertex) {
        source = sourceVertex;
    }

    @Override
    public void setDestinationVertex(GraphVertex destinationVertex) {
        destination = destinationVertex;
    }

    @Override
    public GraphVertex getSourceVertex() {
        return source;
    }

    @Override
    public GraphVertex getDestinationVertex() {
        return destination;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "Edge ["+source+" , "+destination+"]";
    }
}
