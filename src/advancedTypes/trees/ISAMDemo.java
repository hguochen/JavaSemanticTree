package advancedTypes.trees;

import java.util.*;

/**
 * Implementation of Index Sequential Access Methods data structure.
 *
 * Constraints
 *      - fixed page size, fixed max records per page
 *      - record(int key, value) // value can be string or int
 *      - keep everything in memory
 */
class ISAM {
    private static final int DATA_PAGE_SIZE = 4;
    private static final int OVERFLOW_PAGE_SIZE = 4;

    private static class Record implements Comparable<Record> {
        int key;
        int value;

        Record(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Record b) {
            return Integer.compare(this.key, b.key);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Record record = (Record) o;
            return key == record.key && value == record.value;
        }
    }

    private static class DataPage {
        List<Record> records; // sorted list of records
        OverflowPage overflow; // pointer to first overflow page

        DataPage() {
            this.records = new ArrayList<>();
            this.overflow = null;
        }

        public void insert(int key, int value) {
            Record record = new Record(key, value);

            // 1. insert into primary page if space available
            if (this.records.size() < DATA_PAGE_SIZE) {
                this.records.add(record);
                this.records.sort(Comparator.naturalOrder());
                return;
            }
            // 2. primary page is full -> walk overflow chain
            if (this.overflow == null) {
                this.overflow = new OverflowPage();
                this.overflow.insert(record);
                return;
            }

            OverflowPage prev = null;
            OverflowPage current = this.overflow;

            // walk to find first non-null overflow page
            while (current != null && current.isFull()) {
                prev = current;
                current = current.next;
            }

            if (current == null) {
                // reached end of chain, append new page
                OverflowPage newPage = new OverflowPage();
                newPage.insert(record);
                prev.next = newPage;
            } else {
                // found a page with space
                current.insert(record);
            }
        }
    }

    private static class OverflowPage {
        List<Record> overflowRecords; // list of overflow records
        OverflowPage next; // next overflow page

        OverflowPage() {
            this.overflowRecords = new ArrayList<>();
            this.next = null;
        }

        public void insert(Record record) {
            this.overflowRecords.add(record);
            this.overflowRecords.sort(Comparator.naturalOrder());
        }

        public boolean isFull() {
            return this.overflowRecords.size() == OVERFLOW_PAGE_SIZE;
        }
    }

    private static class IndexPage {
        private static final int FAN_OUT = 3; // 2 keys, 3 children max
        List<Integer> keys; // separator keys
        List<Object> children; // IndexPage or DataPage
        boolean isLeafIndex;

        IndexPage(boolean isLeafIndex) {
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
            this.isLeafIndex = isLeafIndex;
        }

        public boolean isFull() {
            return this.keys.size() == FAN_OUT - 1;
        }
    }

    private List<DataPage> dataPages; // primary storage
    private IndexPage rootIndex; // root of multi-level index

    ISAM() {
        this.dataPages = new ArrayList<>();
        this.rootIndex = null;
    }

