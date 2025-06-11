package advancedTypes.heap;

import java.util.Arrays;
import java.util.List;

/**
 * MinHeap is a binary heap where the parent node is always less than or equal to its child nodes.
 * It's commonly implemented using an array. Root is always the minimum value in the minimum heap.
 *
 * Internal structure:
 * - stored as an array heap
 * - for a node at index i
 *      - left = 2 * i + 1
 *      - right = 2 * i + 2
 *      - parent = (i - 1) / 2
 */
class MinHeap {
    private int[] heap;
    private int lastIndex;

    public MinHeap(int size) {
        this.heap = new int[size];
        Arrays.fill(this.heap, Integer.MIN_VALUE);
        this.lastIndex = -1;
    }

    /**
     * Insert value into heap
     * @param val
     */
    public void insert(int val) {
        this.ensureCapacity();
        this.lastIndex += 1;
        this.heap[this.lastIndex] = val;
        this.heapifyUp();
    }

    /**
     * Return the min value without removing
     * @return
     */
    public int peek() {
        if (this.lastIndex < 0) {
            throw new RuntimeException("Heap is empty.");
        }
        return this.heap[0];
    }

    /**
     * Remove and return the min value
     * @return
     */
    public int poll() {
        if (this.lastIndex < 0) {
            throw new RuntimeException("Heap is empty");
        }
        // swap root with the last element
        this.swap(0, this.lastIndex);
        // extract min value
        int minValue = this.heap[this.lastIndex];
        // clear the slot
        this.heap[this.lastIndex] = Integer.MIN_VALUE;
        // reduce heap size
        this.lastIndex -= 1;
        // maintain heap property
        this.heapifyDown();

        return minValue;
    }

    /**
     * Restores the min-heap property by percolating the last inserted element up.
     * Should be called after inserting a new element at the end of the heap.
     */
    public void heapifyUp() {
        int currIndex = this.lastIndex;
        while (currIndex > 0) {
            int parentIndex = (currIndex- 1) / 2;
            if (this.heap[currIndex] >= this.heap[parentIndex]) break;
            this.swap(currIndex, parentIndex);
            currIndex = parentIndex;
        }
    }

    /**
     * swap the values of 2 indexes
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2) {
        int temp = this.heap[index1];
        this.heap[index1] = this.heap[index2];
        this.heap[index2] = temp;
    }

    /**
     * Restores the min-heap property by moving the top element down.
     * Should be called after removing the top element and replacing with the lastIndex element.
     */
    public void heapifyDown() {
        if (this.lastIndex < 0) return;
        this.heapifyDown(0);
    }

    /**
     * Restores min-heap property by moving the index element down.
     * @param index
     */
    public void heapifyDown(int index) {
        int currIndex = index;
        while (currIndex < this.lastIndex) {
            int smallestIndex = currIndex;
            int leftIndex = 2 * currIndex + 1;
            int rightIndex = leftIndex + 1;
            if (leftIndex <= this.lastIndex && this.heap[leftIndex] < this.heap[smallestIndex]) {
                smallestIndex = leftIndex;
            }
            if (rightIndex <= this.lastIndex && this.heap[rightIndex] < this.heap[smallestIndex]) {
                smallestIndex = rightIndex;
            }
            if (smallestIndex == currIndex) break;
            this.swap(smallestIndex, currIndex);
            currIndex = smallestIndex;
        }
    }

    // Return true if heap is empty
    public boolean isEmpty() {
        return this.lastIndex < 0;
    }

    /**
     * Return number of elements in heap
     * @return
     */
    public int size() {
        return this.lastIndex + 1;
    }

    /**
     * Remove arbitrary element.
     * @param val
     */
    public void remove(int val) {
        // 1. find the index of the value, if value is not found, return
        int index = -1;
        for (int i = 0; i <= this.lastIndex; i++) {
            if (this.heap[i] == val) {
                index = i;
                break;
            }
        }
        if (index == -1) return;
        // 2. if the index is first, simply call poll
        if (index == 0) this.poll();
        // 3. if the index is last, simply remove
        if (index == this.lastIndex) {
            this.heap[index] = Integer.MIN_VALUE;
            this.lastIndex -= 1;
            return;
        }
        // --now we deal with the case where index is somewhere in between--
        // 4. replace the index element to remove with the last element, then heapifyDown
        this.swap(index, this.lastIndex);
        this.heap[this.lastIndex] = Integer.MIN_VALUE;
        this.lastIndex -= 1;
        this.heapifyDown(index);
    }

    private void ensureCapacity() {
        if (this.lastIndex + 1 >= this.heap.length) {
            this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
        }
    }
}

public class MinHeapDemo {
    public static void main(String[] args) {

    }
}
