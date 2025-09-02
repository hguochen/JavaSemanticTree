package advancedTypes.trees;

import java.util.Arrays;

/**
 * Fenwick Tree (Binary Indexed Tree) implementation.
 * Supports point updates and prefix sum queries in O(log n) time.
 *
 * references: https://www.youtube.com/watch?v=BHPez138yX8&ab_channel=WilliamFiset
 */
class FenwickTree {
    private int[] tree;
    private int size;

    /**
     * Constructs the Fenwick Tree from a given input array (0-based index).
     * Internally uses 1-based indexing.
     *
     * @param arr the input array
     */
    public FenwickTree(int[] arr) {
        this.size = arr.length;
        this.tree = new int[this.size + 1]; // 1 based index

        // construct tree using point updates
        for (int i = 0; i < this.size; i++) {
            this.update(i + 1, arr[i]);
        }
        System.out.println("Fenwick Tree internal state: " + Arrays.toString(this.tree));
    }

    /**
     * Returns the prefix sum from index 1 to i (inclusive).
     *
     * Example:
     * Let’s say n = 8, and we call query(6):
     * i = 6 → sum += tree[6]
     * i = 4 → sum += tree[4]
     * i = 0 → stop
     * So query(6) = tree[6] + tree[4] = sum(arr[1..6])
     *
     * Time Complexity: O(log n)
     *
     * @param i index to query (1-based)
     * @return sum of elements from 1 to i
     */
    public int query(int i) {
       int sum = 0;
       while (i > 0) {
           sum += this.tree[i];
           // this is a bitwise operation that lets you move to the parent node that covers the next left block of
           // the prefix sum
           // i & -i isolates the last set bit of i (e.g., 4 → 100 → 4)
           // Subtracting it from i brings you to the parent node of the current block
           i -= (i & -i); // move tot parent node
       }
       return sum;
    }

    /**
     * Adds delta to index i in the original array and updates the tree.
     *
     * Example:
     * Let’s say n = 8, and we call update(3, 5).
     * i = 3 → tree[3] += 5
     * i = 4 → tree[4] += 5
     * i = 8 → tree[8] += 5
     *
     * Time Complexity: O(log n)
     *
     * @param index the position to update (1-based)
     * @param delta the value to add
     */
    public void update(int index, int delta) {
        while (index <= this.size) {
            // adds delta to the current index i in the tree
            // tree[index] stores the prefix sum of a range that includes index i, so it must reflect the change
            this.tree[index] += delta;
            // this moves index to the next index that depends on index
            //(i & -i) givse the lowest set bit in i -> this bit tells us the size of the block covered by tree i
            index += (index & -index); // move to next responsible node
        }
    }

    /**
     * Returns the sum of elements from index left to right (inclusive)
     *
     * Uses prefix sums: sum(left...right) = query(right) - query(left - 1)
     *
     * @param left  left index of the range (1-based)
     * @param right right index of the range (1-based)
     * @return the sum from arr[left-1] to arr[right-1]
     */
    public int queryRange(int left, int right) {
        return this.query(right) - this.query(left - 1);
    }
}

public class FenwickTreeDemo {
    public static void main(String[] args) {
        int[] A = {3, 4, -2, 7, 3, 11, 5, -8, -9, 2, 4, -8};
        FenwickTree tree = new FenwickTree(A);
    }
}
