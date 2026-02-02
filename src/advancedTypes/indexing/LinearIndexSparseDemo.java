package advancedTypes.indexing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Record implements Comparable<Record>{
    int key;
    int value;

    public Record(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Record record) {
        return Integer.compare(this.key, record.key);
    }
}

class DataPage {
    private static final int PAGE_SIZE = 4;

    private final List<Record> records;

    DataPage() {
        this.records = new ArrayList<>();
    }

    public void insert(int key, int value) {
        if (this.isFull()) throw new IllegalStateException("Page full");
        Record record = new Record(key, value);
        if (this.records.isEmpty()) {
            this.records.add(record);
            return;
        }
        int pos = Collections.binarySearch(this.records, record);
        if (pos >= 0) {
            this.records.set(pos, record); // overwrite
            return;
        }
        int insertionPoint = -pos - 1;
        this.records.add(insertionPoint, record);
    }

    public boolean isFull() {
        return this.records.size() >= PAGE_SIZE;
    }

    public Integer minKey() {
        if (this.records.isEmpty()) return null;
        return this.records.get(0).key;
    }

    public List<Record> getRecords() {
        return this.records;
    }
}

class SparseEntry implements Comparable<SparseEntry> {
    int minKey;
    DataPage page;

    SparseEntry(int minKey, DataPage page) {
        this.minKey = minKey;
        this.page = page;
    }

    @Override
    public int compareTo(SparseEntry entry) {
        return Integer.compare(this.minKey, entry.minKey);
    }
}

class SparseIndex {
    List<SparseEntry> entries;

    private static class SplitResult {
        DataPage left;
        DataPage right;
        int rightMinKey;

        SplitResult(DataPage left, DataPage right, int rightMinKey) {
            this.left = left;
            this.right = right;
            this.rightMinKey = rightMinKey;
        }
    }

    SparseIndex() {
        this.entries = new ArrayList<>();
    }

    /**
     * Build sparse index from data pages.
     * Assumes each DataPage has its minKey populated.
     */
    public void buildIndex(List<DataPage> pages) {
        this.entries.clear();
        for (DataPage page : pages) {
            Integer mk = page.minKey();
            if (mk != null) {
                this.entries.add(new SparseEntry(mk, page));
            }
        }
        Collections.sort(this.entries);
    }

    /**
     * Find the data page that should contain the key.
     * @param key
     * @return
     */
    public DataPage findPage(int key) {
        if (this.entries.isEmpty()) return null;

        int pos = Collections.binarySearch(this.entries, new SparseEntry(key, null));
        if (pos >= 0) {
            // exact match
            return entries.get(pos).page;
        }

        // no exact match -> get floor(minKey)
        int insertionPoint = -pos - 1;

        // floor entry is insertionPoint - 1
        if (insertionPoint == 0) return this.entries.get(0).page;
        return this.entries.get(insertionPoint - 1).page;
    }

    /**
     * Insert into sparse index:
     *  - Find the correct page
     *  - if full, caller must handle split (Step 3)
     */
    public void insert(int key, int value) {
        DataPage page = this.findPage(key);
        if (page == null) throw new IllegalStateException("No data pages");

        if (!page.isFull()) {
            page.insert(key, value);
            return;
        }

        // find the SparseEntry index for this page
        int idx = -1;
        for (int i = 0; i < this.entries.size(); i++) {
            if (this.entries.get(i).page == page) {
                idx = i;
                break;
            }
        }
        if (idx == -1) throw new IllegalStateException("SparseEntry not found");

        // otherwise: page overflow, perform split
        SplitResult splitResult = this.splitPage(page);
        int oldMinKey = this.entries.get(idx).minKey; // left keeps the original minKey

        // remove old entry
        this.entries.remove(idx);

        // insert left page
        this.entries.add(idx, new SparseEntry(oldMinKey, splitResult.left));
        // insert right page
        this.entries.add(idx + 1, new SparseEntry(splitResult.rightMinKey, splitResult.right));
    }

    private SplitResult splitPage(DataPage page) {
        List<Record> records = page.getRecords();
        DataPage left = new DataPage();
        DataPage right = new DataPage();
        int i = 0;
        while (i < records.size() / 2) {
            Record record = records.get(i);
            left.insert(record.key, record.value);
            i++;
        }
        while (i < records.size()) {
            Record record = records.get(i);
            right.insert(record.key, record.value);
            i++;
        }
        int rightMinKey = right.minKey();
        return new SplitResult(left, right, rightMinKey);
    }
}

public class LinearIndexSparseDemo {
    public static void main(String[] args) {

    }
}
