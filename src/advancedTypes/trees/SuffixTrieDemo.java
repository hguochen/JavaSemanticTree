package advancedTypes.trees;

import java.util.*;

class SuffixTrieNode {
    // next node
    public Map<Character, SuffixTrieNode> next;
    // store indices where substring occurs
    public List<Integer> occurrences;
    // true if current node is end of a word, false otherwise
    public boolean endOfWord;

    public SuffixTrieNode() {
        this.next = new HashMap<>();
        this.occurrences = new ArrayList<>();
        this.endOfWord = false;
    }
}

/**
 * A Suffix Trie data structure for efficient substring search.
 * <p>
 * This implementation builds a trie containing all suffixes of the input text
 * with a unique terminal symbol ('$') appended to ensure that no suffix
 * is a prefix of another. Each node maintains a list of starting indices
 * where the substring represented by the path to that node occurs.
 * <p>
 * Complexity:
 * <ul>
 *   <li>Build time: O(n^2) in the worst case, where n is the length of the input text</li>
 *   <li>Space: O(n^2) nodes in the worst case (uncompressed trie)</li>
 *   <li>Search (contains/findAll): O(m), where m is the length of the pattern</li>
 * </ul>
 */
class SuffixTrie {
    private SuffixTrieNode root;

    /**
     * Constructs a suffix trie for the given input text.
     * A unique terminal symbol ('$') is appended to the text during construction.
     *
     * @param text the input string to build the suffix trie from
     * @throws IllegalArgumentException if {@code text} is null
     */
    public SuffixTrie(String text) {
        this.buildSuffixTrie(text);
    }

    /**
     * Builds the suffix trie from the given text by inserting
     * every suffix of the text plus a unique terminal symbol ('$').
     *
     * Time: O(n^2), where n is the size of input text
     * Space: O(n^2) worst case, uncompressed trie
     *
     * @param text the input string
     */
    private void buildSuffixTrie(String text) {
        this.root = new SuffixTrieNode();
        String text$ = text + "$";
        for (int i = 0; i < text$.length(); i++) {
            this.insertSuffix(this.root, text$, i);
        }
    }

    /**
     * Inserts a single suffix of the given text, starting at {@code startIndex},
     * into the suffix trie rooted at {@code root}.
     * <p>
     * Each traversed node along the insertion path records {@code startIndex}
     * in its occurrence list.
     *
     * @param root       the root of the suffix trie
     * @param text       the input string with terminal symbol
     * @param startIndex the starting index of the suffix being inserted
     */
    private void insertSuffix(SuffixTrieNode root, String text, int startIndex) {
        SuffixTrieNode node = root;
        for (int i = startIndex; i < text.length(); i++) {
            char c = text.charAt(i);
            node.next.putIfAbsent(c, new SuffixTrieNode());
            node = node.next.get(c);
            node.occurrences.add(startIndex);
        }
        node.endOfWord = true;
    }

    /**
     * Prints the structure of the suffix trie to standard output,
     * showing characters and marking suffix-end nodes.
     */
    public void printTrie() {
        printTrie(this.root, 0);
    }

    /**
     * Determines whether the given pattern exists as a substring
     * of the text used to build this suffix trie.
     *
     * @param root    the root node of the suffix trie
     * @param pattern the substring to search for
     * @return {@code true} if the pattern exists in the text, {@code false} otherwise
     */
    public boolean contains(SuffixTrieNode root, String pattern) {
        SuffixTrieNode curr = root;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!curr.next.containsKey(c)) {
                return false;
            }
            curr = curr.next.get(c);
        }
        return true;
    }

    /**
     * Finds all starting indices of occurrences of the given pattern
     * in the text used to build this suffix trie.
     *
     * @param root    the root node of the suffix trie
     * @param pattern the substring to search for
     * @return a list of starting indices where the pattern occurs;
     *         empty list if the pattern does not exist
     */
    public List<Integer> findAll(SuffixTrieNode root, String pattern) {
        SuffixTrieNode curr = root;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!curr.next.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.next.get(c);
        }
        return curr.occurrences;
    }


    /**
     * Counts how many times the given pattern occurs in the text
     * used to build this suffix trie.
     *
     * @param root    the root node of the suffix trie
     * @param pattern the substring to search for
     * @return the number of occurrences of the pattern
     */
    public int countOccurrences(SuffixTrieNode root, String pattern) {
        SuffixTrieNode curr = root;
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!curr.next.containsKey(c)) {
                return 0;
            }
            curr = curr.next.get(c);
        }
        return curr.occurrences.size();
    }


    /**
     * Recursive helper for {@link #printTrie()}.
     *
     * @param node  the current trie node
     * @param depth the depth in the trie (used for indentation)
     */
    private void printTrie(SuffixTrieNode node, int depth) {
        if (node == null) return;

        // Stable order helps debugging
        List<Character> keys = new ArrayList<>(node.next.keySet());
        Collections.sort(keys);

        for (Character c : keys) {
            for (int i = 0; i < depth; i++) System.out.print("  "); // two spaces per level
            SuffixTrieNode child = node.next.get(c);
            System.out.println(c + (child.endOfWord ? " [*]" : ""));
            printTrie(child, depth + 1);
        }
    }

    public SuffixTrieNode getRoot() {
        return this.root;
    }
}

public class SuffixTrieDemo {
    public static void main(String[] args) {
        SuffixTrie trie = new SuffixTrie("banana");
        System.out.println(trie.contains(trie.getRoot(), "ana"));   // true
        System.out.println(trie.findAll(trie.getRoot(), "ana"));    // [1, 3]
        System.out.println(trie.countOccurrences(trie.getRoot(), "ana")); // 2
        System.out.println(trie.findAll(trie.getRoot(), "na"));     // [2, 4]
        System.out.println(trie.contains(trie.getRoot(), "band"));  // false
        System.out.println(trie.findAll(trie.getRoot(), "banana")); // [0]
        System.out.println("--------------------");
        SuffixTrie trie2 = new SuffixTrie("racecar");
        System.out.println(trie2.contains(trie2.getRoot(), "ace"));    // true
        System.out.println(trie2.findAll(trie2.getRoot(), "car"));     // [4]
        System.out.println(trie2.findAll(trie2.getRoot(), "racecar")); // [0]
        System.out.println(trie2.contains(trie2.getRoot(), "racer"));  // false
    }
}
