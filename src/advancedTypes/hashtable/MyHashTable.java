package advancedTypes.hashtable;

import java.util.Hashtable;

class Entry<K, V> {
    public K key;
    public V value;
    public Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class MyHashTable<K, V> {
    private Entry<K, V>[] buckets;
    private int capacity = 16;
    private int size = 0;

    public MyHashTable() {
        this.buckets = new Entry[capacity];
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Time: O(n)
     * Space: O(1)
     * where n is the longest bucket chain size
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> head = this.buckets[index];
        // replace existing key
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        // insert new entry at head
        Entry<K, V> newNode = new Entry<>(key, value);
        newNode.next = this.buckets[index];
        this.buckets[index] = newNode;
        this.size += 1;
    }

    /**
     * Time: O(n)
     * Space: O(1)
     * where n is the longest bucket chain size
     * @param key
     * @return
     */
    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> head = this.buckets[index];

        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    /**
     * Time: O(n)
     * Space: O(1)
     * where n is the longest bucket chain size
     * @param key
     * @return
     */
    public boolean remove(K key) {
        int index = this.getBucketIndex(key);
        Entry<K, V> head = this.buckets[index];
        Entry<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) prev.next = head.next;
                else this.buckets[index] = head.next;
                this.size -= 1;
                return true;
            }
            prev = head;
            head = head.next;
        }
        return false;
    }

    public boolean containsKey(K key) {
        return this.get(key) != null;
    }

    public int size() {
        return size;
    }
}
