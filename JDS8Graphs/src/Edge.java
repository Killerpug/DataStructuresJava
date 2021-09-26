public class Edge {
    public int dest;    //index of destination vertex in list of vertices
    public int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
    public boolean isSameEdge(int edgeDest) {         //if the edge goes from the same origin to the same vertex
        return edgeDest == this.dest;      //then its the same edge
    }

}
