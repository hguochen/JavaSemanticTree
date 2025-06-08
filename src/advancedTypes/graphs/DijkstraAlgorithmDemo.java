package advancedTypes.graphs;

import advancedTypes.graphs.representation.MyGraph;
import advancedTypes.graphs.representation.WeightedEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


public class DijkstraAlgorithmDemo {
    /**
     * Dijkstra's algorithm aims to find the shortest path(minimum total weight) from a source node to all other nodes in
     * a weighted graph with non-negative edge weights.
     *
     * Time Complexity: O((|V| + |E|) * log |V|) using binary heap
     * Space Complexity: O(|V|) for distance[], visited[], and the priority queue
     *
     * Key concepts:
     * - works on directed and undirected graphs
     * - requires non-negative weights
     * - often used in maps, network routing, and game AI
     *
     * Algorithm
     * 1. initialize distance array from start to each node, every node is an unknown distance here from start node
     * 2. use a priority queue to always pick the next closest unvisited node
     * 3. for current node:
     *      - visit all its neighbors
     *      - if going through current node provides a shorter path, update the neighbor's distance
     * 4. repeat until all nodes are processed or the queue is empty
     */
    public static void dijkstras(MyGraph graph, int start) {
        int vertexCount = graph.getVerticeCount();
        boolean[] visited = new boolean[vertexCount];
        int[] distance = new int[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<WeightedEdge> pQueue = new PriorityQueue<>();
        pQueue.offer(new WeightedEdge(start, distance[start]));

        System.out.println("dijkstra's algorithm visiting sequence from " + start + ": ");
        while (!pQueue.isEmpty()) {
            WeightedEdge curr = pQueue.poll();
            if (visited[curr.to]) continue;
            visited[curr.to] = true;

            System.out.println("Vertex: " + curr.to + ", " + "Distance: " + curr.weight);
            List<WeightedEdge> neighbors = graph.getNeighbors(curr.to);
            for (WeightedEdge neighbor : neighbors) {
                int neighborId = neighbor.to;
                int currentWeight = neighbor.weight;

                if (!visited[neighborId] && distance[curr.to] + currentWeight < distance[neighborId]) {
                    int newDistance = distance[curr.to] + currentWeight;
                    distance[neighborId] = newDistance;
                    pQueue.offer(new WeightedEdge(neighborId, newDistance));
                }
            }
        }
    }

    public static void main(String[] args) {
        MyGraph graph = new MyGraph(6);
        graph.addEdge(0, 1, 2, false);
        graph.addEdge(0, 2, 4, false);
        graph.addEdge(1, 3, 1, false);
        graph.addEdge(1, 2, 3, false);
        graph.addEdge(2, 4, 5, false);
        graph.addEdge(4, 3, 1, false);
        graph.addEdge(4, 5, 3, false);

        dijkstras(graph, 0);
    }













































}
