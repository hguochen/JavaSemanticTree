package advancedTypes.graphs;

import advancedTypes.graphs.representation.MyGraph;
import advancedTypes.graphs.representation.WeightedEdge;

import java.util.*;

class AStarNode implements Comparable<AStarNode> {
    public int id;
    public int fScore;

    public AStarNode(int id, int fScore) {
        this.id = id;
        this.fScore = fScore;
    }

    @Override
    public int compareTo(AStarNode other) {
        return Integer.compare(this.fScore, other.fScore);
    }
}
/**
 * A* algorithm is a 'best-first search' algorithm used to find the shortest path from a start node to a goal node in
 * a graph. This algorithm is often for pathfinding(like in google maps), games of characters that needs pathing and
 * routing systems.
 *
 * Core Idea
 * - combines the strengths of other concepts
 *      - shortest path concept from dijkstra's algorithm
 *      - greedy best-first search
 *          - ie. using heuristics to guide a search
 *
 * What exactly is 'best-first search'?
 * Each node is evaluated using this formula: f(n) = g(n) + h(n)
 * - f(n): total estimated cost of the cheapest solution through node n
 * - g(n): cost of start node to node n (actual cost so far)
 * - h(n): heuristic estimate from current node to the goal node
 *
 * A heuristic estimate of a distance is essentially a best estimated guess of distance. Some good distance measuring
 * heuristics are:
 * - Euclidean distance (for maps)
 * - Manhattan distance (for grid based maps)
 * - Straight line distance (for graphs with positions)
 *
 * A good heuristic has the following characteristics:
 * - admissible: never over-estimate the cost to reach the goal
 * - consistent: triangle equality holds
 *
 * Algorithm:
 * 1. Initialize:
 *      - open priority queue with start node
 *      - g(start) = 0
 *      - f(start) = h(start)
 * 2. while the priority queue is not empty
 *      - pick node with lowest f(n) from the queue
 *      - if it's the goal node, reconstruct the path and return
 *      - else:
 *          - for each neighbor:
 *              - calculate tentative_g = g(current) + cost(current, neighbor)
 *              - If tentative_g < g(neighbor):
 *                  - Update g(neighbor) and f(neighbor)
 *                  - Add neighbor to open set if not already
 * 3. if goal not reached, return failure
 *
 * When to use A* ?
 * - when path cost matters
 * - when you want fast and optimal results
 * - when you have a good heuristic
 *
 * When not to use?
 * - in graphs without good heuristics
 * - when high memory usage is a concern
 * - when optimality is not needed
 *
 * References:
 * - https://www.youtube.com/watch?v=-L-WgKMFuhE&ab_channel=SebastianLague
 * - https://www.youtube.com/watch?v=nhiFx28e7JY&ab_channel=SebastianLague
 *
 * Time: O(Vlogv + E)
 * Space: O(V)
 *
 * 	•	V log V comes from operations on the priority queue (openSet), which stores at most V nodes and requires log V time per insertion or update.
 * 	•	E is for traversing each edge exactly once in the worst case (similar to Dijkstra’s algorithm).
 *
 * 		gScore, cameFrom, and the priority queue (openSet) all store up to V entries.
 */
public class AStarAlgorithmDemo {
    public static List<Integer> aStar(MyGraph graph, int start, int goal, Map<Integer, int[]> coordinates) {
        PriorityQueue<AStarNode> pQueue = new PriorityQueue<>();
        pQueue.offer(new AStarNode(start, heuristic(start, goal, coordinates)));

        // cost of getting from start node to index node
        // every other node distance is unknown, therefore infinity value, start cost to itself is 0
        int[] gScore = new int[graph.getVerticeCount()];
        Arrays.fill(gScore, Integer.MAX_VALUE);
        gScore[start] = 0;

        // keep tracks how the (key) node is reached from the (value) parent
        // key(node): value(parent where node came from)
        Map<Integer, Integer> cameFrom = new HashMap<>();

        while (!pQueue.isEmpty()) {
            AStarNode curr = pQueue.poll();
            // we have reached the goal node, reconstruct the path to see how to get to goal node
            if (curr.id == goal) {
                return reconstructPath(cameFrom, curr.id);
            }

            for (WeightedEdge neighbor : graph.getNeighbors(curr.id)) {
                // tentativeScore: the distance so far from starting node AND this distance to this neighbor
                int tentativeScore = gScore[curr.id] + neighbor.weight;
                // when distance to this neighbor is shorter than any other distance to this neighbor found so far
                // - we update the parent table to track that current node has a shorter distance to this neighbor
                // - we update the shorter distance found so far to this neighbor
                // - we calculate the h(n) score and the tentativeScore, along with the node to be added into
                // priorityqueue for future visits
                if (tentativeScore < gScore[neighbor.to]) {
                    // set the parent where this neighbor node came from
                    cameFrom.put(neighbor.to, curr.id);
                    gScore[neighbor.to] = tentativeScore;
                    int fScore = tentativeScore + heuristic(neighbor.to, goal, coordinates);
                    pQueue.offer(new AStarNode(neighbor.to, fScore));
                }
            }
        }
        // no path is found.
        return Collections.emptyList();
    }

    public static List<Integer> reconstructPath(Map<Integer, Integer> cameFrom, int current) {
        List<Integer> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(current);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Heuristic function
     * Given a start node and the goal node, calculate the heuristic distance from node to goal node.
     *
     * @param start
     * @param goal
     * @param coords
     * @return
     */
    public static int heuristic(int start, int goal, Map<Integer, int[]> coords) {
        int[] a = coords.get(start);
        int[] b = coords.get(goal);
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return (int)Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        // we define each node as having (x, y) coordinates to calculate f(n) using euclidean distance
        Map<Integer, int[]> coordinates = new HashMap<>();
        coordinates.put(0, new int[]{0, 0});
        coordinates.put(1, new int[]{2, 2});
        coordinates.put(2, new int[]{2, 5});
        coordinates.put(3, new int[]{5, 2});
        coordinates.put(4, new int[]{6, 5});

        // create the graph and edges
        MyGraph graph = new MyGraph(5);
        graph.addEdge(0, 1, 3, true);
        graph.addEdge(0, 2, 8, true);
        graph.addEdge(1, 3, 2, true);
        graph.addEdge(2, 4, 4, true);
        graph.addEdge(3, 4, 1, true);

        List<Integer> path = aStar(graph, 0, 4, coordinates);
        System.out.println(path);
    }
}