    /**
     * Builds the ISAM structure from a sorted array of keys.
     *
     * <p>This method constructs the entire static ISAM layout:
     * <ol>
     *     <li><b>Primary storage (DataPages)</b> – The sorted input keys are packed
     *         into fixed-size {@code DataPage} blocks. Each DataPage may later
     *         contain an overflow chain.</li>
     *
     *     <li><b>First-level index (leaf index)</b> – One {@code IndexPage} is created
     *         that directly indexes all DataPages. The page contains:
     *         <ul>
     *             <li>{@code children}: all DataPages in order</li>
     *             <li>{@code keys}: separator keys, one per DataPage except the last.
     *                 For a DataPage Pᵢ, the separator is {@code max(Pᵢ)}</li>
     *         </ul>
     *         This allows binary-search-style routing into the correct DataPage.</li>
     *
     *     <li><b>Higher-level index pages</b> – If the first-level index has more keys
     *         than allowed by the fixed {@code FAN_OUT}, additional index layers
     *         are built. Nodes are grouped in chunks of {@code FAN_OUT} children,
     *         and each chunk becomes a new IndexPage. This process iterates until
     *         a single root index remains.</li>
     * </ol>
     *
     * <p>After this method completes, the ISAM structure is fully initialized and
     * the {@code rootIndex} field refers to the top-most IndexPage.
     *
     * @param sortedRecords an array of keys in strictly sorted ascending order.
     *                      The build process assumes sorted input (as in classical ISAM).
     */
    public void build(int[] sortedRecords) {
        if (sortedRecords.length < 1) return;

        // 1. break records into fixed size DataPages
        DataPage dataPage = null;
        for (int i = 0; i < sortedRecords.length; i++) {
            if (i % DATA_PAGE_SIZE == 0) {
                // create new page every DATA_PAGE_SIZE records
                dataPage = new DataPage();
                this.dataPages.add(dataPage);
            }
            dataPage.insert(sortedRecords[i], sortedRecords[i]);
        }

        // 2. build level 1 IndexPage
        // 2a. loop through data pages
        // 2b. extract max key from each
        // 2c. build an IndexPage with:
        //      - keys = [k0, k1, k2, ...]
        //      - children = [c0, c1, c2, ...]
        IndexPage firstLevel = new IndexPage(true);
        // add children (data pages)
        for (DataPage page : this.dataPages) {
            firstLevel.children.add(page);
        }
        // add separator keys: one per child except the last
        for (int i = 0; i < this.dataPages.size() - 1; i++) {
            DataPage dp = this.dataPages.get(i);
            int maxKey = dp.records.get(dp.records.size() - 1).key;
            firstLevel.keys.add(maxKey);
        }

        // 3. build higher level IndexPage until 1 root remains
        IndexPage current = firstLevel;
        while (current.keys.size() > IndexPage.FAN_OUT - 1) {
            current = this.buildNextLevel(current);
        }
        this.rootIndex = current;
    }

    /**
     * Constructs the next higher index level above the given IndexPage.
     *
     * <p>This method groups the children of {@code current} into chunks of size
     * {@code FAN_OUT}. Each chunk becomes a new IndexPage in the parent level.
     * The separator keys for each new IndexPage are built using:
     *
     * <pre>
     * keys = [ max(child₀), max(child₁), ..., max(childₙ₋₂) ]
     * </pre>
     *
     * where {@code max(childᵢ)} is the maximum key reachable within that subtree.
     *
     * <p>After all child IndexPages are created for the new parent level, the
     * parent’s own separator keys are generated. For a parent with children:
     *
     * <pre>
     * [N0, N1, N2, ..., Nk]
     * </pre>
     *
     * the separator keys become:
     *
     * <pre>
     * [ max(N0), max(N1), ..., max(N(k-1)) ]
     * </pre>
     *
     * <p>This process mirrors B-tree index construction, but because ISAM is
     * static (built once, not dynamically updated), all index levels are built
     * bottom-up and remain immutable afterward.
     *
     * @param current an index level whose size exceeds the allowed
     *                {@code FAN_OUT - 1} separator keys.
     * @return a newly constructed parent IndexPage that indexes the {@code current}
     *         level. This may itself require further promotion if it becomes oversized.
     */
    private IndexPage buildNextLevel(IndexPage current) {
        // newLevel is the parent level we are building
        IndexPage newLevel = new IndexPage(false);

        // group children into balanced groups
        int N = current.children.size();
        int groups = (int) Math.ceil((double) N / IndexPage.FAN_OUT);
        int groupSize = (int) Math.ceil((double) N / groups);
        int idx = 0;
        for (int g = 0; g < groups && idx < N; g++) {
            IndexPage node = new IndexPage(false); // this becomes one child of newLevel

            int end = Math.min(idx + groupSize, N);
            for (int j = idx; j < end; j++) {
                Object child = current.children.get(j);
                node.children.add(child);
            }

            // 2) build node.keys as separators (size = node.children.size() - 1)
            // for routing, we store "max key" of each child except the last child
            // eg. children [c0, c1, c2] => keys [max(c0), max(c1)]
            for (int k = 0; k < node.children.size() - 1; k++) {
                int separator = this.maxKeyOfChild(node.children.get(k));
                node.keys.add(separator);
            }
            newLevel.children.add(node);

            idx = end;
        }

        // 5) build newLevel.keys as separators between newLevel.children
        // children [n0, n1, n2] => keys [max(n0), max(n1)]
        newLevel.keys.clear();
        for (int i = 0; i < newLevel.children.size() - 1; i++) {
            IndexPage childNode = (IndexPage) newLevel.children.get(i);
            int separator = this.maxKeyOfIndexNode(childNode);
            newLevel.keys.add(separator);
        }

        return newLevel;
    }

