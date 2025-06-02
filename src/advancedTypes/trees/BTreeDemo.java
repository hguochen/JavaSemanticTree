package advancedTypes.trees;

import java.util.ArrayList;
import java.util.List;

class BTreeNode {
    public List<Integer> keys;
    public List<BTreeNode> children;
    // indicates whether this node is a leaf node or an internal node
    // 1. Guides insertion logic
	//  - When inserting, if leaf == true, you insert the key directly into that node.
    //  - If leaf == false, you need to descend into the appropriate child node.
    // 2. Simplifies deletion
    //	- Deleting from a leaf is simpler (just remove the key).
    //	- Deleting from an internal node requires special handling (borrow, merge, etc.).
    public boolean leaf;

    public BTreeNode(boolean leaf) {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.leaf = leaf;
    }
}

/**
 * B-Tree is a self-balancing, multi way tree data structure that:
 * - maintains sorted keys
 * - allows logn time operations:
 *      - Search
 *      - Insertion
 *      - Deletion
 * It is designed for efficient disk memory access, making it ideal for databases, file systems, and indexes
 *
 * Core Properties of B-Tree of minimum degree 't'
 *
 * Property                     | Description
 * Keys per node                  Between t - 1 and 2t - 1 keys (except root)
 * Children per internal node     Between t and 2t children
 * Sorted keys                    All keys in a node are sorted
 * Balanced                       All leaves are at the same depth
 * Node can have multiple keys    Unlike BSTs, reduces tree height
 *
 * Real World Uses
 * System                       | How it uses B-Trees
 * MySQL(InnoDB)                  B+Tree index on primary and secondary keys
 * PostgreSQL                     Uses B-Tree for general-purpose indexing
 * NTFS, HFS+ file system         Store directory structures with B-Tree
 * Lucene(ElasticSearch)          Inverted indexes uses B+Tree variants
 *
 * Different between B-Tree vs B+Tree
 * Feature                  | B-Tree                    | B+Tree
 * Keys in internal nodes     Yes                         No(only in leaves)
 * Range queries              Slower                      Faster(linked leaves)
 * Used in                    General data structure      Databases, file systems
 *
 * Why B-Trees Are Great
 * 	•	Handles large data on disk efficiently
 * 	•	Reduces disk reads: high branching factor → low height
 * 	•	Keeps data sorted for fast search and range queries
 */
class BTree {
    private BTreeNode root;
    private final int minDegree;

    public BTree(int minDegree) {
        this.root = new BTreeNode(true);
        this.minDegree = minDegree;
    }

    /**
     *  Search
     * 	•	Binary search inside node’s keys
     * 	•	Recursively descend into the right child
     * 	•	Time: O(log n)
     * @param key
     * @return
     */
    public BTreeNode search(int key) {
        return this.searchRecursive(this.root, key);
    }

    /**
     *
     * @param node
     * @param key
     * @return
     */
    public BTreeNode searchRecursive(BTreeNode node, int key) {
        if (node == null) {
            return null;
        }
        int i = 0;
        while (i < node.keys.size() && key > node.keys.get(i)) {
            i += 1;
        }
        // key found
        if (i < node.keys.size() && key == node.keys.get(i)) {
            return node;
        }
        // key not found and no children
        if (node.leaf) return null;
        // recurse into appropriate child which may contain key
        return this.searchRecursive(node.children.get(i), key);
    }

    /**
     * To insert a key such that:
     * - B-tree remains sorted
     * - tree stays balanced
     * - no node has more than 2(minDegree) - 1 keys
     *
     * Step 1: Start at the root
     * 	•	Navigate down to the correct leaf node where the key belongs, using comparisons.
     *
     * Step 2: If leaf has space (less than 2t - 1 keys)
     * 	•	Insert the key into the node in sorted order.
     *
     * Step 3: If the node is full
     * 	•	Split the node before inserting.
     * 	•	Push the middle key up to the parent.
     * 	•	Recurse into the correct new child and repeat if necessary.
     *
     * @param key
     */
    public void insert(int key) {
        BTreeNode root = this.root;
        // check if the root keys are full
        if (this.root.keys.size() == 2 * minDegree - 1) {
            BTreeNode newRoot = new BTreeNode(false);
            this.root = newRoot;
            newRoot.children.add(0, root);
            this.splitChild(newRoot, 0);
            this.insertNonFull(newRoot, key);
        } else {
            // root keys are NOT full
            this.insertNonFull(root, key);
        }
    }

