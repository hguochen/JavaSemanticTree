package advancedTypes.trees;

import java.util.LinkedList;

/**
 * Design a class BinarySearchTree to represent a generic Binary Search Tree (BST) with support for:
 * 	•	Insertion of values in BST order
 * 	•	Search for a specific value
 * 	•	In-order, pre-order, and post-order traversal
 * 	•	Deletion of nodes (optional/advanced)
 * 	•	Finding minimum and maximum values
 * 	•	Finding the height of the tree
 */
class BinarySearchTree {
    private Node<Integer> root;

    public BinarySearchTree(int data) {
        this.root = new Node<>(data);
    }

    public Node<Integer> getRoot() {
        return this.root;
    }

    /**
     * Insert value in BST order
     * Time: O(logn)
     * - where n is the size of BST
     * Space: O(1)
     *
     * @param data
     */
    public void insert(int data) {
        Node<Integer> node = new Node<>(data);
        // case 1. root is null
        if (this.root == null) {
            this.root = node;
            return;
        }

        // case 2. traverse the BST to find the correct position for data
        Node<Integer> prev = null;
        Node<Integer> curr = this.root;
        while (curr != null) {
            prev = curr;
            if (data < curr.data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        // insert node as left or right child
        if (node.data < prev.data) prev.left = node;
        else prev.right = node;
    }

    /**
     * Recursive version of insert operation
     * @param data
     */
    public void insertRecursive(int data) {
        this.root = this.insertRecursiveUtil(this.root, data);
    }
    public Node<Integer> insertRecursiveUtil(Node<Integer> node, int data) {
        // base case: empty spot found
        if (node == null) return new Node<Integer>(data);

        if (data < node.data) {
            node.left = this.insertRecursiveUtil(node.left, data);
        } else {
            node.right = this. insertRecursiveUtil(node.right, data);
        }
        return node;
    }

    /**
     * Return true if value exists
     * @param data
     * @return
     */
    public boolean contains(int data) {
        // case 1. root is null
        if (root == null) return false;
        // case 2. root is data
        // case 3. data is in one of the child BST
        Node<Integer> curr = this.root;
        while (curr != null) {
            if (curr.data == data) return true;
            if (data < curr.data) curr = curr.left;
            else curr = curr.right;
        }
        return false;
    }

    /**
     * In order traversal: left -> root -> right
     */
    public void inOrder(Node node) {
        if (node == null) return;
        if (node.left != null) this.inOrder(node.left);
        System.out.println(node.data);
        if (node.right != null) this.inOrder(node.right);
    }

    /**
     * Pre order traversal: root -> left -> right
     */
    public void preOrder(Node<Integer> node) {
        if (node == null) return;
        System.out.println(node.data);
        if (node.left != null) this.preOrder(node.left);
        if (node.right != null) this.preOrder(node.right);
    }

    /**
     * Post order traversal: left -> right -> root
     */
    public void postOrder(Node<Integer> node) {
        if (node == null) return;
        if (node.left != null) this.postOrder(node.left);
        if (node.right != null) this.postOrder(node.right);
        System.out.println(node.data);
    }

    /**
     * Level order traversal is essentially a Breadth first search algorithm
     */
    public void levelOrder() {
        if (this.root == null) return;
        LinkedList<Node<Integer>> queue = new LinkedList<>();
        queue.addLast(this.root);

        // print by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node<Integer> curr = queue.removeFirst();
                System.out.print(curr.getData() + " ");
                if (curr.left != null) {
                    queue.addLast(curr.left);
                }
                if (curr.right != null) {
                    queue.addLast(curr.right);
                }
            }
            System.out.println();
        }
    }

    /**
     * Return minimum value
     * @return
     */
    public int findMin() {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node<Integer> curr = this.root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.data;
    }

    private Node<Integer> findMinNode(Node<Integer> node) {
        if (node == null) throw new RuntimeException("Node is empty");
        Node<Integer> curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    /**
     * Return maximum value
     * @return
     */
    public int findMax() {
        if (root == null) throw new RuntimeException("Tree is empty");
        Node<Integer> curr = this.root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.data;
    }

    /**
     * Return tree height
     * @return
     */
    public int height() {
        return this.heightRecursive(this.root);
    }

    public int heightRecursive(Node<Integer> node) {
        if (node == null) return 0;
        int leftHeight = this.heightRecursive(node.left);
        int rightHeight = this.heightRecursive(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * Find and return the node that has data
     * @param data
     * @return
     */
    public Node<Integer> findNode(int data) {
        // case 1. root is null
        if (this.root == null) return null;
        // case 2. root is data
        // case 3. data is in one of the child BST
        Node<Integer> curr = this.root;
        while (curr != null) {
            if (curr.data == data) return curr;
            if (data < curr.data) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    /**
     * Delete node with value from BST
     * Case 1
     * Node has no children -> Return null
     *
     * Case 2
     * Node has one child -> Return the child node
     *
     * Case 3
     * Node has two children -> Replace with in-order successor, then delete successor
     *
     * @param data
     */
    public void delete(int data) {
        if (!this.contains(data)) return;
        // case 1. data node has 0 child -> set parent's left/right link to null
        // case 2. data node has 1 child -> if its left/right child, replace child node with data node.
        // case 3. data node has 2 child
        //      a. go to the right child(child_A) of data node
        //      b. go to the left child of child_A (child_B) of data node
        //      c. from child_B, find the rightmost grandchild(child_C)
        //      d. replace the value of child_C with the data node
        //      e. then remove child_C
        this.root = this.deleteRecursive(this.root, data);
    }

    /**
     * Time:
     * - Average: O(logn)
     * - Worst: O(n)
     * - where n is the size of the BST
     * Space: O(h)
     * - where h is the height of the BST, representing the recursive call stack
     * @param node
     * @param data
     * @return
     */
    private Node<Integer> deleteRecursive(Node<Integer> node, int data) {
        if (node == null) return null;
        if (data < node.data) {
            node.left = deleteRecursive(node.left, data);
        } else if (data > node.data) {
            node.right = deleteRecursive(node.right, data);
        } else {
            // node to delete found

            // case 1: No child
            if (node.left == null && node.right == null) {
                return null;
            }

            // case 2: 1 child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // case 3: 2 children
            Node<Integer> successor = this.findMinNode(node.right); // in-order successor
            node.data = successor.data;
            node.right = this.deleteRecursive(node.right, successor.data);
        }
        return node;
    }
}

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree(10);
        Node root = tree.getRoot();
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(7);
        tree.inOrder(root); // Expected: 2 5 7 10 15
        System.out.println(tree.contains(1)); // false
        System.out.println(tree.contains(10)); // true
        System.out.println(tree.contains(15)); // true
        System.out.println(tree.contains(5)); // true
        System.out.println(tree.contains(2)); // true
        System.out.println(tree.contains(7)); // true
        System.out.println(tree.findMin()); // 2
        System.out.println(tree.findMax()); // 15
        System.out.println(tree.height()); // 3
        System.out.println("-----");
        tree.delete(7);
        tree.inOrder(root);
        System.out.println("-----");
        tree.levelOrder();
    }
}
