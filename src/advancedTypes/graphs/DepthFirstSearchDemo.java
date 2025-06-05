package advancedTypes.graphs;

import advancedTypes.graphs.representation.AdjacencyList;

import java.util.List;
import java.util.Stack;

public class DepthFirstSearchDemo {
    /**
     * DFS iterative implementation
     *
     * Time: O(V + E)
     * Space: O(V)
     *- where V is the number of vertices since each vertice is visited exactly once
     *- where E is the number of edges since each edge is traversed exactly once
     * @param graph
     * @param start
     */
    public static void depthFirstSearchIterative(AdjacencyList graph, int start) {
        boolean[] visited = new boolean[graph.getVerticeCount()];

        System.out.println("Visiting vertices DFS: ");
        dfsIterative(graph, start, visited);
        for (int i = 0; i < graph.getVerticeCount(); i++) {
            if (!visited[i]) {
                dfsIterative(graph, i, visited);
            }
        }

    }

    public static void dfsIterative(AdjacencyList graph, int start, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (visited[curr]) continue;

            // mark as visited only when processing
            visited[curr] = true;
            System.out.print(curr + " ");

            List<Integer> neighbors = graph.getNeighbors(curr);
            for (int neighbor: neighbors) {
                if (!visited[neighbor]) {
                    // we do not mark the neighbor as visited here because
                    //    - we only want to mark it as visited when we actually process it
                    stack.push(neighbor);
                }
            }
        }
    }

    /**
     * DFS recursive implementation.
     *
     * Time: O(V + E)
     * Space: O(V)
     * - V: number of vertices, each visited once.
     * - E: number of edges, each traversed once.
     *
     * @param graph the adjacency list representation of the graph
     * @param start the starting vertex for DFS
     */
    public static void depthFirstSearchRecursive(AdjacencyList graph, int start) {
        boolean[] visited = new boolean[graph.getVerticeCount()];
        System.out.println("Visiting vertices DFS: ");
        // the for loop here handles disconnected graph
        for (int i = 0; i < graph.getVerticeCount(); i++) {
            if (!visited[i]) {
                dfsRecursive(graph, i, visited);
            }
        }
    }

    public static void dfsRecursive(AdjacencyList graph, int start, boolean[] visited) {
        if (visited[start]) return;
        // visit current vertice
        visited[start] = true;
        System.out.print(start + " ");

        // visit neighors
        List<Integer> neighbors = graph.getNeighbors(start);
        for (int neighbor : neighbors) {
            dfsRecursive(graph, neighbor, visited);
        }
    }


    public static void main(String[] args) {
        AdjacencyList graph = new  AdjacencyList(10);
        // Component 1
        //Component 1 (connected):
        //    0 ── 1 ── 2
        //    │     │   │
        //    3     4 ── 5
        //    │         │
        //    └───── 6 ─┘
        //Component 2 (disconnected):
        //    7 ── 8
        //         │
        //         9
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(3, 6);
        graph.addEdge(6, 5);

        // Component 2
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);

        depthFirstSearchIterative(graph, 0);
        depthFirstSearchIterative(graph, 7);
        System.out.println();
        depthFirstSearchRecursive(graph, 0);
    }
}
