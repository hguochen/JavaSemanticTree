package sorting;

import java.util.Arrays;

/**
 * Counting sort is a non comparison sorting algorithm that works best for sorting integers over a known, small range.
 * 1. Counts how many times each value appears
 * 2. Uses that count to figure out where each element belongs in the final sorted array
 *
 * When to use Counting sort:
 * - sorting integers
 * - values have a known small range
 * - need a stable sort
 *
 * When NOT to use Counting sort:
 * - sorting floating points or strings
 * - range is huge
 * - low on memory(extra arrays needed)
 *
 * Time: O(n + k)
 * - where n is the number of items
 * - k is the range of input values (eg. 0-1000)
 * Space: O(n + k)
 */
public class CountingSort {
    public static int[] countingSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;

        // get the max and min value in this array
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        // size of the counting array to initialize to
        int range = max - min + 1;
        int[] count = new int[range];
        int[] result = new int[arr.length];

        // 1. count frequencies
        for (int element : arr) {
            count[element - min] += 1;
        }

        // 2. cumulative sums.
        // this cumulative sums tells us the final position of each element
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        // 3. build output
        for (int i = arr.length - 1; i >= 0; i--) {
            result[--count[arr[i] - min]] = arr[i];
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
                {1, 100, 2, 99, 3, 98},
                {1000000, 999999, 2147483647, -2147483648, 500000} // not suitable to use counting sort as the range
                // is too wide that leads to overflow

        };
        System.out.println("Test cases for selectionSort");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            System.out.println("Sorted: " + Arrays.toString(countingSort(testCases[i])));
        }
    }
}