    private int maxKeyOfChild(Object child) {
        if (child instanceof DataPage) {
            DataPage dp = (DataPage) child;
            return dp.records.get(dp.records.size() - 1).key;
        }
        if (child instanceof IndexPage) {
            IndexPage ip = (IndexPage) child;
            return this.maxKeyOfIndexNode(ip);
        }

        throw new IllegalArgumentException("Unknown child type: " + child.getClass());
    }

    private int maxKeyOfIndexNode(IndexPage node) {
        Object rightmost = node.children.get(node.children.size() - 1);
        return this.maxKeyOfChild(rightmost);
    }

    /**
     * Point search
     *
     * Algorithm:
     * 1. start at rootIndex.
     * 2. at each IndexPage:
     *      - do a binary search on keys
     *      - choose the correct child pointer
     * 3. continue until reaching a DataPage
     * 4. binary search the primary page
     * 5. if not found, walk the overflow chain
     * @return
     */
    public Integer getKey(int key) {
        if (rootIndex == null) return null;

        DataPage page = this.descendIndex(this.rootIndex, key);
        if (page == null) return null;

        // search primary block
        Integer result = this.searchRecords(page.records, key);
        if (result != null) return result;

        // search overflow pages
        OverflowPage op = page.overflow;
        while (op != null) {
            Integer res = this.searchRecords(op.overflowRecords, key);
            if (res != null) return res;
            op = op.next;
        }
        return null;
    }

    private DataPage descendIndex(IndexPage node, int key) {
        if (node.isLeafIndex) {
            // children are DataPage objects
            return (DataPage) this.routeToChild(node, key);
        }
        // children are IndexPage objects -> recurse
        Object child = this.routeToChild(node, key);
        // case 1: reached a DataPage
        if (child instanceof DataPage) {
            return (DataPage) child;
        }
        // case 2: internal index node
        return descendIndex((IndexPage) child, key);
    }

    private <T> T routeToChild(IndexPage node, int key) {
        int i = 0;
        while (i < node.keys.size() && node.keys.get(i) < key) {
            i += 1;
        }
        return (T) node.children.get(i);
    }

    private Integer searchRecords(List<Record> list, int key) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Record r = list.get(mid);
            if (r.key == key) return r.value;
            if (r.key < key) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    /**
     * Range search
     * @param low
     * @param high
     * @return
     */
    public List<Integer> rangeScan(int low, int high) {
        List<Integer> out = new ArrayList<>();
        if (rootIndex == null) return out;
        if (low > high) return out;

        DataPage page = this.findStartPageForRange(this.rootIndex, low);
        if (page == null) return out;

        boolean done = false;
        while (page != null && !done) {
            // 1) scan primary records (sorted)
            for (Record r : page.records) {
                if (r.key < low) continue;
                if (r.key > high) {
                    done = true;
                    break;
                }
                out.add(r.value);
            }

            // 2) scan overflow chain (do NOT early-stop globally; chain ordering is not guaranteed)
            OverflowPage op = page.overflow;
            while(op != null) {
                for (Record r : op.overflowRecords) {
                    if (r.key >= low && r.key <= high) {
                        out.add(r.value);
                    }
                }
                op = op.next;
            }

            // 3) move to the next data page if we haven't exceeded 'high'
            if (!done) {
                page = this.nextDataPage(page);

                // Optional fast-stop: if the next page's smallest primary key is already > high,
                // then no later primary pages can contribute (data pages are built in sorted order).
                if (page != null && !page.records.isEmpty() && page.records.get(0).key > high) {
                    // Still safe to stop for primary pages; overflow semantics depend on your insert policy.
                    done = true;
                }
            }
        }
        return out;
    }

    private DataPage findStartPageForRange(IndexPage node, int low) {
        if (node.isLeafIndex) {
            DataPage dp = (DataPage) this.routeToChild(node, low);

            // dp is the first page whose maxKey >= low
            int dpMax = this.maxKeyOfChild(dp);
            if (dpMax >= low) return dp;

            // edge case: routed to a page whose maxKey < low
            // go to next page if available
            int idx = this.dataPages.indexOf(dp);
            if (idx + 1 < this.dataPages.size()) {
                return this.dataPages.get(idx + 1);
            }
            return null;
        }
        // internal node -> recurse down index
        Object child = this.routeToChild(node, low);
        if (child instanceof DataPage) return (DataPage) child;
        return this.findStartPageForRange((IndexPage) child, low);
    }

