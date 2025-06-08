package advancedTypes.graphs.representation;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A weighted graph represented using an adjacency list.
 * Supports directed and bidirectional edge insertion.
 */
public class MyGraph {
    private final Map<Integer, List<WeightedEdge>> adjList;

    /**
     * Constructs a graph with the specified number of vertices.
     * Vertices are indexed from 0 to (vertices - 1).
     *
     * @param vertices the number of vertices in the graph
     */
    public MyGraph(int vertices) {
        this.adjList = new LinkedHashMap<>();
        for (int i = 0; i < vertices; i++) {
            this.adjList.put(i, new ArrayList<>());
        }
    }

    /**
     * Adds an edge to the graph.
     *
     * @param from         the source vertex
     * @param to           the destination vertex
     * @param weight       the weight of the edge
     * @param bidirectional if true, also adds the reverse edge (undirected)
     * @throws IllegalArgumentException if either vertex is invalid
     */
    public void addEdge(int from, int to, int weight, boolean bidirectional) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            throw new IllegalArgumentException("Invalid vertex.");
        }
        this.adjList.get(from).add(new WeightedEdge(to, weight));
        if (bidirectional) {
            this.adjList.get(to).add(new WeightedEdge(from, weight));
        }
    }

    /**
     * Removes the edge from one vertex to another.
     *
     * @param from the source vertex
     * @param to   the destination vertex to remove
     */
    public void removeEdge(int from, int to) {
        List<WeightedEdge> neighbors = this.adjList.get(from);
        List<WeightedEdge> adjusted = neighbors.stream()
                .filter(neighbor -> neighbor.to != to)
                .collect(Collectors.toList());
        this.adjList.put(from, adjusted);
    }

    /**
     * Checks whether an edge from one vertex to another exists.
     *
     * @param from the source vertex
     * @param to   the destination vertex
     * @return true if an edge exists, false otherwise
     */
    public boolean hasEdge(int from, int to) {
        List<WeightedEdge> neighbors = this.adjList.get(from);
        return neighbors.stream().anyMatch(neighbor -> neighbor.to == to);
    }

    /**
     * Prints the graph's adjacency list.
     * Each line represents one vertex and its neighbors with edge weights.
     */
    public void print() {
        for (int vertex : this.adjList.keySet()) {
            System.out.print(vertex + " -> ");
            for (WeightedEdge neighbor : this.adjList.get(vertex)) {
                System.out.print("(" + neighbor.to + ", w=" + neighbor.weight + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Returns the list of neighbors (edges) for a given vertex.
     *
     * @param vertex the vertex whose neighbors to return
     * @return a list of WeightedEdge objects
     */
    public List<WeightedEdge> getNeighbors(int vertex) {
        return this.adjList.get(vertex);
    }

    /**
     * Returns the total number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int getVerticeCount() {
        return this.adjList.size();
    }
}
