package advancedTypes.trees;

import java.util.ArrayDeque;

class TwoThreeNode{
    int keyCount; // 1 or 2
    int key1; // always valid
    int key2; // valid only if keyCount == 2

    TwoThreeNode left; // < key1
    TwoThreeNode middle; // between key1 and key2 (or > key1 if 2-node)
    TwoThreeNode right; // > key2 (only for 3-node)

    TwoThreeNode(int key) {
        this.keyCount = 1;
        this.key1 = key;
    }

    /**
     * isLeaf definition depends on the following invariants:
     * - 2-node internal: left and middle are non-null
     * - 3-node internal: left, middle and right are non-null
     * @return
     */
    boolean isLeaf() {
        return this.left == null && this.middle == null && this.right == null;
    }

    boolean isTwoNode() {
        return this.keyCount == 1;
    }

    boolean isThreeNode() {
        return this.keyCount == 2;
    }

    boolean contains(int x) {
        if (this.key1 == x) return true;
        return this.keyCount == 2 && this.key2 == x;
    }

    int maxKey() {
        if (keyCount == 2) return this.key2;
        return this.key1;
    }

    int minKey() {
        return this.key1;
    }

    /**
     * Assume currently a 2-node leaf; turns into 3-node leaf
     * @param x
     */
    void insertKeyIntoLeaf(int x) {
        if (!isLeaf() || !isTwoNode()) {
            throw new IllegalStateException("insertKeyIntoLeaf requires a 2-node leaf");
        }
        this.keyCount = 2;
        this.key2 = x;
        if (this.key1 > this.key2) {
            int temp = this.key1;
            this.key1 = this.key2;
            this.key2 = temp;
        }
    }

    String keys() {
        if (keyCount == 1) return "(" + key1 + ")";
        return "(" + key1 + "," + key2 + ")";
    }
}

class Split {
    int promotedKey;
    TwoThreeNode left;
    TwoThreeNode right;

    Split(int promotedKey, TwoThreeNode left, TwoThreeNode right) {
        this.promotedKey = promotedKey;
        this.left = left;
        this.right = right;
    }
}

class TwoThreeTree {
    TwoThreeNode root;

    TwoThreeTree(int key) {
        this.root = new TwoThreeNode(key);
    }

    public boolean search(int x) {
        return this.containsRecursive(this.root, x);
    }

    private boolean containsRecursive(TwoThreeNode node, int x) {
        if (node == null) return false;
        if (node.contains(x)) return true;
        if (node.isTwoNode()) {
            if (x < node.key1) return this.containsRecursive(node.left, x);
            return this.containsRecursive(node.middle, x);
        } else { // 3 node
            if (x < node.key1) return this.containsRecursive(node.left, x);
            if (x < node.key2) return this.containsRecursive(node.middle, x);
            return this.containsRecursive(node.right, x);
        }
    }

    /**
     * Insert goes to a leaf.
     * If leaf is a 2-node, insert in place.
     * If leaf is a 3-node, split into two 2-nodes and promote middle key.
     * Parent absorbs if it's a 2-node; otherwise parent splits too.
     * This may cascade to root, where we create a new root.
     * Leaf depth never changes, so it stays balanced.
     * @param x
     */
    public void insert(int x) {
        Split split = this.insertRecursive(this.root, x);
        if (split == null) return;

        // split has propagated all the way to root, here we create a new root
        TwoThreeNode newRoot = new TwoThreeNode(split.promotedKey);
        newRoot.left = split.left;
        newRoot.middle = split.right;
        this.root = newRoot;
    }