    private DataPage nextDataPage(DataPage current) {
        if (current == null) return null;
        int idx = this.dataPages.indexOf(current);
        if (idx < 0) return null;
        int next = idx + 1;
        if (next >= this.dataPages.size()) return null;
        return this.dataPages.get(next);
    }

    /**
     * Insert key into ISAM.
     *
     * ISAM insertion is NOT like B-trees:
     *  - We NEVER split data pages
     *  - We NEVER change the index structure
     *  - All new records go to the overflow chain
     *
     * To restore full structure, caller must run rebuild().
     */
    public void insert(int key) {
        if (this.rootIndex == null) {
            // build a 1-page ISAM if empty
            this.build(new int[] {key});
            return;
        }
        // 1. route to correct data page
        DataPage page = this.descendIndex(this.rootIndex, key);

        // 2. insert into primary or overflow pages
        page.insert(key, key);
    }

}

public class ISAMDemo {
    public static void main(String[] args) {
        ISAM isam = new ISAM();

        // Example sorted keys (size chosen to span multiple pages)
        int[] sorted = {3, 7, 9, 14,   // page 0
                15, 18, 21, 25, // page 1
                30, 32, 40, 41, // page 2
                50, 55, 60, 70  // page 3
        };

        System.out.println("=== Building ISAM Structure ===");
        isam.build(sorted);

        System.out.println("\n=== Data Pages ===");
        printDataPages(isam);

        System.out.println("\n=== Index Structure (Top Down) ===");
        printIndex(isam);

        System.out.println("\n=== Search keys ===");
        System.out.println("3 == " + isam.getKey(3));
        System.out.println("9 == " + isam.getKey(9));
        System.out.println("18 == " + isam.getKey(18));
        System.out.println("25 == " + isam.getKey(25));
        System.out.println("41 == " + isam.getKey(41));
        System.out.println("55 == " + isam.getKey(55));
        System.out.println("60 == " + isam.getKey(60));

        System.out.println(isam.rangeScan(31, 65));
    }

    // -----------------------------
    // PRINT HELPERS
    // -----------------------------

    private static void printDataPages(ISAM isam) {
        try {
            // reflectively access private field dataPages
            var field = ISAM.class.getDeclaredField("dataPages");
            field.setAccessible(true);
            List<?> pages = (List<?>) field.get(isam);

            int pageId = 0;
            for (Object p : pages) {
                var recField = p.getClass().getDeclaredField("records");
                recField.setAccessible(true);
                List<?> recs = (List<?>) recField.get(p);

                System.out.print("Page " + pageId++ + ": ");
                for (Object r : recs) {
                    var keyF = r.getClass().getDeclaredField("key");
                    keyF.setAccessible(true);
                    int key = keyF.getInt(r);
                    System.out.print(key + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Reflection error printing data pages: " + e);
        }
    }

    private static void printIndex(ISAM isam) {
        try {
            var field = ISAM.class.getDeclaredField("rootIndex");
            field.setAccessible(true);
            Object rootIndex = field.get(isam);

            printIndexRecursive(rootIndex, 0);

        } catch (Exception e) {
            System.out.println("Reflection error printing index tree: " + e);
        }
    }

    private static void printIndexRecursive(Object indexPage, int depth) {
        if (indexPage == null) return;

        try {
            var keysField = indexPage.getClass().getDeclaredField("keys");
            var childrenField = indexPage.getClass().getDeclaredField("children");
            var leafFlag = indexPage.getClass().getDeclaredField("isLeafIndex");

            keysField.setAccessible(true);
            childrenField.setAccessible(true);
            leafFlag.setAccessible(true);

            List<?> keys = (List<?>) keysField.get(indexPage);
            List<?> children = (List<?>) childrenField.get(indexPage);
            boolean isLeafIndex = leafFlag.getBoolean(indexPage);

            String indent = " ".repeat(depth * 4);

            System.out.println(indent + "IndexPage { keys=" + keys + ", leaf=" + isLeafIndex + " }");

            // recursively print children if they are IndexPages
            for (Object child : children) {
                if (child.getClass().getSimpleName().equals("IndexPage")) {
                    printIndexRecursive(child, depth + 1);
                }
            }

        } catch (Exception e) {
            System.out.println("Reflection error in recursive print: " + e);
        }
    }
}
