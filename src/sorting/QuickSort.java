package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Quicksort, also known as partition-exchange sort, is an efficient, in-place sorting algorithm, which uses divide and
 * conquer principles. It was developed by Tony Hoare in 1959. It operates by selecting a 'pivot' element from the array
 * and partitioning the other elements into two sub-arrays, according to whether they are less than or greater than the
 * pivot. The sub-arrays are then recursively sorted. This process continues until the base case is achieved, which is
 * when the array or sub-array has zero or one element, hence is already sorted. Quicksort can have worst-case performance
 * of O(n^2) if the pivot is the smallest or the largest element in the array, although this scenario is rare if the pivot
 * is chosen randomly. The average case time complexity is O(n log n).
 *
 * Time:
 * - Worst: O(n^2)
 * - Average: O(nlogn)
 * Space:
 * - Stack (best/avg): O(log n)
 * - Stack (worst): O(n)
 * - Extra memory: O(1) (in-place)
 *
 * Your implementation is recursive, so the main space used is on the call stack:
 * 	•	Best / Average Case (balanced partitions):
 * Recursion depth is O(log n) → O(log n) stack space
 * 	•	Worst Case (e.g. sorted array, bad pivot every time):
 * Recursion depth becomes O(n) → O(n) stack space
 *
 * However, since you use a random pivot, worst-case is rare in practice.
 *
 * You’re sorting the array in-place — no extra arrays or lists are used — so:
 * 	•	No additional heap memory → O(1) auxiliary space (excluding recursion stack)
 */
public class QuickSort {
    private static final Random RANDOM = new Random();
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quickSortUtil(arr, 0, arr.length - 1);
    }

    private static void quickSortUtil(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) return;

        int pivot = partition(arr, leftIndex, rightIndex);
        quickSortUtil(arr, leftIndex, pivot - 1);
        quickSortUtil(arr, pivot + 1, rightIndex);

    }

    private static int partition(int[] arr, int left, int right) {
        // generate a random index between 2 bounds inclusive
        int pivot = RANDOM.nextInt(right - left + 1) + left;
        swap(arr, pivot, right);
        int pivotValue = arr[right];
        // storeIndex is the index of the bound where left bound are elements less than pivotValue, right bound are
        // elements more than pivotValue.
        // storeIndex is also where the pivotValue should go to at the end of this partition
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] <= pivotValue) {
                // current i element is less than pivotValue, we put this value into the left bound
                swap(arr, i, storeIndex);
                storeIndex += 1;
            }
        }
        // move pivot value to its final place
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    public static void swap(int[] arr, int i, int j) {
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
            quickSort(testCases[i]);
            System.out.println("Sorted: " + Arrays.toString(testCases[i]));
        }
    }
}