    private Split insertRecursive(TwoThreeNode node, int x) {
        if (node == null) return null;
        if (node.contains(x)) return null; // ignore duplicates
        // 1. deal with leaf nodes
        if (node.isLeaf()) {
            // case 1: a 2-node
            //  -> insert into node in sorted order
            //  -> return null
            if (node.isTwoNode()) {
                node.insertKeyIntoLeaf(x);
                return null;
            } else if (node.isThreeNode()) {
                // case 2: a 3-node
                // figure out which is the promotedKey between x, node.key1, node.key2
                int a = node.key1;
                int b = node.key2;
                int[] sorted = this.sortThree(a, b, x);
                int min = sorted[0];
                int mid = sorted[1];
                int max = sorted[2];
                // figure out the 2 nodes to enter into split and create the Split obj
                // return split obj
                return new Split(
                        mid,
                        new TwoThreeNode(min),
                        new TwoThreeNode(max)
                );
            } else {
                throw new IllegalStateException("Invalid keyCount:" + node.keyCount);
            }
        }
        // 2. Deal with non leaf nodes, figure out which node to recurse into. ie. propagate down the tree
        // choose which branch to recurse into
        TwoThreeNode child;
        int which; // 0=left, 1=middle, 2=right
        if (node.isTwoNode()) {
            if (x < node.key1) { child = node.left; which = 0;}
            else { child = node.middle; which = 1;}
        } else { // 3-node
            if (x < node.key1) { child = node.left; which = 0;}
            else if (x < node.key2) { child = node.middle; which = 1;}
            else { child = node.right; which = 2;}
        }
        if (!node.isLeaf() && child == null) {
            throw new IllegalStateException("Internal node has null child: " + node.keys());
        }
        Split childSplit = this.insertRecursive(child, x);
        if (childSplit == null) return null;

        // 3. Absorb a child split into a 2-node parent
        // if node is a 2-node, absorbing makes it a 3-node.
        // case 1: split came from left
        //  -> promoted key becomes new key1, old key1 shifts to key2
        // case 2: split came from middle
        //  -> promoted key comes key2
        if (node.isTwoNode()) {
            if (which == 0) { // case 1
                node.keyCount = 2;
                node.key2 = node.key1;
                node.key1 = childSplit.promotedKey;

                TwoThreeNode oldMiddle = node.middle;
                node.left = childSplit.left;
                node.middle = childSplit.right;
                node.right = oldMiddle;
            } else { // case 2
                node.keyCount = 2;
                node.key2 = childSplit.promotedKey;

                node.middle = childSplit.left;
                node.right = childSplit.right;
                // node.left stays
            }
            return null;
        }

        // 4. Split propagation into a 3-node parent
        // if node is already a 3-node and a child split happens, we temporarily have 3 keys and 4 children
        //  -> must split into two 2-nodes and promote the middle key upward
        // 3 cases depending on which child split:
        // case 1: split from LEFT, which = 0
        // case 2: split from MIDDLE, which = 1
        // case 3: split from RIGHT, which = 2
        if (node.isThreeNode()) {
            int a = node.key1, b = node.key2;
            int k = childSplit.promotedKey;

            TwoThreeNode oldLeft = node.left;
            TwoThreeNode oldMiddle = node.middle;
            TwoThreeNode oldRight = node.right;

            if (which == 0) { // left split: k < a < b
                TwoThreeNode left2 = new TwoThreeNode(k);
                left2.left = childSplit.left;
                left2.middle = childSplit.right;

                TwoThreeNode right2 = new TwoThreeNode(b);
                right2.left = oldMiddle;
                right2.middle = oldRight;

                return new Split(a, left2, right2);
            }
            if (which == 1) { // middle split: a < k < b
                TwoThreeNode left2 = new TwoThreeNode(a);
                left2.left = oldLeft;
                left2.middle = childSplit.left;

                TwoThreeNode right2 = new TwoThreeNode(b);
                right2.left = childSplit.right;
                right2.middle = oldRight;

                return new Split(k, left2, right2);
            }
            // right split: a < b < k
            TwoThreeNode left2 = new TwoThreeNode(a);
            left2.left = oldLeft;
            left2.middle = oldMiddle;

            TwoThreeNode right2 = new TwoThreeNode(k);
            right2.left = childSplit.left;
            right2.middle = childSplit.right;

            return new Split(b, left2, right2);
        }
        return null;
    }

    /**
     * Validate the 2-3 tree is built correctly.
     *
     * Invariants checked:
     * 1) Node is either a 2-node (1 key, 2 children) or a 3-node (2 keys, 3 children)
     * 2) Keys inside a node are strictly sorted (key1 < key2 for 3-nodes)
     * 3) Internal nodes have correct non-null children counts
     * 4) All subtree keys fall within the allowed (low, high) range
     * 5) All leaves are at the same depth
     */
    public void validate() {
        LeafDepthTracker tracker = new LeafDepthTracker();
        // at root level, the key range must not restrict keys
        // root can have any integer keys, so the low and high are null
        boolean isValidated = this.validateRecursive(this.root, 0, tracker, null, null);
        if (isValidated) {
            System.out.println("2-3 Tree is validated and correct!");
        } else {
            System.out.println("2-3 Tree is built incorrectly");
        }
    }

