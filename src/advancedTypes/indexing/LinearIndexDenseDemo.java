package advancedTypes.indexing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pointer {
    int pageId; // or blockId
}

class IndexEntry implements Comparable<IndexEntry> {
    int key;
    Pointer ptr;

    IndexEntry(int key, Pointer ptr) {
        this.key = key;
        this.ptr = ptr;
    }

    @Override
    public int compareTo(IndexEntry ie) {
        return Integer.compare(this.key, ie.key);
    }
}

class LinearIndexDense {
    private List<IndexEntry> entries;

    LinearIndexDense() {
        this.entries = new ArrayList<>();
    }

    /**
     * Insert key and keep entries sorted
     * @param key
     * @param ptr
     */
    public void insert(int key, Pointer ptr) {
        IndexEntry ie = new IndexEntry(key, ptr);
        if (this.entries.isEmpty()) {
            this.entries.add(ie);
            return;
        }
        IndexEntry dummy = new IndexEntry(key, null);
        int pos = Collections.binarySearch(this.entries, dummy);
        if (pos >= 0) {
            // key exists -> insert AFTER existing (stable insert)
            this.entries.add(pos + 1, ie);
        } else {
            // per Java Collections.binarySearch docs, if key is not found in the list, it returns the point at which the key
            // should be inserted into the list. so here we negate the negative value to find the rightful insertion point
            int insertionPoint = -pos - 1;
            this.entries.add(insertionPoint, ie);
        }
    }

    /**
     * Binary search for exact key
     * @param key
     * @return
     */
    public Pointer search(int key) {
        if (key < 0) {
            throw new IllegalArgumentException("key " + key + " is negative, which is not allowed.");
        }
        IndexEntry dummy = new IndexEntry(key, null);
        int pos = Collections.binarySearch(this.entries, dummy);
        if (pos < 0) {
            return null;
        }
        return this.entries.get(pos).ptr;
    }

    /**
     * Range scan between [low, high]
     * @param low lower bound
     * @param high higher bound
     * @return
     */
    public List<Pointer> range(int low, int high) {
        List<Pointer> result = new ArrayList<>();
        if (this.entries.isEmpty()) return result;
        if (low > high) return result;

        IndexEntry dummy = new IndexEntry(low, null);
        int pos = Collections.binarySearch(this.entries, dummy);
        int rangeIdx = (pos >= 0) ? pos : (-pos - 1);
        for (int i = rangeIdx; i < this.entries.size(); i++) {
            IndexEntry entry = this.entries.get(i);
            if (entry.key > high) break;
            result.add(entry.ptr);
        }

        return result;
    }

    public boolean delete(int key) {
        if (entries.isEmpty()) return false;

        IndexEntry dummy = new IndexEntry(key, null);
        int pos = Collections.binarySearch(this.entries, dummy);
        if (pos < 0) {
            return false; // key not found
        }
        // remove entry at position pos
        this.entries.remove(pos);
        return true;
    }

    public int deleteAll(int key) {
        int count = 0;
        IndexEntry dummy = new IndexEntry(key, null);
        int pos = Collections.binarySearch(entries, dummy);

        if (pos < 0) return 0;

        // find first
        int first = pos;
        while (first > 0 && entries.get(first - 1).key == key) first--;

        // find last
        int last = pos;
        while (last + 1 < entries.size() && entries.get(last + 1).key == key) last++;

        // remove range [first, last]
        for (int i = last; i >= first; i--) {
            entries.remove(i);
            count++;
        }

        return count;
    }
}

public class LinearIndexDenseDemo {
    public static void main(String[] args) {

    }
}
