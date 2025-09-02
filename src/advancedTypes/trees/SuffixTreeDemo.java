package advancedTypes.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Store edge labels as index ranges into the original string S(don't copy substrings).
 */
class Edge {
    // inclusive index into S
    public int start;
    // inclusive index into S
    public int end;
    // child node
    public SuffixNode to;
}

class SuffixNode {
    // outgoing edges keyed by first char
    Map<Character, Edge> next;
    // for leaves, store suffixStart to know which suffix this leaf represents
    int suffixStart = -1; // -1 for internal nodes
}

class SuffixTree {
    private SuffixNode root;

    /**
     * builds suffix tree for s + '$'
     * @param s
     */
    public SuffixTree(String s) {
        this.root = new SuffixNode();
    }

    public boolean contains(String p) {
        // TODO
        return false;
    }

    public List<Integer> findAll(String p) {
        // TODO
        return new ArrayList<Integer>();
    }

}

public class SuffixTreeDemo {
    public static void main(String[] args) {

    }
}
