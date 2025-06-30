package advancedTypes.graphs;

import advancedTypes.graphs.representation.MyGraph;
import advancedTypes.graphs.representation.UnionFind;
import advancedTypes.graphs.representation.WeightedEdge;

import java.util.*;

class Edge implements Comparable<Edge>{
    public int from;
    public int to;
    public int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    /**
     * Returns:
     * - <0 this object is less than the other object
     * - == 0 this object is equal to the other object
     * - > 0 this object is larger than the other object
     */
    public int compareTo(Edge other) {
        // min-heap: smaller distances has higher priority
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "Edge{" + "from=" + from + ", to=" + to + ", weight=" + weight + '}';
    }
}

/**
 * Kruskal's algorithm is a greedy algorithm used to find the minimum spanning tree of a connected, undirect,, weighted graph.
 *
 * Goal: Connect all vertices with the minimum edge total edge weight without any cycles.
 *
 * Core idea:
 * - always pick the smallest weight edge that doesn't create a cycle.
 * - uses disjoint set union(Union-Find) data structure to detect cycles efficiently
 *
 * Steps:
 * 1. Sort all edges in non-decreasing order of weight.
 * 2. Initialize union find for all vertices
 * 3. Iterate through sorted edges:
 *      a. if two edges connect two different sets, ie. no cycle, add to MST
 *      b. Union the two sets
 */
public class KruskalAlgorithmDemo {
    /**
     * this algorithm assumes that 2 vertices are connected by max 1 edge only.
     * 	•	Uses a PriorityQueue to greedily select the smallest edge.
     * 	•	Avoids duplicate edges in an undirected graph with a Set<String> and min-max key.
     * 	•	Correctly applies Union-Find with path compression to detect and avoid cycles.
     * 	•	Returns a list of Edge objects representing the MST edges.
     * 	•	Tracks and prints the total weight of the MST.
     *
     * Time: O(V + ElogE)
     * - Sorting edges: O(E log E)
     * - Union-Find with path compression: O(α(V)) ≈ O(V)
     * - Total: O(E log E + V) (since E dominates V in sparse/dense graphs)
     * Space: O(E + V)
     *
     * @param graph
     * @return
     */
    public static List<Edge> kruskal(MyGraph graph) {
        // List<Edge> to store the resulting MST
        List<Edge> mst = new ArrayList<>();
        // Set to track seen undirected edges (ensure no duplicates)
        Set<String> seenEdges = new HashSet<>();
        // Priority queue to pick the minimum weight edge
        PriorityQueue<Edge> pQueue = new PriorityQueue<>();
        int verticeCount = graph.getVerticeCount();
        for (int i = 0; i < verticeCount; i++) {
            int currVertex = i;
            List<WeightedEdge> neighbors = graph.getNeighbors(currVertex);
            // for each neighbor, add its associated edge to the queue, if it hasn't already been added
            for (WeightedEdge neighbor : neighbors) {
                String edgeKey = Math.min(currVertex, neighbor.to) + "-" + Math.max(currVertex, neighbor.to);
                if (seenEdges.contains(edgeKey)) continue;
                seenEdges.add(edgeKey);
                pQueue.offer(new Edge(currVertex, neighbor.to, neighbor.weight));
            }
        }

        int totalWeight = 0;
        // use UnionFind to determine if 2 vertices are connected
        UnionFind uf = new UnionFind(verticeCount);
        // keep extracting from the priority queue
            // each time try to add new edge that does not form a cycle in Union Find DS
            // when Union Find DS has only 1 set, break
        while (!pQueue.isEmpty()) {
            Edge curr = pQueue.poll();
            int from = curr.from;
            int to = curr.to;
            if (uf.connected(from, to)) continue;
            uf.union(from, to);
            mst.add(curr);
            totalWeight += curr.weight;
            if (uf.getComponentCount() == 1) break;
        }
        System.out.println("total weight is: " + totalWeight);
        // return the parents hashmap
        return mst;
    }

    public static void main(String[] args) {
        MyGraph graph = new MyGraph(7);
        graph.addEdge(0, 1, 2, true);
        graph.addEdge(0, 2, 3, true);
        graph.addEdge(0, 3, 3, true);
        graph.addEdge(1, 2, 4, true);
        graph.addEdge(1, 4, 3, true);
        graph.addEdge(2, 4, 1, true);
        graph.addEdge(2, 3, 5, true);
        graph.addEdge(2, 5, 6, true);
        graph.addEdge(3, 5, 7, true);
        graph.addEdge(4, 5, 8, true);
        graph.addEdge(5, 6, 9, true);

        List<Edge> mst = kruskal(graph);
        System.out.println("Edges in MST:");
        for (Edge edge : mst) {
            System.out.printf("(%d - %d), weight = %d%n", edge.from, edge.to, edge.weight);
        }
    }
}
