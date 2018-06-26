import java.util.Set;

public interface Graph <V,E>{
    void addEdge (V sourceVertex, V targetVertex, int weight);
    void addEdge (E edge);
    //void addVertex (V vertex);
    void removeEdge(E edge);
    //void removeVertex (V vertex);
    boolean containsEdge (E edge);
    Set<V> getVertexSet();
    Set<E> getEdgeSet();
}
