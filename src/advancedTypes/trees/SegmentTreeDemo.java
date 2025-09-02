package advancedTypes.trees;

import java.util.Arrays;
import java.util.List;

/**
 * SegmentTree supports efficient range queries (sum, min, max) over an array.
 * It builds three segment trees internally:
 * - Sum Tree: for range sum queries
 * - Min Tree: for range minimum queries
 * - Max Tree: for range maximum queries
 *
 * reference: https://www.youtube.com/watch?v=2bSS8rtFym4&ab_channel=Techdose
 */
class SegmentTree {
    private int[] sumTree;
    private int[] minTree;
    private int[] maxTree;
    private int[] arr;

    /**
     * Constructs a SegmentTree from the given array.
     * Builds the sumTree, minTree, and maxTree in one pass.
     *
     * @param arr the input array to build segment trees from
     */
    public SegmentTree(int[] arr) {
        this.arr = arr;
        int len = arr.length;
        this.sumTree = new int[4 * len];
        this.minTree = new int[4 * len];
        this.maxTree = new int[4 * len];
        Arrays.fill(this.sumTree, Integer.MIN_VALUE);
        Arrays.fill(this.minTree, Integer.MAX_VALUE);
        Arrays.fill(this.maxTree, Integer.MIN_VALUE);
        this.buildSumTree(arr, this.sumTree, 0, 0, len - 1);
        System.out.println(Arrays.toString(this.sumTree));
        this.buildMinTree(arr, this.minTree, 0, 0, len - 1);
        System.out.println(Arrays.toString(this.minTree));
        this.buildMaxTree(arr, this.maxTree, 0, 0, len - 1);
        System.out.println(Arrays.toString(this.maxTree));
    }

    /**
     * Computes the sum of elements in the range [left, right] of the original array.
     * This method acts as a wrapper that hides the internal tree structure.
     *
     * @param left  the starting index of the query range (inclusive)
     * @param right the ending index of the query range (inclusive)
     * @return the sum of elements from index left to right
     */
    public int querySum(int left, int right) {
        return this.querySumRecursive(this.sumTree, 0, 0, this.arr.length-1, left, right);
    }

    /**
     * Updates the original array and propagates the change through the sum segment tree.
     *
     * @param index the index of the element to update
     * @param value the new value to set at the index
     */
    public void updateArray(int index, int value) {
        // update the original array
        int diff = value - this.arr[index];
        this.arr[index] = value;
        // update the segment tree
        this.update(this.sumTree, 0, 0, this.arr.length - 1, index, diff);
    }

    /**
     * Recursively updates the segment tree to reflect the change at a specific index.
     *
     * Time: O(logn)
     * - recusively update the nodes in the height of the tree until the leaf node.
     *
     * @param tree         the segment tree array (e.g. sumTree)
     * @param currIndex    the current node index in the segment tree
     * @param leftSegment  the left bound of the current segment
     * @param rightSegment the right bound of the current segment
     * @param pos          the index in the original array that was updated
     * @param diff         the difference between new and old value
     */
    private void update(int[] tree, int currIndex, int leftSegment, int rightSegment, int pos, int diff) {
        // case 1: pos is outside of left and right segments
        if (pos < leftSegment || pos > rightSegment) return;
        // case 2: pos is within the range, so we must update the sum in current node
        tree[currIndex] += diff;
        // case 2.1: after updating the sum, we check if current node is a leaf node. if not leaf node, we need to
        // continue recursing down the left and right childs to update their sums
        if (leftSegment != rightSegment) {
            int mid = leftSegment + (rightSegment - leftSegment) / 2;
            this.update(tree, 2 * currIndex + 1, leftSegment, mid, pos, diff);
            this.update(tree, 2 * currIndex + 2, mid + 1, rightSegment, pos, diff);
        }
    }

