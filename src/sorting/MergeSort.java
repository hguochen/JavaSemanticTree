package sorting;

import java.util.Arrays;

/**
 * Merge sort is a type of sorting algorithm that follows the divide-and-conquer paradigm. It was invented by John von
 * Neumann in 1945. This algorithm works by dividing an unsorted list into n partitions, each containing one element (a
 * list of one element is considered sorted), then repeatedly merging partitions to produce new sorted lists until there
 * is only 1 sorted list remaining. This resulting list is the fully sorted list. The process of dividing the list is
 * done recursively until it hits the base case of a list with one item. Merge sort has a time complexity of O(n log n)
 * for all cases (best, average and worst), which makes it highly efficient for large data sets.
 *
 * Time: O(nlogn)
 * Explanation:
 * 	•	You repeatedly divide the array in half (logarithmic number of times → log n splits).
 * 	•	For each split, you merge arrays which takes linear time (O(n)) overall.
 * Space: O(n)
 *  Explanation:
 * 	•	Every merge creates a new array of size n (not in-place).
 * 	•	Each recursive call returns a new array, and even though recursive calls are log n deep, the space used for new
 * 	arrays adds up to O(n) (not O(n log n) because the merges are sequential — the memory is reused once smaller arrays
 * 	are merged).
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int[] temp = new int[arr.length];
        mergeSortInPlace(arr, temp, 0, arr.length - 1);
    }

    /**
     * In place merge sort with an auxiliary buffer
     * @param arr
     * @param temp
     * @param left
     * @param right
     */
    private static void mergeSortInPlace(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        // we calculate mid like this to avoid large arrays leading to integer overflow
        int mid = left + (right - left) / 2;
        mergeSortInPlace(arr, temp, left, mid);
        mergeSortInPlace(arr, temp, mid + 1, right);
        mergeInPlace(arr, temp, left, mid, right);
    }

    private static void mergeInPlace(int[] arr, int[] temp, int left, int mid, int right) {
        // Copies the 'arr', beginning at left index position, to the specified 'temp' array, starting at 'left'
        // index position of 'temp', with length of the number of elements to be copied
        System.arraycopy(arr, left, temp, left, right - left + 1);
        int i = left, j = mid + 1, resultIndex = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[resultIndex++] = temp[i++];
            } else {
                arr[resultIndex++] = temp[j++];
            }
        }
        while (i <= mid) arr[resultIndex++] = temp[i++];
        // no need to copy the right half - already in place
    }

    /**
     * Utility class to perform merge sort by repeatedly breaking input array into sub sections and then join them
     * together again in sorted order.
     * Note: This is more memory consuming.
     * @param arr
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    private static int[] mergeSortUtil(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) return new int[]{arr[leftIndex]};
        int pivot = (leftIndex + rightIndex) / 2;
        int[] left = mergeSortUtil(arr, leftIndex, pivot);
        int[] right = mergeSortUtil(arr, pivot + 1, rightIndex);
        return merge(left, right);
    }

    /**
     * Takes 2 arrays and merge the elements together in sorted order.
     *
     * @param left
     * @param right
     * @return
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }
        return result;
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
            mergeSort(testCases[i]);
            System.out.println("Sorted: " + Arrays.toString(testCases[i]));
        }
    }
}
