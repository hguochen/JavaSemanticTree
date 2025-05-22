package advancedTypes.trees;

/**
 * Design a class BinaryTree that supports:
 * 	•	Inserting nodes (in BST order)
 * 	•	Searching for a value
 * 	•	In-order traversal (left → root → right)
 * 	•	Pre-order and post-order traversals (optional)
 * 	•	Deleting a node (optional, stretch goal)
 */
class BinaryTree<T> {
    private Node<T> root;

    public BinaryTree(T data) {
        this.root = new Node<>(data);
    }

    /**
     * Return a reference to the root node.
     * @return
     */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Inserts a child node to the left of the root node.
     * Throws exception if root's left child is already present.
     * @param root
     * @param child
     */
    public Node<T> insertLeft(Node<T> root, Node<T> child) {
        if (root.left != null) throw new RuntimeException("Failed to add node. Node: " + root.toString() + " left " +
                "child is occupied.");
        if (child == null) throw new RuntimeException("Child node cannot be empty");
        root.left = child;
        return root;
    }

    /**
     * Inserts a child node to the right of the root node.
     * Throws exception if root's left child is already present.
     * @param root
     * @param child
     */
    public Node<T> insertRight(Node<T> root, Node<T> child) {
        if (root.right != null) throw new RuntimeException("Failed to add node. Node: " + root.toString() + " right " +
                "child is occupied.");
        if (child == null) throw new RuntimeException("Child node cannot be empty");
        root.right = child;
        return root;
    }

    /**
     * Searches for a node with the given value. Returns true if found, false otherwise
     * @param data
     * @return
     */
    public boolean contains(T data) {
        return this.containsRecursive(this.root, data);
    }

    /**
     * Recursively find the node that contains data.
     * @param node
     * @param data
     * @return
     */
    private boolean containsRecursive(Node<T> node, T data) {
        if (node == null) return false;
        if (node.data == data) return true;
        return this.containsRecursive(node.left, data) || this.containsRecursive(node.right, data);
    }

    /**
     * In order traversal: left -> root -> right
     */
    public void inOrder(Node<T> node) {
        if (node == null) return;
        if (node.left != null) this.inOrder(node.left);
        System.out.println("Visiting node: " + node.toString());
        if (node.right != null) this.inOrder(node.right);
    }

    /**
     * Preorder traversal: root -> left -> right
     */
    public void preOrder(Node<T> node) {
        if (node == null) return;
        System.out.println("Visiting node: " + node.toString());
        if (node.left != null) this.preOrder(node.left);
        if (node.right != null) this.preOrder(node.right);
    }

    /**
     * Postorder traversal: left -> right -> root
     */
    public void postOrder(Node<T> node) {
        if (node == null) return;
        if (node.left != null) this.postOrder(node.left);
        if (node.right != null) this.postOrder(node.right);
        System.out.println("Visiting node: " + node.toString());
    }

    /**
     * Find and return the parent of the node with data as value
     * @param data
     * @return
     */
    public Node<T> find(T data) {
        if (this.root.data == data) return this.root;
        return findRecursive(this.root, data);
    }

    private Node<T> findRecursive(Node<T> node, T data) {
        if (node == null) return null;
        if (node.left != null && node.left.data == data) return node;
        if (node.right != null && node.right.data == data) return node;
        if (node.left != null) return this.findRecursive(node.left, data);
        if (node.right != null) return this.findRecursive(node.right, data);
        return null;
    }

    /**
     * Return the height(depth) of the tree
     * @return
     */
    public int height(Node<T> node) {
        if (node == null) return 0;
        int leftHeight = this.height(node.left);
        int rightHeight = this.height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree(1);
        Node<Integer> root = bt.getRoot();
        bt.insertLeft(root, new Node(2));
        bt.insertRight(root, new Node(3));
        bt.inOrder(root);
        bt.preOrder(root);
        bt.postOrder(root);
        System.out.println(bt.contains(1));
        System.out.println(bt.contains(2));
        System.out.println(bt.contains(3));
        System.out.println(bt.contains(4));
        System.out.println(bt.find(1));
        System.out.println(bt.find(2));
        System.out.println(bt.find(3));

        System.out.println(bt.height(root)); // 2
        System.out.println(bt.height(root.left)); // 1
        System.out.println(bt.height(root.right)); // 1
    }
}
