package sorting;

import java.util.Arrays;

/**
 * Selection Sort
 * 1. Go through the array to find the lowest value.
 * 2. Move the lowest value to the front of the unsorted part of the array.
 * 3. Go through the array again as many times as there are values in the array.
 *
 * Time: O(N^2)
 * Space: O(1)
 */
public class SelectionSort {
    public static int[] selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        // 'i' marks the current position where the smallest element from the unsorted section should be placed
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 'j' loop traverses through the unsorted section of the array to find the smallest element
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) swap(arr, i, minIndex);
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
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
            selectionSort(testCases[i]);
            System.out.println("Sorted: " + Arrays.toString(testCases[i]));
        }
    }
}
