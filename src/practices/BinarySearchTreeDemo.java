package practices;

class Node{
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree{
    Node root;

    BinarySearchTree(int value) {
        this.root = new Node(value);
    }

    /**
     * Inserts value into the BST.
     *
     * Time:
     *  - Worst: O(n)
     *  - Average: O(logn)
     * Space:
     *  - Worst: O(n)
     *  - Average: O(logn)
     * where n is the number of nodes in BST
     *
     * @param value
     */
    public void insert(int value) {
        this.root = this.insertRecursive(this.root, value);

    }

    private Node insertRecursive(Node node, int value) {
        if (node == null) return new Node(value);
        if (value <= node.value) {
            node.left = this.insertRecursive(node.left, value);
        } else {
            node.right = this.insertRecursive(node.right, value);
        }
        return node;
    }

    /**
     * Searches if a value is in BST.
     * Time:
     *  - Worst: O(n)
     *  - Average: O(logn)
     * Space:
     * - Worst: O(n)
     * - Average: O(logn)
     * where n is the number of nodes in BST
     * @param value
     * @return
     */
    public Node search(int value) {
        return this.searchRecursive(this.root, value);
    }

    private Node searchRecursive(Node node, int value) {
        if (node == null || node.value == value) return node;
        if (value <= node.value) return this.searchRecursive(node.left, value);
        return this.searchRecursive(node.right, value);
    }

    /**
     * Delete a node with the given value from the BST.
     *
     * Time:
     * - Worst: O(n)
     * - Average: O(logn)
     * Space:
     * - Worst: O(n)
     * - Average: O(logn)
     * where n is the number of nodes in the BST
     * @param value
     */
    public void delete(int value) {
        this.root = this.deleteRecursive(this.root, value);
    }

    /**
     * Recursively delete a node rooted at given node.
     *
     * Time:
     * - Worst: O(n)
     * - Average: O(logn)
     * Space (Recursive):
     * - Worst: O(n)
     * - Average: O(logn)
     * where n is the number of nodes in the BST
     * @param node
     * @param value
     * @return
     */
    private Node deleteRecursive(Node node, int value) {
        if (node == null) return null;

        if (value < node.value) {
            node.left = this.deleteRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = this.deleteRecursive(node.right, value);
        } else {
            // 🎯found node to be deleted.
            // there are 3 cases to consider here:
            // case 1: node to delete has 0 children
            //  -> set the parent's left/right child of node-to-delete as null. then destroy node-to-delete
            if (node.left == null && node.right == null) {
                return null;
            }
            // case 2: node to delete has 1 child
            //  -> set the parent's left/right child of node-to-delete as the node-to-delete's only child. then destroy node-to-delete
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // case 3: node to delete has 2 children
            //  -> find the inorder successor of node-to-delete's right child.
            //  -> replace node-to-delete's value to be inorder successor value
            //  -> remove inorder successor node.
            Node successor = this.findMin(node.right);
            node.value = successor.value;
            node.right = this.deleteRecursive(node.right, successor.value);
        }
        return node;
    }

    /**
     * Find the minimum value target node starting from node.
     * This node is also the leftmost child node from node.
     * @param node
     * @return
     */
    private Node findMin(Node node) {
        if (node.left == null) return node;
        return this.findMin(node.left);
    }

    public void inOrderTraversal() {
        this.inOrderRecursive(this.root);
        System.out.println();
    }

    private void inOrderRecursive(Node node) {
        if (node == null) return;
        this.inOrderRecursive(node.left);
        System.out.print(node.value + " ");
        this.inOrderRecursive(node.right);
    }
}

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);

        bst.inOrderTraversal();
    }
}