    /**
     * if (node.leaf) {
     *     // Insert directly into sorted position in the leaf node
     * } else {
     *     // Find child index i
     *     if (child[i] is full) {
     *         splitChild(node, i);
     *         // Decide which of the two new children to descend into
     *         if (key > node.keys[i]) i++;
     *     }
     *     insertNonFull(child[i], key);
     * }
     * @param node
     * @param key
     */
    private void insertNonFull(BTreeNode node, int key) {
        int i = node.keys.size() - 1;
        // is a leaf node
        if (node.leaf) {
            while (i >= 0 && key < node.keys.get(i)) {
                i -= 1;
            }
            node.keys.add(i+1, key);
        } else {
            //not a leaf node
            while (i >= 0 && key < node.keys.get(i)) {
                i -= 1;
            }
            // set i to the position of the child node to go to
            i += 1;
            if (node.children.get(i).keys.size() == 2 * minDegree - 1) {
                // child node is full
                this.splitChild(node, i);
                if (key > node.keys.get(i)) {
                    i += 1;
                }
            }
            this.insertNonFull(node.children.get(i), key);
        }
    }

    /**
     * Splits the 'index' child node of root(which is assumed to be full) at the median value
     *
     * Splitting a full node
     * If a node has 2t - 1 keys and we try to insert:
     * 	•	Middle key (at index t - 1) moves up to the parent.
     * 	•	Node splits into:
     * 	•	Left child with t - 1 keys
     * 	•	Right child with t - 1 keys
     *
     * @param root the parent node of the full node which we want to split up
     * @param index the index of the child node in this.children which is full
     */
    private void splitChild(BTreeNode root, int index) {
        // the full child of root node
        BTreeNode fullChild = root.children.get(index);
        BTreeNode newNode = new BTreeNode(fullChild.leaf);

        // promote the median key to root
        int medianKey = fullChild.keys.get(minDegree - 1);
        root.keys.add(index, medianKey);
        root.children.add(index + 1, newNode);

        // copy keys to newNode
        newNode.keys = new ArrayList<>(fullChild.keys.subList(minDegree, 2 * (minDegree) - 1));
        fullChild.keys = new ArrayList<>(fullChild.keys.subList(0, minDegree - 1));

        // if fullChild is not a leaf, we reassign fullChild's children to fullChild and newNode
        if (!fullChild.leaf) {
            newNode.children = new ArrayList<>(fullChild.children.subList(minDegree, 2 * minDegree));
            fullChild.children = new ArrayList<>(fullChild.children.subList(0, minDegree));
        }
    }

    /**
     * Deletion
     *
     * More complex. Three main cases:
     * 	•	Key in leaf → remove directly
     * 	•	Key in internal node:
     * 	•	Replace with predecessor or successor
     * 	•	Merge or borrow if child node is too small
     * 	•	Time: O(log n)
     *
     * Given a key k and a node x, we look for the key and take action:
     *
     * Case 1: k is in internal node x
     *
     * There are two subcases:
     * 	•	1a: The left child of k has ≥ t keys → replace k with its predecessor, recursively delete the predecessor.
     * 	•	1b: The right child of k has ≥ t keys → replace k with its successor, recursively delete the successor.
     * 	•	1c: Both children have t-1 keys → merge the two children and k into one, then recursively delete.
     *
     * Case 2: k is in a leaf node
     * 	•	Just remove k directly.
     *
     * Case 3: k is not in node x
     * 	•	Determine the child subtree where k should be.
     * 	•	Before descending, ensure the child has ≥ t keys (borrow or merge if not).
     * 	•	Recurse into child.
     * @param key
     * @return
     */
    public int delete(int key) {
        // TODO: impl
        // case 1: simple delete key from leaf
        // case 2a: internal node, left child has t keys
        // case 2b: internal node, right child has t keys
        // case 2c: internal node, both children have t-1 keys
        // case 3b: node in recursion path has only t-1 keys
        // case 3a: node has only t-1 keys, but sibling has t keys
    }
}

public class BTreeDemo {
}
