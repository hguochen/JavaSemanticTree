package sorting;

import java.util.Arrays;

/**
 * Heap Sort is an efficient, comparison-based sorting algorithm. It utilizes a data structure known as a 'binary heap',
 * and works by dividing its input into a sorted and an unsorted region, and iteratively shrinking the unsorted region
 * by extracting the largest element and moving that to the sorted region. It's an in-place algorithm but not a stable
 * sort. It involves building a Max-Heap, which is a specialized tree-based data structure, and then swapping the root
 * node (maximum element) with the last node, reducing the size of heap by one and heapifying the root node. The maximum
 * element is now at the end of the list and this step is repeated until all nodes are sorted. Heap Sort offers a good
 * worst-case runtime of O(n log n), irrespective of the input data.
 *
 * Core Idea
 * 	1.	Build a Max Heap from the input array.
 * 	2.	Repeatedly extract the maximum element from the heap and move it to the end of the array.
 * 	3.	Heapify the reduced heap.
 * 	4.	Repeat until the array is sorted in ascending order.
 *
 * Type: in place, comparison sort
 * Stable: NO
 *
 * Time: O(nlogn)
 * Space: O(1)
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        // 1. build max heap
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length,i);
        }
        // 2. extract elements one by one
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            // call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    /**
     *
     * @param arr the array to be sorted
     * @param n heap size. It tells the method how many elements of the array should be considered as part of the active heap.
     * @param i Index of the current node to heapify
     */
    private static void heapify(int[] arr, int n, int i) {
        // initialize largest as root
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // if left child is larger
        if (left < n && arr[left] > arr[largest])
            largest = left;
        // if right child is larger
        if (right < n && arr[right] > arr[largest])
            largest = right;
        // if root is not largest
        if (largest != i) {
            swap(arr, i, largest);
            // recursively heapify the affected sub tree
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {3, 1, 4, 5, 2},
                {7, 7, 7, 7, 7},
                {4, 2, 5, 2, 3, 1, 4},
                {-3, 0, 2, -1, 5, -2},
                {42},
                {}, // empty array
                {1000000, 999999, 2147483647, -2147483648, 500000},
                {1, 100, 2, 99, 3, 98}
        };
        System.out.println("Test cases for selectionSort");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            heapSort(testCases[i]);
            System.out.println("Sorted: " + Arrays.toString(testCases[i]));
        }
    }
}
