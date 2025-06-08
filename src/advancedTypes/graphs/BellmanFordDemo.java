package advancedTypes.graphs;

import advancedTypes.graphs.representation.MyGraph;
import advancedTypes.graphs.representation.WeightedEdge;

import java.util.Arrays;
import java.util.List;

/**
 * Bellman Ford algorithm is a method used in graph theory for finding the shortest path between a single source vertex
 * and all other vertices in a weighted graph.
 *
 * This algorithm can handle graphs with negative weight edges, unlike dijkstra's algorithm.
 *
 * It follows a bottom-up approach, filling up the distance table gradually while relaxing edges.
 *
 * Why use Bellman-Ford?
 * - Handles negative weights, Dijkstra's does not
 * - Detects negative cycles, Dijkstra's does not
 * - Has complexity O(V X E)
 * - Works on both directed and undirected graphs
 *
 * When to use it?
 * - graph has edges with negative weights
 * - need to detect negative cycles
 * - can tolerate increased time complexity(O(V * E))
 */
public class BellmanFordDemo {

    /**
     * Time: O(V * E)
     * Space: O(V)
     * @param graph
     * @param start
     */
    public static void bellmanFord(MyGraph graph, int start) {
        int vertexCount = graph.getVerticeCount();
        int[] distance = new int[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        // outer loop here runs at most V-1 times. because we know that for V vertices, there are at most V-1 edges.
        for (int j = 0; j < vertexCount - 1; j++) {
            boolean updated = false;
            // here we visit every node and compare this node's registered distance + neighbor weight. if this
            // distance is lesser than the registered neighbor's distance so far, we have found a shorter distance
            // from this node. so we update it to the shorter distance
            for (int i = 0; i < vertexCount; i++) {
                if (distance[i] == Integer.MAX_VALUE) continue;
                List<WeightedEdge> neighbors = graph.getNeighbors(i);
                for (WeightedEdge neighbor : neighbors) {
                    int targetVertex = neighbor.to;
                    int currentWeight = neighbor.weight;
                    if (distance[i] + currentWeight < distance[targetVertex]) {
                        updated = true;
                        distance[targetVertex] = distance[i] + currentWeight;
                    }
                }
            }
            // as soon as there's no further updates, we break the loop early.
            if (!updated) break;
        }

        // detect negative cycles
        for (int i = 0; i < vertexCount; i++) {
            if (distance[i] == Integer.MAX_VALUE) continue;
            for (WeightedEdge neighbor : graph.getNeighbors(i)) {
                int target = neighbor.to;
                int weight = neighbor.weight;
                if (distance[i] + weight < distance[target]) {
                    System.out.println("Graph contains a negative weight cycle");
                    return;
                }
            }
        }
        System.out.println("Shortest distances from vertex " + start + ":");
        for (int i = 0; i < vertexCount; i++) {
            System.out.println("Vertex " + i + ": " + (distance[i] == Integer.MAX_VALUE ? "∞" : distance[i]));
        }
    }

    public static void main(String[] args) {
        MyGraph graph = new MyGraph(6);
        // graph source: https://www.youtube.com/watch?v=obWXjtg0L64&ab_channel=MichaelSambol
        // S: 0
        // A: 1
        // B: 2
        // C: 3
        // D: 4
        // E: 5
        graph.addEdge(0, 5, 8, false);
        graph.addEdge(0, 1, 10, false);
        graph.addEdge(1, 3, 2, false);
        graph.addEdge(2, 1, 1, false);
        graph.addEdge(3, 2, -2, false);
        graph.addEdge(4, 1, -4, false);
        graph.addEdge(4, 3, -1, false);
        graph.addEdge(5, 4, 1, false);

        bellmanFord(graph, 0); // [0, 5, 5, 7, 9, 8]
    }
}
