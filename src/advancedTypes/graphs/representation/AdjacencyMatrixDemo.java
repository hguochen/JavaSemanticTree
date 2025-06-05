package advancedTypes.graphs.representation;

/**
 * An adjacency matrix is a 2D array(or matrix) used to represent a graph.
 *
 * if the graph has n vertices, the matrix is an N x N grid where:
 * - matrix[i][j] = 1(or weight w) means there's an edge from vertix i to j.
 * - matrix[i][j] = 0 (or null) means there's no edge.
 *
 * Space: O(V^2)
 * - where V is the number of vertices
 *
 * Pros:
 * - simple and fast edge lookup
 * - easy to implement
 * - great for dense graphs
 *
 * Cons:
 * - wastes space for sparse graphs
 * - iterating over neighbors is slower than adjacency list
 */
class AdjacencyMatrix {
    private int[][] graph;

    public AdjacencyMatrix(int vertices) {
        this.graph = new int[vertices][vertices];
        for (int i = 0; i < this.graph.length; i++) {
            for (int j = 0; j < this.graph[i].length; j++) {
                this.graph[i][j] = 0;
            }
        }
    }

    /**
     * Add an edge between 2 vertices. The edge is unweighted and undirected, which means adding edge from A to B is
     * equivalent to adding edge from B to A.
     *
     * Time: O(1)
     *
     * @param vertice1
     * @param vertice2
     */
    public void addEdge(int vertice1, int vertice2) {
        this.graph[vertice1][vertice2] = 1;
        this.graph[vertice2][vertice1] = 1;
    }

    /**
     * Remove an edge between 2 vertices.
     *
     * Time: O(1)
     * @param vertice1
     * @param vertice2
     */
    public void removeEdge(int vertice1, int vertice2) {
        this.graph[vertice1][vertice2] = 0;
        this.graph[vertice2][vertice1] = 0;
    }

    /**
     * Checks if there's an edge between vertice1 and vertice2.
     * @param vertice1
     * @param vertice2
     * @return
     */
    public boolean hasEdge(int vertice1, int vertice2) {
        if (this.graph[vertice1][vertice2] == 0) return false;
        return true;
    }

    public void print() {
        for (int i = 0; i < this.graph.length; i++) {
            for (int j = 0; j < this.graph[i].length; j++) {
                System.out.print(this.graph[i][j] + " ");
            }
            System.out.println();
        }
    }

}

public class AdjacencyMatrixDemo {
    public static void main(String[] args) {
        // 0: A, 1: B, 2: C
        // A — B
        // |
        // C
        AdjacencyMatrix graph = new AdjacencyMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        //0 1 1 0 0
        //1 0 0 0 0
        //1 0 0 0 0
        //0 0 0 0 0
        //0 0 0 0 0
        graph.print();

    }
}
