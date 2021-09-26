import java.util.LinkedList;

public class VertexInfo<T> {
    private Integer vertexLocation = this.hashCode();
    private LinkedList<Edge> edgeList;
    private int inDegree = 0;
    private boolean isOccupied = false;
    private String color = "white";
    private T data;

    public VertexInfo(){
        edgeList = new LinkedList<Edge>();
        isOccupied = true;
    }
    public VertexInfo(T value){
        edgeList = new LinkedList<Edge>();
        isOccupied = true;
        data = value;
    }

    public Integer getVertexLocation() { return vertexLocation; }
    public LinkedList<Edge> getEdgeList() { return edgeList; }
    public int getInDegree() { return inDegree; }
    public boolean getIsOccupied() { return isOccupied; }
    public String getColor() { return color; }
    public T getData() { return data; }

    public void setColor(String color) { this.color = color; }
    public void setEdgeList(LinkedList<Edge> edgeList) { this.edgeList = edgeList; }
    public void setInDegree(int inDegree) { this.inDegree = inDegree; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
    public void setVertexLocation(Integer vertexLocation) { this.vertexLocation = vertexLocation; }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
