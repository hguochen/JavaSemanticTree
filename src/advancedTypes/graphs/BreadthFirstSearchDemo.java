package advancedTypes.graphs;

import advancedTypes.graphs.representation.AdjacencyList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearchDemo {
    /**
     * Breadth first search iterative
     *
     * Time: O(V + E)
     * Space: O(V)
     *
     * - where V is the number of vertices in graph
     * - where E is the number of edges in graph
     *
     * 	•	V: You visit each vertex once when dequeuing from the queue.
     * 	•	E: You iterate over each edge exactly once when traversing neighbors of each vertex.
     *
     * 	•	Queue: At most holds O(V) nodes in worst case (e.g. in a complete level of a tree or graph).
     * 	•	Visited[]: Boolean array of size V → O(V).
     * 	•	Adjacency list data structure is part of the input, not extra space used by BFS itself.
     * @param graph
     */
    public static void breadthFirstSearch(AdjacencyList graph, int start) {
        // init a queue
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(start);
        // init a visitation array to track which vertices are visited
        // no init to false needed, all elements are automatically initialized to false by default.
        boolean[] visited = new boolean[graph.getVerticeCount()];

        System.out.println("Visiting vertices: ");
        while(!queue.isEmpty()) {
            // remove first in queue to 'visit' the current vertice
            int vertice = queue.removeFirst();
            visited[vertice] = true;
            System.out.print(vertice + " ");
            // fetch all of vertice's neighbors
            List<Integer> neighbors = graph.getNeighbors(vertice);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true; // mark as visited BEFORE enqueue to avoid a vertice being enqueue
                    // multiple times by different neighbors
                    queue.addLast(neighbor);
                }
            }
        }
    }

    /**
     * Breadth first search recursive
     *
     * Time: O(V + E)
     * Space: O(V)
     * @param graph
     */
    public static void breadthFirstSearchRecursive(AdjacencyList graph, int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.getVerticeCount()];
        queue.addLast(start);
        visited[start] = true;
        System.out.println("Visiting vertices: ");
        recursiveBFS(graph, queue, visited);
    }

    public static void recursiveBFS(AdjacencyList graph, LinkedList<Integer> queue, boolean[] visited) {
        if (queue.isEmpty()) return;
        int current = queue.removeFirst();
        System.out.print(current + " ");

        List<Integer> neighbors = graph.getNeighbors(current);
        for (int neighbor: neighbors) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.addLast(neighbor);
            }
        }
        recursiveBFS(graph, queue, visited);
    }

    public static void main(String[] args) {
        AdjacencyList graph = new AdjacencyList(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        breadthFirstSearch(graph, 0);
        breadthFirstSearchRecursive(graph, 0);
    }
}