    /**
     * Recursively computes the range sum in the segment tree for a given query range.
     *
     * Time: O(logn)
     * - each segment tree query at most visits 2 node per tree level
     * - in worse case(partial overlap at every level), recursive traversal descends to both left and right children
     * - but total number of visited nodes ist still bounded by O(logn)
     * Space: O(n)
     * - O(n) for segment tree stored in array of size 4n
     * - O(logn) for recursion stack proportional to height of tree
     *
     * @param tree         the segment tree array used for storing range sums
     * @param currIndex    the current index in the segment tree
     * @param leftSegment  the starting index of the segment represented by this node
     * @param rightSegment the ending index of the segment represented by this node
     * @param queryLeft    the starting index of the query range
     * @param queryRight   the ending index of the query range
     * @return the sum of elements in the overlap between [leftSegment, rightSegment] and [queryLeft, queryRight]
     */
    private int querySumRecursive(int[] tree, int currIndex, int leftSegment, int rightSegment, int queryLeft,
                                  int queryRight) {
        // case 1: no overlap
        if (queryLeft > rightSegment || queryRight < leftSegment) {
            return 0;
        }
        // case 2: total overlap. queried range is totally within the leftRange & rightRange
        if (queryLeft <= leftSegment && queryRight >= rightSegment) {
            return tree[currIndex];
        }
        // case 3: partial overlap - query both children
        int mid = leftSegment + (rightSegment - leftSegment) / 2;
        int leftSum = this.querySumRecursive(tree, 2 * currIndex + 1, leftSegment, mid, queryLeft, queryRight);
        int rightSum = this.querySumRecursive(tree, 2 * currIndex + 2, mid + 1, rightSegment, queryLeft, queryRight);
        return leftSum + rightSum;
    }

    /**
     * Recursively builds the segment tree for range sum queries.
     *
     * Time: O(n)
     * - visits each node once
     * - a segment tree for an array of size n has about 2n - 1 nodes(internal + leaves)
     * - each of the n element contributes to 1 leaf node, each internal node combines two children
     * - hence, the total recursive calls is linear in the number of nodes
     *
     * Space: O(n)
     * - tree is stored in array of size 4n (to ensure space for all possible nodes in a complete binary tree)
     * - recursive stack depth is O(logn) in the worst case
     *
     * @param arr        the original input array
     * @param tree       the tree array to populate
     * @param treeIndex  the index in the tree array currently being populated
     * @param left       the start index of the range in the original array
     * @param right      the end index of the range in the original array
     * @return the sum of the segment [left, right], stored at tree[treeIndex]
     */
    private int buildSumTree(int[] arr, int[] tree, int treeIndex, int left, int right) {
        if (left == right) {
            // leaf node, store array element
            tree[treeIndex] = arr[left];
            return arr[left];
        }
        int mid = left + (right - left) / 2; // prevent number overflow
        // get the left and right child values of this node to construct its value
        tree[treeIndex] = this.buildSumTree(arr, tree, 2 * treeIndex + 1, left, mid) +
                this.buildSumTree(arr, tree, 2 * treeIndex + 2, mid + 1, right);
        return tree[treeIndex];
    }

    /**
     * Recursively builds the segment tree for range minimum queries.
     *
     * @param arr        the original input array
     * @param tree       the tree array to populate
     * @param treeIndex  the index in the tree array currently being populated
     * @param left       the start index of the range in the original array
     * @param right      the end index of the range in the original array
     * @return the minimum value in the segment [left, right], stored at tree[treeIndex]
     */
    private int buildMinTree(int[] arr, int[] tree, int treeIndex, int left, int right) {
        if (left == right) {
            // leaf node, store array element
            tree[treeIndex] = arr[left];
            return arr[left];
        }
        int mid = left + (right - left) / 2;
        tree[treeIndex] = Math.min(this.buildMinTree(arr, tree, 2 * treeIndex + 1, left, mid),
                this.buildMinTree(arr, tree, 2 * treeIndex + 2, mid + 1, right));
        return tree[treeIndex];
    }

    /**
     * Recursively builds the segment tree for range maximum queries.
     *
     * @param arr        the original input array
     * @param tree       the tree array to populate
     * @param treeIndex  the index in the tree array currently being populated
     * @param left       the start index of the range in the original array
     * @param right      the end index of the range in the original array
     * @return the maximum value in the segment [left, right], stored at tree[treeIndex]
     */
    private int buildMaxTree(int[] arr, int[] tree, int treeIndex, int left, int right) {
        if (left == right) {
            // leaf node, store array element
            tree[treeIndex] = arr[left];
            return arr[left];
        }
        int mid = left + (right - left) / 2;
        tree[treeIndex] = Math.max(this.buildMaxTree(arr, tree, 2 * treeIndex + 1, left, mid),
                this.buildMaxTree(arr, tree, 2 * treeIndex + 2, mid + 1, right));
        return tree[treeIndex];
    }
}

public class SegmentTreeDemo {
    public static void main(String[] args) {
        int[] arr1 = {2, 4, 5, 7, 8, 9};
        SegmentTree tree = new SegmentTree(arr1);
        System.out.println(tree.querySum(2, 5));
    }
}
