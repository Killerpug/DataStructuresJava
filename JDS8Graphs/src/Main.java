import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        VertexInfo<Integer> vertex1 = new VertexInfo<>(1);
        VertexInfo<Integer> vertex2 = new VertexInfo<>(2);
        VertexInfo<Integer> vertex3 = new VertexInfo<>(3);
        VertexInfo<Integer> vertex4 = new VertexInfo<>(4);
        VertexInfo<Integer> vertex5 = new VertexInfo<>(5);

        LinkedGraph<Integer> graph = new LinkedGraph<>(1);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);

        graph.addEdge(vertex1, vertex2, 1);
        graph.addEdge(vertex2, vertex4, 3);


        System.out.println(graph.containsEdge(vertex1, vertex2));
        System.out.println(graph.containsEdge(vertex2, vertex3));


        //System.out.println(graph.getWeight(vertex1, vertex2));
        //System.out.println(graph.containsVertex(vertex4));
        //System.out.println(graph.containsEdge(vertex2,vertex1));
        //System.out.println(graph.numberOfVertices());
        //System.out.println(graph.numberOfEdges());
        LinkedList<VertexInfo<Integer>> dfs = graph.dfs(vertex1);
        for(int i = 0; i < dfs.size(); i++ ){
            System.out.println(dfs.get(i).getData());
        }
        LinkedList<VertexInfo<Integer>> bfs = graph.bfs(vertex1);
        System.out.println(bfs);

        graph.removeVertex(vertex5);
        graph.removeEdge(vertex1,vertex2);

    }
}
