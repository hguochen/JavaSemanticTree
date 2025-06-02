package advancedTypes.trees;


class AVLNode {
    public int data;
    public AVLNode left;
    public AVLNode right;
    public int height;

    public AVLNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

/**
 * AVLTree is a self-balancing tree that guarantees insertion, search and deletion operations run at O(logn) time
 * complexity.
 *
 * The self-balancing mechanism consists of 2 operations, leftRotate & rightRotate, which rotates nodes in the heavy
 * side of the subtree so that eventually each left and right subTree pairs has balance factors of no more than 1 and
 * less than -1.
 *
 * Use AVLTress when
 * - we need do alot more data searches than insert/deletes
 */
class AVLTree {
    private AVLNode root;

    public AVLTree(int data) {
        this.root = new AVLNode(data);
    }

    /**
     * Insert data into AVL tree
     * Time: O(logn)
     * Space: O(1)
     *
     * @param data
     */
    public void insert(int data) {
        this.root = this.insertRecursive(this.root, data);
    }

    private AVLNode insertRecursive(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);
        if (data < node.data) {
            node.left = this.insertRecursive(node.left, data);
        } else {
            node.right = this.insertRecursive(node.right, data);
        }
        node.height = 1 + Math.max(this.height(node.left), this.height(node.right));

        // update the balance factor and balance the tree
        int balanceFactor = this.getBalanceFactor(node);

        // case 1: node inserted into left subtree && left heavy => rotateRight
        if (balanceFactor > 1 && data < node.left.data) {
            return this.rightRotate(node);
        }
        // case 2: node inserted into right subtree && right heavy => rotateLeft
        if (balanceFactor < -1 && data > node.right.data) {
            return this.leftRotate(node);
        }
        // case 3: node inserted into left subtree && right heavy => rotateLeft then rotateRight
        // Left Rotate on node.left, then Right Rotate on node
        if (balanceFactor > 1 && data > node.left.data) {
            node.left = this.leftRotate(node.left);
            return this.rightRotate(node);
        }
        // case 4: node inserted into right subtree && left heavy => rotateRight then rotateLeft
        // Right Rotate on node.right, then Left Rotate on node
        if (balanceFactor < -1 && data < node.right.data) {
            node.right = this.rightRotate(node.right);
            return this.leftRotate(node);
        }
        return node;
    }

    /**
     * Delete the node with data
     * Time: O(logn)
     * Space: O(1)
     * @param data
     */
    public void delete(int data) {
        this.root = this.deleteRecursive(this.root, data);
    }

    private AVLNode deleteRecursive(AVLNode node, int data) {
        // base case: leaf node
        if (node == null) return null;
        // recursively find the node to delete
        if (data < node.data) {
            node.left = this.deleteRecursive(node.left, data);
        } else if (data > node.data){
            node.right = this.deleteRecursive(node.right, data);
        } else {
            // found the node to be deleted
            if (node.left == null) {
                // only has right child
                return node.right;
            } else if (node.right == null) {
                // only has left child
                return node.left;
            }
            // has both left and right children
            // find in order successor
            AVLNode temp = this.getMinNode(node.right);
            node.data = temp.data;
            node.right = this.deleteRecursive(node.right, temp.data);
        }

        node.height = 1 + Math.max(this.height(node.left), this.height(node.right));

        int balanceFactor = this.getBalanceFactor(node);

        // case 1: node removed from right subtree, left subtree is now heavy
        if (balanceFactor > 1 && this.getBalanceFactor(node.left) >= 0) {
            return this.rightRotate(node);
        }
        // case 2: node removed from left subtree, right subtree is now heavy
        if (balanceFactor < -1 && this.getBalanceFactor(node.right) <= 0) {
            return this.leftRotate(node);
        }
        // case 3: Left-Right case (left child is right heavy)
        if (balanceFactor > 1 && this.getBalanceFactor(node.left) < 0) {
            node.left = this.leftRotate(node.left);
            return this.rightRotate(node);
        }
        // case 4: Right-Left case (right child is left heavy)
        if (balanceFactor < -1 && this.getBalanceFactor(node.right) > 0) {
            node.right = this.rightRotate(node.right);
            return this.leftRotate(node);
        }
        return node;
    }

    private AVLNode getMinNode(AVLNode node) {
        if (node.left == null) return node;
        return this.getMinNode(node.left);
    }

    /**
     * B (node.right) becomes the new root of this subtree.
     *
     * @param node is the node subjected to a leftRotate. it ends up being on the left of its original position in the
     *             tree. this node's right child will become its parent at the end of leftRotate. ie. node is the node
     *             being rotated down to the left.
     * @return the new parent after left rotation
     */
    private AVLNode leftRotate(AVLNode node) {
        AVLNode newRoot = node.right;
        AVLNode leftSubTree = newRoot.left;

        newRoot.left = node;
        node.right = leftSubTree;

        node.height = 1 + Math.max(this.height(node.left), this.height(node.right));
        newRoot.height = 1 + Math.max(this.height(newRoot.left), this.height(newRoot.right));
        return newRoot;
    }

    /**
     *
     * @param node is the node subjected to a rightRotate, it ends up being on the right of its original position in
     *             the tree. this node's left child will become its parent at the end of rightRotate
     * @return the new parent after right rotation
     */
    private AVLNode rightRotate(AVLNode node) {
        // get node references
        AVLNode newRoot = node.left;
        AVLNode rightSubTree = newRoot.right;

        // right rotate nodes
        newRoot.right = node;
        node.left = rightSubTree;

        // recalculate the new heights of nodes that rotated
        node.height = 1 + Math.max(this.height(node.left), this.height(node.right));
        newRoot.height = 1 + Math.max(this.height(newRoot.left), this.height(newRoot.right));

        return newRoot;
    }

    public int height(AVLNode node) {
        if (node == null) return -1;
        int leftHeight = this.height(node.left);
        int rightHeight = this.height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private int getBalanceFactor(AVLNode node) {
        if (node == null) return 0;
        int leftHeight = this.height(node.left);
        int rightHeight = this.height(node.right);
        return leftHeight - rightHeight;
    }
}

public class AVLTreeDemo {
    public static void main(String[] args) {

    }
}
