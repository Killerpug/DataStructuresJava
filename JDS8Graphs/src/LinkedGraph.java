import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LinkedGraph <T> implements LinkeGraph<T> {
    private HashMap<Integer, VertexInfo<T>> vtxMap;
    private int numberOfEdges = 0;

    public LinkedGraph(int size) {
        vtxMap = new HashMap<>(size);
    }

    @Override
    public boolean addEdge(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd, int weight) {
        int locSrc = vtxSrc.getVertexLocation();
        int locEnd = vtxEnd.getVertexLocation();
        LinkedList<Edge> edgeList = vtxSrc.getEdgeList();
        if( vtxMap.containsKey(locSrc) && vtxMap.containsKey(locEnd)) {   //Map of vertices has the Src and End vertices
            for(int i = 0; i < edgeList.size(); i++){
                Edge current = edgeList.get(i);
                if (current.isSameEdge( vtxEnd.getVertexLocation()) ) {    //Edge between vtxSrc and vtxEnd Already Exists
                    return false;
                }
            }
            numberOfEdges += 1;
            Edge addEdge = new Edge(locEnd, weight);
            edgeList.add(addEdge);
            vtxSrc.setEdgeList(edgeList);           //add edge to the vertex edge-list
            return true;
        }
        throw new IllegalArgumentException();       //the vertices arent in the graph
    }

    @Override
    public boolean addVertex(VertexInfo<T> vtx) {
        vtxMap.put(vtx.getVertexLocation(), vtx);
        return true;
    }

    @Override
    public void clear() { vtxMap.clear(); }

    @Override
    public boolean containsEdge(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd) {
        int locSrc = vtxSrc.getVertexLocation();
        int locEnd = vtxEnd.getVertexLocation();
        LinkedList<Edge> edgeList = vtxSrc.getEdgeList();
        if( vtxMap.containsKey(locSrc) && vtxMap.containsKey(locEnd)) {
            for(int i = 0; i < edgeList.size(); i++) {
                Edge current = edgeList.get(i);
                if (current.isSameEdge(vtxEnd.getVertexLocation())){    //Edge between vtxSrc and vtxEnd EXISTS
                    return true;
                }
            }
            return false;   //edge not exist
        }
        throw new IllegalArgumentException();       //the vertices arent in the graph
    }

    @Override
    public boolean containsVertex(VertexInfo<T> vtx) {
        int locSrc = vtx.getVertexLocation();
        return vtxMap.containsKey(locSrc);
    }

    @Override
    public ArrayList<VertexInfo<T>> getNeighbors(VertexInfo<T> vtx) {
        int locSrc = vtx.getVertexLocation();
        if (vtxMap.containsKey(locSrc)) {
            ArrayList<VertexInfo<T>> neighbors = new ArrayList<>();
            for (int i = 0; i < vtx.getEdgeList().size(); i++){
                int dest = vtx.getEdgeList().get(i).dest;       //get dest nodes visible from the current vertex
                neighbors.add(vtxMap.get(dest));
            }
            return neighbors;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int getWeight(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd) {
        int locSrc = vtxSrc.getVertexLocation();
        int locEnd = vtxEnd.getVertexLocation();
        LinkedList<Edge> edgeList = vtxSrc.getEdgeList();
        if( vtxMap.containsKey(locSrc) && vtxMap.containsKey(locEnd)) {   //Map of vertices has the Src and End vertices
            for(int i = 0; i < edgeList.size(); i++){
                Edge current = edgeList.get(i);
                if (current.isSameEdge(vtxEnd.getVertexLocation())){    //Edge between vtxSrc and vtxEnd
                    return current.weight;
                }
            }
            return -1;  //edge does not exist
        }
        throw new IllegalArgumentException();       //the vertices arent in the graph
    }

    @Override
    public boolean isEmpty() {
        return vtxMap == null;
    }

    @Override
    public int numberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public int numberOfVertices() {
        return vtxMap.size();
    }

    @Override
    public boolean removeEdge(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd) {
        int locSrc = vtxSrc.getVertexLocation();
        int locEnd = vtxEnd.getVertexLocation();
        LinkedList<Edge> edgeList = vtxSrc.getEdgeList();
        if( vtxMap.containsKey(locSrc) && vtxMap.containsKey(locEnd)) {
            for(int i = 0; i < edgeList.size(); i++) {
                Edge current = edgeList.get(i);
                if (current.isSameEdge(vtxEnd.getVertexLocation())){    //Edge between vtxSrc and vtxEnd EXISTS
                    edgeList.remove(current);
                    numberOfEdges =- 1;
                }
            }
            return false;   //edge not exist
        }
        throw new IllegalArgumentException();       //the vertices arent in the graph
    }

    @Override
    public boolean removeVertex(VertexInfo<T> vtx) {
        if (!containsVertex(vtx))        //Vertex doesnt exist
            throw new IllegalArgumentException( "removeVertex: vertex is not in the graph");
        vtxMap.forEach( (key, vertex) -> {
            LinkedList<Edge> edgeList = vertex.getEdgeList();
            for(int i = 0; i < edgeList.size(); i++) {
                Edge current = edgeList.get(i);
                if (current.isSameEdge(vtx.getVertexLocation())) {    //remove coincidences of intended remotion vertex
                    edgeList.remove(current);
                    numberOfEdges--;
                }
            }
        });
        vtxMap.remove(vtx.getVertexLocation());
        return true;
    }

    @Override
    public int setWeight(VertexInfo<T> vtxSrc, VertexInfo<T> vtxEnd, int weight) {
        int locSrc = vtxSrc.getVertexLocation();
        int locEnd = vtxEnd.getVertexLocation();
        LinkedList<Edge> edgeList = vtxSrc.getEdgeList();
        if( vtxMap.containsKey(locSrc) && vtxMap.containsKey(locEnd)) {   //Map of vertices has the Src and End vertices
            for(int i = 0; i < edgeList.size(); i++){
                Edge current = edgeList.get(i);
                if (current.isSameEdge(vtxEnd.getVertexLocation())){    //Edge between vtxSrc and vtxEnd
                    int previousWeight = current.weight;
                    current.weight = weight;
                    return previousWeight;
                }
            }
            return -1;  //edge does not exist
        }
        throw new IllegalArgumentException();       //the vertices arent in the graph
    }
    @Override
    public Set<T> vertexSet() {
        return null;
    }

    @Override
    public LinkedList<VertexInfo <T>> bfs(VertexInfo<T> startVtx) {
        if (!containsVertex(startVtx))        //start node doesnt exist
            throw new IllegalArgumentException( "BFS: starting vertex is not in the graph");
        resetToWhite();
        LinkedList<VertexInfo <T>> visitedList = new LinkedList<>();                             //black vertices
        Queue<VertexInfo <T>> waitQueue = new LinkedList<>();        //gray vertices
        ArrayList<VertexInfo <T>> neighbors;     //neighbors
        startVtx.setColor("gray");
        waitQueue.add(startVtx);                                 //add starting node

        while (!waitQueue.isEmpty()) {                          //there are gray nodes waiting for visitation
            VertexInfo<T> nextVtxQue = waitQueue.poll();       //work with next gray vertex on queue
            visitedList.add(nextVtxQue);
            nextVtxQue.setColor("black");
            neighbors = getNeighbors(nextVtxQue);         //get neighbors
            neighbors.forEach( nei -> {
                if (nei.getColor().equals("white")){        //add white nodes to waiting queue, color it gray
                    waitQueue.add(nei);
                    nei.setColor("gray");
                }
            });
        }
        return visitedList;
    }

    @Override
    public LinkedList<VertexInfo <T>> dfs( VertexInfo<T> startVtx) {
        if (!containsVertex(startVtx))        //start vertex doesnt exist
            throw new IllegalArgumentException( "DFS: starting vertex is not in the graph");
        resetToWhite();
        LinkedList<VertexInfo <T>> visitedList = new LinkedList<>();                             //black vertices
        Stack<VertexInfo <T>> waitStack = new Stack<>();        //gray vertices
        startVtx.setColor("gray");
        waitStack.push(startVtx);                               //add starting node

        dfsVisit(visitedList, startVtx, waitStack);     //call dfs recursively

        return visitedList;
    }
    private void dfsVisit( LinkedList<VertexInfo <T>> visitedList,
                          VertexInfo <T> currentVtx, Stack<VertexInfo <T>> waitStack) {
        ArrayList<VertexInfo <T>> neighbors = getNeighbors(currentVtx);
        for (int i = 0; i < neighbors.size(); i++){
            VertexInfo<T> currVtx = neighbors.get(i);
            if (currVtx.getColor().equals("white")){        //add white nodes to waiting stack, color it gray
                waitStack.push(currVtx);
                currVtx.setColor("gray");
                dfsVisit(visitedList, currVtx, waitStack);    //call neighbors recursively
            }
        }
        VertexInfo <T> topOnStack = waitStack.pop();         //get last element and push
        visitedList.add(topOnStack);                         //add it to visited
        topOnStack.setColor("black");
    }
    private void resetToWhite(){
        vtxMap.forEach( (key, vtx) -> vtx.setColor("white"));
    }

}
