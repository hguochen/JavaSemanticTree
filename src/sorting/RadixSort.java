package sorting;

import java.util.Arrays;

/**
 * Radix Sort is a non-comparison-based sorting algorithm. Instead of comparing elements directly, it sorts numbers digit
 * by digit, from least significant digit (LSD) to most significant digit (MSD).
 *
 * It relies on a stable subroutine (often Counting Sort) to sort digits.
 *
 * When to use Radix sort:
 * - sorting non-negative integers
 * - All numbers have similar length
 * - we want linear time performance
 *
 * When NOT to use Radix sort:
 * - sorting floating-point or strings
 * - numbers are very large
 * - we have very large ranges with high memory costs
 *
 * Time: O(n * d)
 * - where n is the number of items and d is the most number of digits
 * Space: O(n + k)
 * - due to internal counting sort
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int max = Arrays.stream(arr).max().getAsInt();

        // apply counting sort for every digit(unit, tens, hundreds...)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];

        // count occurrences
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit] += 1;
        }

        // build cumulative sums
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i-1];
        }

        // build output
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[--count[digit]] = arr[i];
        }
        // copy back to original array
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {3, 1, 4, 5, 2},
                {7, 7, 7, 7, 7},
                {4, 2, 5, 2, 3, 1, 4},
                {-3, 0, 2, -1, 5, -2}, // does not work with test cases with negative numbers
                {42},
                {}, // empty array
                {1, 100, 2, 99, 3, 98},
                {1000000, 999999, 2147483647, -2147483648, 500000} // does not work with test cases with negative numbers
                // is too wide that leads to overflow

        };
        System.out.println("Test cases for selectionSort");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            radixSort(testCases[i]);
            System.out.println("Sorted: " + Arrays.toString(testCases[i]));
        }
    }
}
