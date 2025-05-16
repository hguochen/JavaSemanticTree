package sorting;

import java.util.Arrays;

/**
 * Bubble Sort
 *
 * 1. Go through the array, one value at a time.
 * 2. For each value, compare the value with the next value.
 * 3. If the value is higher than the next one, swap the values so that the highest value comes last.
 * 4. Go through the array as many times as there are values in the array.
 *
 * Time: O(N^2)
 * Space: O(1)
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] arr) {
        if (arr.length < 2) return arr;
        boolean isSwapped;
        do {
             isSwapped= false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i-1]) {
                    isSwapped = true;
                    int temp = arr[i];
                    arr[i] = arr[i-1];
                    arr[i-1] = temp;
                }
            }
        } while (isSwapped);

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
            bubbleSort(testCases[i]);
            System.out.println("Sorted: " + Arrays.toString(testCases[i]));
        }
    }
}
