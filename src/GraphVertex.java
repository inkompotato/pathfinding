public class GraphVertex implements Vertex {

    private String name;
    private int key;


    GraphVertex(int key, String name){
        setKey(key);
        setName(name);
    }

     GraphVertex(int key){
        setKey(key);
        setName("Vertex "+key);
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
