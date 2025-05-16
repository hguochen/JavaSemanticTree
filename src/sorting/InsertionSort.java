package sorting;

import java.util.Arrays;

/**
 * Insertion Sort
 *
 * 1. Take the first value from the unsorted part of the array.
 * 2. Move the value into the correct place in the sorted part of the array.
 * 3. Go through the unsorted part of the array again as many times as there are values.
 *
 * Time: O(N^2)
 * Space: O(1)
 */
public class InsertionSort {
    public static int[] insertionSort(int[] arr) {
        if (arr.length < 2) return arr;
        // i represents the leftmost index of the unsorted array
        // we go from left to right through the unsorted array and put them into sorted section
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (arr[j] < arr[j-1]) {
                    // swap the smaller value to the left
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        return arr;
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
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            insertionSort(testCases[i]);
            System.out.println("Sorted: " + Arrays.toString(testCases[i]));
        }
    }
}
