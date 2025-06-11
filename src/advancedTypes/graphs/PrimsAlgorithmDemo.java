package advancedTypes.graphs;

import advancedTypes.graphs.representation.MyGraph;
import advancedTypes.graphs.representation.WeightedEdge;

import java.util.*;

/**
 * Prim's algoirthm is a greedy algorithm used to find the Minimum Spanning Tree(MST) of a connected, undirected, weighted graph.
 * A MST is a subset of the edges that connects all the vertices in the graph with the minimum total edge weight and no cycles.
 *
 * Problem Prim's solves
 * - V vertices(nodes)
 * - E edges with weights(costs)
 * Goal: Connect all vertices with the minimum total weight with no cycles
 *
 * Key Concepts
 * - Prim starts with any node and grows the MST by repeatedly adding the lowest-weight edge that connects a new node to the MST
 * - It ensures no cycles are formed
 * - It's similar to Dijkstra's algorithm in structure
 *
 * Step-by-Step (High-Level)
 * 	1.	Start with any arbitrary node. Add it to the MST.
 * 	2.	Use a priority queue (min-heap) to keep track of edges crossing from the MST to nodes outside it.
 * 	3.	At each step:
 * 		- Pick the smallest weight edge from the queue that connects a node in MST to a node not yet in MST.
 * 	    - Add that edge and the new node to the MST.
 * 	4.	Repeat until all nodes are included in the MST.
 *
 * 	When to use Prim's algorithm?
 * 	- the graph is dense(ie. number of edges E is close to V2)
 * 	    - adjacency matrix or min-heap + adjacency list, Prim's runs efficiently on dense graphs
 * 	- you want to find the MST from a specific starting node
 * 	    - Prim's starts from a node and expands, which may be preferable if we want local-to-global expansion behavior
 * 	- you need a connected MST guaranteed:
 * 	    - Prim assumes and requires that the graph is connected. It grows the MST incrementally and doesn’t need to consider all edges at once.
 *
 * 	When NOT to use Prim's algorithm?
 * 	- the graph is sparse(ie. E is much less than V^2)
 * 	    - Kruskal’s algorithm tends to outperform Prim’s in sparse graphs since it focuses only on sorting edges once and uses Union-Find to avoid cycles.
 * 	- you don't care about starting node
 * 	- you have a disconnected graph and still want to get a MST
 * 	    - Prim's won't work since it assumes the graph is connected
 * 	- you are constrained on memory and can't afford to maintain a large priority queue
 * 	    - Prim’s priority queue can grow large if there are many neighbors. Kruskal’s only needs to sort edges once and use a Union-Find structure.
 */
public class PrimsAlgorithmDemo {
    /**
     * Time: O((V + E) log V)
     * - where V is the number of vertices, E the number of edges
     * - using a min heap priority queue and adjacency list
     * Space: O(V)
     * Breakdown:
     * 	•	visited[], distance[], parents → O(V)
     * 	•	Priority queue stores at most O(V) nodes at once in the worst case → O(V)
     * 	•	Graph itself (adjacency list) is not counted here, assuming it’s given. But if included, it’s O(V + E)
     *
     * @param graph
     * @param start
     * @return
     */
    public static Map<Integer, Integer> prims(MyGraph graph, int start) {
        int verticeCount = graph.getVerticeCount();
        // initialize a visited array to track visiting status
        boolean[] visited = new boolean[verticeCount];
        // initialize a parent hashmap to track the parent of key node
        Map<Integer, Integer> parents = new HashMap<>();
        // initialize a distance array to track least distance to this node
        int[] distance = new int[verticeCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        // initialize a priority queue of WeightedEdge objects to compare
        PriorityQueue<WeightedEdge> pQueue = new PriorityQueue<>();
        pQueue.offer(new WeightedEdge(start, 0));

        // while priority queue is not empty
            // poll the current node
            // if current node is already visited, continue
            // mark current node as visited
            // get all the neighbors of current
            // for each neighbor:
                // if neighbor is not visited AND distance to this neighbor is shorter than known distance
                    // set distance to shorter distance
                    // set this neighbor parent relationship
                    // add this neighbor weightededge to queue
        while (!pQueue.isEmpty()) {
            WeightedEdge curr = pQueue.poll();
            int vertex = curr.to;
            if (visited[vertex]) continue;
            visited[vertex] = true;

            List<WeightedEdge> neighbors = graph.getNeighbors(vertex);
            for (WeightedEdge neighbor : neighbors) {
                int neighborVertex = neighbor.to;
                int neighborWeight = neighbor.weight;
                if (!visited[neighborVertex] && neighborWeight < distance[neighborVertex]) {
                    distance[neighborVertex] = neighborWeight;
                    parents.put(neighborVertex, vertex);
                    // we are recreating the same WeightedEdge object here because:
                    // - this reflects that we are pushing only the shortest known path at the time
                    // - reinforces that distance[] was updated, and we are enqueueing a matching edge
                    // - the original neighbor object might not be safe to reuse as it depends on how graph
                    // .getNeighbors(vertex) is implemented
                    //
                    // NOTE: If you’re 100% sure your graph guarantees one unique edge between each pair and you’re
                    // only calling offer() when distance improves, you can reuse neighbor. But in real-world code or
                    // interviews, it’s safer and clearer to recreate it.
                    pQueue.offer(new WeightedEdge(neighborVertex, neighborWeight));
                }
            }
        }
        // add up the distance array elements to get the total edge weight
        int totalEdgeWeight = 0;
        for (int weight : distance) {
            totalEdgeWeight += weight;
        }
        // return the hashmap of parents
        return parents;
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

        System.out.println(prims(graph, 0));
    }
}
