package advancedTypes.graphs.representation;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * UnionFind is also called the Disjoint Set Union(DSU) data structure.
 *
 * What is union find?
 * Union find is a data structure that keeps track of a set of elements partitioned into disjoint subsets.
 *
 * Common uses
 * - Detect cycles in undirected graphs(eg.Kruskal's algorithm)
 * - Connected components in a graph
 * - Network connectivity
 * - Image processing, clustering
 *
 * Structure
 * - each element points to a parent, forming a tree.
 * - each set is represented by the root of it's tree.
 */
public class UnionFind {
    // parents[i] points to the parent of i, if parents[i] = i then i is the root node
    private int[] parents;
    // track the sizes of each of the components
    private int[] size;
    // track the number of components in the union find
    private int numComponents;


    public UnionFind(int nodes) {

        this.parents = new int[nodes];
        this.size = new int[nodes];
        // initialize every node's parent to itself
        // initialize size of each node to 1, since each is a root of itself.
        for (int i = 0; i < nodes; i++) {
            this.parents[i] = i;
            this.size[i] = 1;
        }
        // initially, each node is its own components
        this.numComponents = nodes;
    }

    /**
     * Returns the root/representative of the set that contains 'node' param.
     * Use path compression to optimize
     *
     * Amortized Time: O(α(n))
     * - where n is the number of components
     * - where α(n) is the inverse Ackermann function,
     * which grows extremely slowly (almost constant in practice) due to path compression
     *
     * @param node
     * @return
     */
    public int find(int node) {
        int root = node;
        // keep searching for the root parent up the tree.
        while (this.parents[root] != root) {
            root = this.parents[root];
        }
        // path compression to set all intermediary nodes to the root
        while (node != root) {
            int parent = this.parents[node];
            this.parents[node] = root;
            node = parent;
        }
        return root;
    }

    public int findRecursive(int node) {
        if (this.parents[node] != node) {
            this.parents[node] = this.findRecursive(this.parents[node]); // path compression
        }
        return this.parents[node];
    }

    /**
     * Unites the sets that contain node X & Y.
     * Returns true if the union actually merged the 2 different sets.
     * Returns false if 2 nodes were already in the same set.
     * Use union by size to keep the tree flat
     *
     * Time: O(α(n))
     *
     * @param nodeX
     * @param nodeY
     * @return
     */
    public boolean union(int nodeX, int nodeY) {
        // TODO
        int parentX = this.find(nodeX);
        int parentY = this.find(nodeY);
        if (parentX == parentY) return false;
        // join the smaller size component as a subset to the bigger component
        if (this.size[parentX] < this.size[parentY]) {
            this.parents[parentX] = parentY;
            this.size[parentY] += this.size[parentX];
        } else {
            this.parents[parentY] = parentX;
            this.size[parentX] += this.size[parentY];
        }
        this.numComponents -= 1;
        return true;
    }

    /**
     * Returns true if X & Y are in the same set.
     * Amortized Time: O(α(n))
     * - where n is the number of components
     * - where α(n) is the inverse Ackermann function,
     * which grows extremely slowly (almost constant in practice) due to path compression
     * @param nodeX
     * @param nodeY
     * @return
     */
    public boolean connected(int nodeX, int nodeY) {
        return this.find(nodeX) == this.find(nodeY);
    }

    public int getComponentCount() {
        return this.numComponents;
    }
}
