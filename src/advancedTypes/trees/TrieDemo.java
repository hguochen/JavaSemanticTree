package advancedTypes.trees;

import java.util.*;

class TrieNode {
    public static int ALPHABET_COUNT = 26;
    public boolean endOfWord;
    public Map<Character, TrieNode> children;

    public TrieNode(boolean endOfWord) {
        children = new HashMap<>();
        this.endOfWord = endOfWord;
    }

    public Map<Character, TrieNode> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        return "TrieNode{" + "endOfWord=" + endOfWord + ", children=" + children + '}';
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode(false);
    }

    /**
     * Insert a word into the trie.
     *
     * Time: O(L)
     * - where L is the length of the word
     * @param word
     */
    public void insert(String word) {
        String sanitizedWord = word.toLowerCase(Locale.ROOT).trim();
        if (word.isEmpty()) return;
        TrieNode curr = this.root;
        for (int i = 0; i < word.length(); i++) {
            char alphabet = sanitizedWord.charAt(i);
            Map<Character, TrieNode> children = curr.getChildren();
            children.putIfAbsent(alphabet, new TrieNode(false));
            curr = children.get(alphabet);
        }
        curr.endOfWord = true;
    }

    /**
     * Find out if Trie has the word.
     * Time: O(L)
     * - where L is the length of the word.
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        String sanitizedWord = word.toLowerCase(Locale.ROOT).trim();
        TrieNode node = getNode(sanitizedWord);
        return node.endOfWord;
    }

    /**
     * Find out if Trie has given string prefix.
     * Time: O(L)
     * - where L is the length of the word.
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return this.getNode(prefix) != null;
    }

    /**
     * To delete a word:
     * - you unmark endOfWord = true if it exists
     * - then prune empty nodes recursively if they are no longer part of another word
     * @param word
     * @return
     */
    public boolean delete(String word) {
        String sanitizedWord = word.toLowerCase(Locale.ROOT).trim();
        return this.deleteRecursive(this.root, sanitizedWord, 0);
    }

    private boolean deleteRecursive(TrieNode curr, String str, int index) {
        if (index == str.length()) {
            if (!curr.endOfWord) {
                return false; // word not found
            }
            curr.endOfWord = false; // unmark end of word
            return curr.getChildren().isEmpty(); // prune if no children
        }
        char alphabet = str.charAt(index);
        Map<Character, TrieNode> children = curr.getChildren();
        TrieNode next = children.get(alphabet);
        if (next == null) {
            return false; // character path doesn't exist
        }
        boolean shouldDeleteChild = this.deleteRecursive(next, str, index + 1);
        if (shouldDeleteChild) {
            children.remove(alphabet); // delete the mapping to child
            // Prune current node if it has no children and is not end of another word
            return children.isEmpty() && !curr.endOfWord;
        }
        return false; // Either child wasn't deleted, or node must be preserved
    }

    /**
     * Get all the words starting with 'prefix'.
     * Time: O(n^h)
     * In practice, O(K) where K is the total number of characters in all matching words. but O(n^h) is the upper bound
     * - where n is the number of child nodes at each level
     * - where h is the height of the trie
     * Space: O(n^h)
     * @param prefix
     * @return
     */
    public List<String> getAllWordsStartingWith(String prefix) {
        String sanitizedPrefix = prefix.toLowerCase(Locale.ROOT).trim();
        List<String> results = new ArrayList<>();
        // get the node at which prefix ends.
        // ie. we start searching from this node for all words
        TrieNode node = this.getNode(sanitizedPrefix);
        if (node == null) return results;
        this.dfsCollect(node, sanitizedPrefix, results);
        return results;
    }

    /**
     * Depth first search to collect all the words starting with 'str' into results list.
     *
     * Time: O(n^h)
     * - where n is the number of child nodes at each level
     * - where h is the height of the trie
     * Space: O(n^h)
     * @param str
     * @param results
     */
    private void dfsCollect(TrieNode node, String str, List<String> results) {
        if (node == null) return;
        if (node.endOfWord) {
            results.add(str);
        }
        Map<Character, TrieNode> children = node.getChildren();
        for(Character c : children.keySet()) {
            this.dfsCollect(children.get(c), str + c.toString(), results);
        }
    }

    private TrieNode getNode(String str) {
        if (str.isEmpty()) return null;
        TrieNode curr = this.root;
        for (char c : str.toCharArray()) {
            Map<Character, TrieNode> children = curr.getChildren();
            if (!children.containsKey(c)) return null;
            curr = children.get(c);
        }
        return curr;
    }

    public void print() {
        System.out.println(this.root);
    }

    @Override
    public String toString() {
        return "Trie{" + "root=" + root + '}';
    }
}

public class TrieDemo {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));;     // returns false
        System.out.println(trie.startsWith("app"));; // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));;     // returns true

        System.out.println(trie.getAllWordsStartingWith("app"));
    }
}
