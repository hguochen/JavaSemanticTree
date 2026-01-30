package advancedTypes.linkedLists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SkipList {
    private static class Node {
        final int value;
        final Node[] forward;

        Node(int value, int level) {
            this.value = value;
            this.forward = new Node[level];
        }

        public int level() {
            return this.forward.length;
        }
    }
    private final int MAX_LEVEL = 32;
    private final double probability = 0.5;
    private final Random rand = new Random();
    private final Node head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
    // highestLevel represents the COUNT of level, NOT the max index
    private int highestLevel = 0;
    private int size = 0;

    public int size() {
        return this.size;
    }

    /**
     * Determines the height of the new node in the skip list.
     * level is 1-based, while the underlying array is 0-based. so we must do level - 1 when we actually reference the array index.
     */
    private int randomLevel() {
        int level = 1;

        while (rand.nextDouble() < probability && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    /**
     * Search if x is in the skip list.
     * @param x value to search for
     * @return true if x exists, false otherwise
     */
    public boolean contains(int x) {
        if (this.size == 0) return false;
        return this.containsRecursive(this.head, this.highestLevel - 1, x);
    }

    /**
     * Traverse the list from top left to bottom right in search for value x.
     * @param node current node to search for
     * @param level current forward level in the node to traverse
     * @param x value to search for
     * @return true if value x is found, false otherwise
     */
    private boolean containsRecursive(Node node, int level, int x) {
        // 1. if node reached the end, x is not found
        if (node == null) return false;
        // 2. move horizontally while current < x
        while (node.forward[level] != null && node.forward[level].value < x) {
            node = node.forward[level];
        }
        // 3. check equality
        if (node.forward[level] != null && node.forward[level].value == x) return true;
        // 4. if at bottom level, stop
        if (level == 0) return false;
        // 5. drop down on level
        return this.containsRecursive(node, level - 1, x);
    }

    /**
     * Add x into the skip list.
     * Insert algorithm:
     * 1. walk down the skip list
     * 2. fill the update[] with the last node before x on each level
     * 3. generate a new random level
     * 4. insert the node by rewiring forward pointers
     * 5. update the highest level if needed
     * 6. increment size
     *
     * Time: O(logn)
     * @param x
     * @return true if x added successfully, false otherwise
     */
    public boolean add(int x) {
        // update[level] = the last node at that level whose level is < x
        // this is where new node should be inserted at that level
        Node[] update = new Node[MAX_LEVEL];
        Node current = head;
        int level = this.highestLevel - 1;

        // fill update array with all the nodes visited before we potentially reach the place to insert x
        while (level >= 0) {
            while (current.forward[level] != null && current.forward[level].value < x) {
                current = current.forward[level];
            }
            update[level] = current;
            level -= 1;
        }
        // ensure update[0] is valid, especially for first insertion
        if (update[0] == null) {
            update[0] = this.head;
        }
        // check if node with value x already exists. ie. prevent duplicates
        Node next = update[0].forward[0];
        if (next != null && next.value == x) return false;

        // create new node
        int newLevel = this.randomLevel();
        if (newLevel > this.highestLevel) {
            // if the skip list grows in height, we update the head node pointer
            for (int i = this.highestLevel; i < newLevel; i++) {
                update[i] = this.head;
            }
            this.highestLevel = newLevel;
        }
        Node newNode = new Node(x, newLevel);

        // for each level in newNode.forward.length, we must "splice" the node into each linked list level
        for (int i = 0; i < newNode.forward.length; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
        this.size += 1;
        return true;
    }

    /**
     * Remove x from the skip list.
     * Remove algorithm:
     * 1. search from the top level down
     * 2. record all predecessors at each level in an update[] array
     * 3. when we reach the bottom level:
     *      - check if x actually exists, if not -> return false
     * 4. if exists -> for each level where the target node appears
     *      - review: update[level].forward[level] = target.forward[level]
     * 5. if highest level becomes empty -> reduce highestLevel
     * 6. reduce size
     *
     * Time: O(logn)
     * @param x
     * @return true if x successfully removed, false otherwise.
     */
    public boolean remove(int x) {
        // contains all the predecessors for which we need to update the pointers
        Node[] update = new Node[MAX_LEVEL];
        int level = this.highestLevel - 1; // offset array index level
        Node current = this.head;

        // record predecessors
        while (level >= 0) {
            while (current.forward[level] != null && current.forward[level].value < x) {
                current = current.forward[level];
            }
            update[level] = current;
            level -= 1;
        }
        // remove from empty list
        if (update[0] == null) return false;

        // x does not exist in skip list
        Node candidate = update[0].forward[0];
        if (candidate == null || candidate.value != x) return false;

        // update each predecessor's pointer to point to candidate's pointer
        for (int i = 0; i < candidate.level(); i++) {
            if (update[i].forward[i] == candidate) {
                update[i].forward[i] = candidate.forward[i];
            }
        }
        // topmost level becomes empty, we reduce the level of skiplist
        while (this.highestLevel > 1 && this.head.forward[this.highestLevel - 1] == null) {
            this.highestLevel -= 1;
        }
        this.size -= 1;
        return true;
    }

    /**
     * Search for all values that's more than L and less than R
     * @param L lower bound value
     * @param R upper bound value
     * @return list of values within both bounds
     */
    public List<Integer> rangeSearch(int L, int R) {
        List<Integer> result = new ArrayList<>();

        // 1. find first node >= L
        Node current = this.head;
        for (int level = this.highestLevel - 1; level >= 0; level--) {
            while (current.forward[level] != null && current.forward[level].value < L) {
                current = current.forward[level];
            }
        }

        // drop to level 0
        current = current.forward[0];

        // 2. collect values <= R
        while (current != null && current.value <= R) {
            result.add(current.value);
            current = current.forward[0];
        }
        return result;
    }

    /**
     * Print the skip list level by level (top-down).
     * Example:
     * Level 3:  10 ----> 30
     * Level 2:  5 ----> 10 ----> 30
     * Level 1:  5 ----> 7 ----> 10 ----> 20 ----> 30
     */
    public void printLevels() {
        System.out.println("\n=== Skip List Levels ===");
        if (this.size == 0) {
            System.out.println("(empty)");
            return;
        }

        // highestLevel is the count of levels — e.g., if highestLevel == 3,
        // valid levels are 0,1,2 ; top is level 2.
        for (int lvl = this.highestLevel - 1; lvl >= 0; lvl--) {
            Node curr = head;
            System.out.print("Level " + lvl + ":  ");

            while (curr.forward[lvl] != null) {
                curr = curr.forward[lvl];
                System.out.print(curr.value + " ----> ");
            }
            System.out.println("null");
        }
        System.out.println("==========================\n");
    }
}

public class SkipListDemo {
    public static void main(String[] args) {
        SkipList list = new SkipList();

        System.out.println("=== Test A: Remove from empty list ===");
        System.out.println(list.remove(10)); // false
        list.printLevels();

        System.out.println("=== Test B: Insert some items ===");
        list.add(5);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.printLevels();
        System.out.println(list.contains(5)); // true
        System.out.println(list.contains(10)); // true
        System.out.println(list.contains(20)); // true
        System.out.println(list.contains(30)); // true
        System.out.println(list.contains(40)); // true
        System.out.println(list.contains(100)); // false

        System.out.println("=== Test C: Remove head element (5) ===");
        System.out.println(list.remove(5));  // true
        System.out.println("Contains 5? " + list.contains(5)); // false
        list.printLevels();

        System.out.println("=== Test D: Remove middle element (20) ===");
        System.out.println(list.remove(20)); // true
        System.out.println("Contains 20? " + list.contains(20)); // false
        list.printLevels();

        System.out.println("=== Test E: Remove tail element (40) ===");
        System.out.println(list.remove(40)); // true
        System.out.println("Contains 40? " + list.contains(40)); // false
        list.printLevels();

        System.out.println("=== Test F: Remove element that does not exist ===");
        System.out.println(list.remove(999)); // false
        System.out.println(list.remove(-100)); // false
        list.printLevels();

        System.out.println("=== Test G: Remove last existing elements ===");
        System.out.println(list.remove(10)); // true
        System.out.println(list.remove(30)); // true
        System.out.println("Size: " + list.size()); // 0
        list.printLevels();

        System.out.println("=== Test H: Ensure skiplist still works after clearing ===");
        list.add(7);
        list.add(1);
        list.add(50);
        list.printLevels();
        System.out.println("Remove 1? " + list.remove(1));
        list.printLevels();

        System.out.println("=== Test I: Stress test with sequential insert + remove ===");
        for (int i = 1; i <= 10; i++) list.add(i);
        list.printLevels();
        for (int i = 1; i <= 10; i++) {
            System.out.println("Remove " + i + ": " + list.remove(i));
        }
        System.out.println("Size: " + list.size());
        list.printLevels();
    }
}
