package advancedTypes.graphs.representation;

/**
 * Represents a weighted directed edge in a graph.
 * Contains the target vertex and the weight of the edge.
 */
public class WeightedEdge implements Comparable<WeightedEdge> {
    /** The target vertex this edge points to. */
    public int to;
    /** The weight or cost of this edge. */
    public int weight;

    /**
     * Constructs a weighted edge to the given vertex with the specified weight.
     *
     * @param to     the destination vertex
     * @param weight the weight of the edge
     */
    public WeightedEdge(int to, int weight) {
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
    public int compareTo(WeightedEdge other) {
        // min-heap: smaller distances has higher priority
        return Integer.compare(this.weight, other.weight);
    }
}