    private boolean validateRecursive(TwoThreeNode node, Integer height, LeafDepthTracker t,
                                      Integer low, Integer high) {

        if (node == null) return true;

        // 1. validate keys sorted
        if (node.isThreeNode() && node.key1 >= node.key2) return false;

        // 2. validate key ranges
        if (low != null && node.key1 <= low) return false;
        if (high != null && node.maxKey() >= high) return false;

        if (node.isLeaf()) {
            // leaf depth check
            if (t.leafDepth == null) t.leafDepth = height;
            else if (t.leafDepth != height) return false;
            return true;
        }

        // 3. validate child counts
        if (node.isTwoNode()) {
            if (node.left == null || node.middle == null || node.right != null)
                return false;

            // recurse
            return validateRecursive(node.left, height+1, t, low, node.key1)
                    && validateRecursive(node.middle, height+1, t, node.key1, high);
        }

        if (node.isThreeNode()) {
            if (node.left == null || node.middle == null || node.right == null)
                return false;

            return validateRecursive(node.left, height+1, t, low, node.key1)
                    && validateRecursive(node.middle, height+1, t, node.key1, node.key2)
                    && validateRecursive(node.right, height+1, t, node.key2, high);
        }

        return false;
    }

    /**
     * Prints the 2-3 Tree in level order to show shape of the tree
     */
    public void printLevelOrder() {
        if (this.root == null) {
            System.out.println("(empty)");
            return;
        }
        ArrayDeque<LevelOrderTracker> queue = new ArrayDeque<>();
        queue.addLast(new LevelOrderTracker(this.root, 0));
        Integer currentLevel = queue.peekFirst() == null ? 0 : queue.peekFirst().level;
        System.out.print("Level 0: ");
        while (!queue.isEmpty()) {
            LevelOrderTracker tracker = queue.removeFirst();
            TwoThreeNode node = tracker.node;
            Integer level = tracker.level;
            // level changed → start new line
            if (level != currentLevel) {
                System.out.println();                // finish previous level
                System.out.print("Level " + level + ": ");
                currentLevel = level;
            }
            System.out.print(tracker.node.keys() + " ");
            if (node.left != null) {
                queue.addLast(new LevelOrderTracker(node.left, level + 1));
            }
            if (node.middle != null) {
                queue.addLast(new LevelOrderTracker(node.middle, level + 1));
            }
            if (node.right != null) {
                queue.addLast(new LevelOrderTracker(node.right, level + 1));
            }
        }
        System.out.println();
    }

    /**
     * Prints the 2-3 Tree in ascending order
     */
    public void inOrderTraversal() {
        this.inOrderRecursive(this.root);
        System.out.println();
    }

    private void inOrderRecursive(TwoThreeNode node) {
        if (node == null) return;
        if (node.isTwoNode()) { // 2-node
            this.inOrderRecursive(node.left);
            System.out.print(node.key1 + ", ");
            this.inOrderRecursive(node.middle);
        } else { // 3-node
            this.inOrderRecursive(node.left);
            System.out.print(node.key1 + ", ");
            this.inOrderRecursive(node.middle);
            System.out.print(node.key2 + ", ");
            this.inOrderRecursive(node.right);
        }
    }

    /**
     * We assume a is smaller than b here. c is unknown position among the 2
     * @param a
     * @param b
     * @param c
     * @return
     */
    private int[] sortThree(int a, int b, int c) {
        int min, mid, max;
        if (c < a) {
            min = c;
            mid = a;
            max = b;
        } else if (c < b) {
            min = a;
            mid = c;
            max = b;
        } else {
            min = a;
            mid = b;
            max = c;
        }
        return new int[]{min, mid, max};
    }
}

class LeafDepthTracker {
    Integer leafDepth = null;
}

class LevelOrderTracker {
    TwoThreeNode node;
    Integer level;

    public LevelOrderTracker(TwoThreeNode node, Integer level) {
        this.node = node;
        this.level = level;
    }
}

public class TwoThreeTreeDemo {
    public static void main(String[] args) {
        TwoThreeTree tree = new TwoThreeTree(50);
        tree.insert(60);
        tree.insert(70);
        tree.insert(40);
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);
        tree.insert(80);
        tree.insert(90);
        tree.insert(100);
        tree.validate();
        tree.inOrderTraversal();
        tree.printLevelOrder();
        System.out.println(tree.search(50));
        System.out.println(tree.search(60));
        System.out.println(tree.search(70));
        System.out.println(tree.search(40));
        System.out.println(tree.search(30));
        System.out.println(tree.search(20));
        System.out.println(tree.search(10));
        System.out.println(tree.search(80));
        System.out.println(tree.search(90));
        System.out.println(tree.search(100));
    }
}
