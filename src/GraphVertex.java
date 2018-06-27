import java.util.ArrayList;

public class GraphVertex implements Vertex {

    private String name;
    private int key;
    private ArrayList<Edge> edges;


    GraphVertex(int key, String name){
        setKey(key);
        setName(name);
        edges = new ArrayList<>();
    }

     GraphVertex(int key){
        this (key, "Vertex "+key);
    }

    public void addEdge(GraphEdge edge){
        edges.add(edge);
    }

    public void removeEdge(GraphEdge edge){
        edges.remove(edge);
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(GraphVertex vertex){
        return vertex.getKey() == getKey();
    }

    public String toString(){
        return name;
    }
}
