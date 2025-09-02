package advancedTypes.trees;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SuffixTrie2Node {
    // the edge(key) stores the character representing the child node, the node(value) is the actual representation of the character in the trie
    public Map<Character, SuffixTrie2Node> children;
    // occurrences list in each node is like a map of all starting positions in the text where the path to this node appears.
    // it turns the suffix trie from just "does it exist?" into "where exactly does it appear?"
    // allows for fast substring search, O(m), where m = pattern length
    // useful for substring search
    public List<Integer> occurrences;
    public boolean endOfWord;

    public SuffixTrie2Node(boolean endOfWord) {
        this.children = new HashMap<>();
        this.occurrences = new ArrayList<>();
        this.endOfWord = endOfWord;
    }
}

class SuffixTrie2 {
    private SuffixTrie2Node root;

    public SuffixTrie2(String input) {
        this.root = new SuffixTrie2Node(false);
        String input$ = input + "$";
        this.buildSuffixTrie(input$);
    }

    private void buildSuffixTrie(String input) {
        // loop through the input for processing
        for (int i = 0; i < input.length(); i++) {
            this.insertSuffix(this.root, input, i, i);
        }
    }

    /**
     * Insert the suffix by the word
     * @param node
     * @param word
     * @param index
     */
    private void insertSuffix(SuffixTrie2Node node, String word, int index, int startIndex) {
        // termination
        if (index == word.length()) {
            node.endOfWord = true;
            return;
        }
        // extract the current char
        char c = word.charAt(index);
        // check if current char exists as a child path
        node.children.putIfAbsent(c, new SuffixTrie2Node(false));
        SuffixTrie2Node child = node.children.get(c);
        child.occurrences.add(startIndex);
        this.insertSuffix(child, word, index + 1, startIndex);
    }

    /**
     * Checks if str exists starting from node
     * @param node
     * @param str
     * @return true if node path contains str, false otherwise.
     */
    public boolean containsPrefix(SuffixTrie2Node node, String str) {
        SuffixTrie2Node curr = node;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }

    /**
     * Checks if str exists starting from node AND marks the end of a suffix
     * @param node
     * @param str
     * @return true only if path exists AND marks the end of a suffix
     */
    public boolean containsWord(SuffixTrie2Node node, String str) {
        SuffixTrie2Node curr = node;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        SuffixTrie2Node dollar = curr.children.get('$');
        return dollar != null && dollar.endOfWord;
    }

    public List<Integer> findAll(SuffixTrie2Node node, String str) {
        SuffixTrie2Node curr = node;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!curr.children.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }
        return new ArrayList<>(curr.occurrences);
    }

    public int countOccurrences(SuffixTrie2Node node, String str) {
        SuffixTrie2Node curr = node;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!curr.children.containsKey(c)) {
                return 0;
            }
            curr = curr.children.get(c);
        }
        return curr.occurrences.size();
    }

    public SuffixTrie2Node getRoot() {
        return this.root;
    }
}

public class SuffixTrieDemo2 {
    public static void main(String[] args) {
        SuffixTrie2 trie = new SuffixTrie2("banana");
        System.out.println(trie.containsPrefix(trie.getRoot(), "ana"));   // true
        System.out.println(trie.findAll(trie.getRoot(), "ana"));    // [1, 3]
        System.out.println(trie.countOccurrences(trie.getRoot(), "ana")); // 2
        System.out.println(trie.findAll(trie.getRoot(), "na"));     // [2, 4]
        System.out.println(trie.containsPrefix(trie.getRoot(), "band"));  // false
        System.out.println(trie.findAll(trie.getRoot(), "banana")); // [0]
        System.out.println("--------------------");
        SuffixTrie trie2 = new SuffixTrie("racecar");
        System.out.println(trie2.contains(trie2.getRoot(), "ace"));    // true
        System.out.println(trie2.findAll(trie2.getRoot(), "car"));     // [4]
        System.out.println(trie2.findAll(trie2.getRoot(), "racecar")); // [0]
        System.out.println(trie2.contains(trie2.getRoot(), "racer"));  // false
    }
}
