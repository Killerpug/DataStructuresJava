import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public interface LinkeGraph<T> {
    boolean addEdge(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd, int weight);
    boolean addVertex(VertexInfo<T> vtx);
    void clear();
    boolean containsEdge(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd);
    boolean containsVertex(VertexInfo<T> vtx);
    ArrayList<VertexInfo<T>> getNeighbors(VertexInfo<T> vtx);
    int getWeight(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd);
    boolean isEmpty();
    int numberOfEdges();
    int numberOfVertices();
    boolean removeEdge(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd);
    boolean removeVertex(VertexInfo<T> vtx);
    int setWeight(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd, int weight);
    Set<T> vertexSet();
    LinkedList<VertexInfo <T>> bfs(VertexInfo<T> startVtx);
    LinkedList<VertexInfo <T>> dfs(VertexInfo<T> startVtx);



}
