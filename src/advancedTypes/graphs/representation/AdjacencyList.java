package advancedTypes.graphs.representation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public /**
 * An adjacency list is a collection where each vertex stores a list of its neighbours(the vertices it connects to)
 * Graph:
 * A — B
 * |
 * C
 *
 * Adjacency list:
 * A → [B, C]
 * B → [A]
 * C → [A]
 *
 * Pros:
 * - Space efficient
 * - easy to iterate over neighbors
 * - flexible to store additional info(weights, metadata)
 *
 * Cons
 * - slower edge lookup
 * - more complex to implement than adjacency matrix
 */
class AdjacencyList {
    private Map<Integer, List<Integer>> adjList;

    public AdjacencyList(int vertices) {
        adjList = new LinkedHashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    /**
     * Adds an edge between vertice1 and vertice2
     *
     * Time: O(1)
     * @param vertice1
     * @param vertice2
     */
    public void addEdge(int vertice1, int vertice2) {
        this.adjList.get(vertice1).add(vertice2);
        this.adjList.get(vertice2).add(vertice1);
    }

    /**
     * Removes an edge between vertice1, and vertice2
     *
     * Time: O(E)
     * - where E is the most number of edges a vertice has in the entire graph
     * @param vertice1
     * @param vertice2
     */
    public void removeEdge(int vertice1, int vertice2) {
        this.adjList.get(vertice1).remove(new Integer(vertice2));
        this.adjList.get(vertice2).remove(new Integer(vertice1));
    }

    /**
     * Check if vertice1 and vertice2 are connected.
     *
     * Time: O(E)
     * @param vertice1
     * @param vertice2
     * @return
     */
    public boolean hasEdge(int vertice1, int vertice2) {
        List<Integer> vertice1Edges = this.adjList.get(vertice1);
        return vertice1Edges.contains(new Integer(vertice2));
    }

    public void print() {
        for (int vertice : adjList.keySet()) {
            System.out.println(vertice + " -> " + adjList.get(vertice));
        }
    }

    /**
     * Get all the neighbors of a vertice.
     * @param vertice
     * @return
     */
    public List<Integer> getNeighbors(int vertice) {
        return this.adjList.get(vertice);
    }

    public int getVerticeCount() {
        return this.adjList.size();
    }
}