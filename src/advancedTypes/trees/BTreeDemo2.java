package advancedTypes.trees;

import java.util.Arrays;

class BTreeNode2 {
    int t; // node's minimum degree
    int[] keys; // keys stored in this node
    int keyCount; // number of keys used
    int maxKeyCount;
    boolean isLeaf; // true if leaf node
    BTreeNode2[] children;

    BTreeNode2(int t, boolean isLeaf) {
        this.t = t;
        this.maxKeyCount = 2 * t - 1;
        this.keys = new int[2 * t - 1];
        this.keyCount = 0;
        this.children = new BTreeNode2[2 * t];
        this.isLeaf = isLeaf;
    }

    public void insertKey(int key) {
        int i = this.keyCount - 1;

        // shift keys to make room
        while (i >= 0 && this.keys[i] > key) {
            this.keys[i+1] = this.keys[i];
            i -= 1;
        }
        // insert key
        this.keys[i + 1] = key;
        this.keyCount += 1;
    }
}

/**
 * Implement a B-Tree with minimum degree t:
 * - max keys in a node: 2t - 1
 * - max children: 2t
 * - min keys(non root) = t - 1
 */
class BTree2 {
    BTreeNode2 root;
    int t; // minimum degree

    BTree2(int t) {
        this.t = t;
        this.root = new BTreeNode2(t, true);
    }

    public boolean search(int key) {
        return this.searchRecursive(this.root, key);
    }

    private boolean searchRecursive(BTreeNode2 node, int key) {
        int low = 0;
        int high = node.keyCount - 1;
        int mid;

        // binary search inside node.keys[]
        while (low <= high) {
            mid = (low + high) / 2;

            if (node.keys[mid] == key) {
                return true; // found exact match
            }
            if (node.keys[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // at this point: low = first index > key (insertion point)
        if (node.isLeaf) {
            return false;
        }
        // recurse into the correct child
        // move into the subtree whose value range contains the key
        return this.searchRecursive(node.children[low], key);
    }

    public void insert(int key) {
        // 1. if root is not full, insert into root
        if (this.root.keyCount < 2 * this.t - 1) {
            this.insertNonFull(this.root, key);
            return;
        }
        // 2. if root is full, split root first -> grow tree height by 1
        BTreeNode2 newRoot = new BTreeNode2(this.t, false);
        newRoot.children[0] = this.root;

        this.splitChild(newRoot, 0, this.root);

        this.root = newRoot;
        this.insertNonFull(newRoot, key);
    }

    /**
     * Handles inserting into a node that is guaranteed NOT full.
     *
     * Rules:
     * - if node.isLeaf -> directly insert into the keys array in sorted position
     * - if internal node -> find the child to descend into, split child if full, then recurse.
     * @param node
     * @param key
     */
    private void insertNonFull(BTreeNode2 node, int key) {
        // case 1: if node.isLeaf -> directly insert into the keys array in sorted position
        if (node.isLeaf) {
            node.insertKey(key);
            return;
        }
        // case 2: if internal node -> find the child to descend into, split child if full, then recurse
        int i = node.keyCount - 1;
        while (i >= 0 && key < node.keys[i]) {
            i -= 1;
        }
        int childIndex = i + 1;
        // if child is full, split it first
        if (node.children[childIndex] != null && node.children[childIndex].keyCount == node.children[childIndex].maxKeyCount) {
            this.splitChild(node, childIndex, node.children[childIndex]);
            // after split, one key was promoted into 'node'
            // decide which of the two children to descend into
            if (key > node.keys[childIndex]) {
                childIndex += 1;
            }
        }
        this.insertNonFull(node.children[childIndex], key);
    }

    private void splitChild(BTreeNode2 parent, int index, BTreeNode2 child) {

        BTreeNode2 newNode = new BTreeNode2(parent.t, child.isLeaf);

        // STEP 1 — Save median BEFORE modifying child
        int median = child.keys[child.t - 1];

        // STEP 2 — Copy last t-1 keys to newNode
        for (int i = 0; i < child.t - 1; i++) {
            newNode.keys[i] = child.keys[i + child.t];
            child.keys[i + child.t] = 0;   // optional cleanup
        }

        // STEP 3 — Copy children
        if (!child.isLeaf) {
            for (int i = 0; i < child.t; i++) {
                newNode.children[i] = child.children[i + child.t];
                child.children[i + child.t] = null; // optional cleanup
            }
        }

        // STEP 4 — Adjust key counts
        child.keyCount = child.t - 1;
        newNode.keyCount = child.t - 1;

        // STEP 5 — Shift parent keys to make room
        for (int i = parent.keyCount - 1; i >= index; i--) {
            parent.keys[i + 1] = parent.keys[i];
        }

        // Insert median into parent
        parent.keys[index] = median;

        // STEP 6 — Shift parent children
        for (int i = parent.keyCount; i >= index + 1; i--) {
            parent.children[i + 1] = parent.children[i];
        }

        parent.children[index + 1] = newNode;
        parent.keyCount += 1;
    }
}

public class BTreeDemo2 {
    public static void main(String[] args) {
        BTree2 tree = new BTree2(2);  // minimum degree t = 2 (classic B-tree)

        System.out.println("=== Test 1: Simple inserts (no split) ===");
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        printTree(tree.root);

        System.out.println("\n=== Test 2: Insert causing first split ===");
        tree.insert(6);
        printTree(tree.root);

        System.out.println("\n=== Test 3: More inserts ===");
        tree.insert(12);
        tree.insert(30);
        tree.insert(7);
        tree.insert(17);
        printTree(tree.root);

        System.out.println("\n=== Test 4: Full split chain ===");
        tree.insert(3);
        tree.insert(4);
        tree.insert(2);
        tree.insert(50);
        printTree(tree.root);

        System.out.println("\n=== Search Test ===");
        System.out.println("Search 17: " + tree.search(17)); // true
        System.out.println("Search 999: " + tree.search(999)); // false
        System.out.println("Search 6: " + tree.search(6)); // true
    }

    static void printTree(BTreeNode2 node) {
        printTree(node, 0);
    }

    static void printTree(BTreeNode2 node, int depth) {
        if (node == null) return;

        char[] indent = new char[depth * 4];
        Arrays.fill(indent, ' ');
        String prefix = new String(indent);

        System.out.println(prefix + Arrays.toString(Arrays.copyOf(node.keys, node.keyCount)));

        if (!node.isLeaf) {
            for (int i = 0; i <= node.keyCount; i++) {
                if (node.children[i] != null) {
                    printTree(node.children[i], depth + 1);
                }
            }
        }
    }
}
