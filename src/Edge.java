public interface Edge<V> {
    void setSourceVertex(V sourceVertex);
    void setDestinationVertex(V destinationVertex);
    V getSourceVertex();
    V getDestinationVertex();
    void setWeight(int weight);
    int getWeight();
    String toString();
}
