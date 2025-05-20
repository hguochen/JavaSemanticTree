package search;

/**
 * Binary Search is a fast algorithm to find a target element in a sorted array.
 * It works by repeatedly dividing the search interval in half, eliminating half of the elements each time.
 *
 * Algorithm
 * 1. Check the value in the center of the array.
 * 2. If the target value is lower, search the left half of the array. If the target value is higher, search the right
 * half.
 * 3. Continue step 1 and 2 for the new reduced part of the array until the target value is found or until the search
 * area is empty.
 * 4. If the value is found, return the target value index. If the target value is not found, return -1.
 *
 * Time: O(logn)
 * Space:
 * - O(n) recursive
 * - O(1) iterative
 */
public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length < 1) return -1;
        return binarySearchUtil(arr, target, 0, arr.length - 1);
    }

    private static int binarySearchUtil(int[] arr, int target, int left, int right) {
        if (left > right) return -1; // base case: not found

        int mid = left + (right - left) / 2; // avoid potential integer overflow with large arrays
        if (arr[mid] == target) return mid; // found
        if (target < arr[mid]) { // search left half
            return binarySearchUtil(arr, target, left, mid - 1);
        } else { // search right half
            return binarySearchUtil(arr, target, mid + 1, right);
        }
    }

    public static int binarySearchIterative(int[] arr, int target) {
        if (arr == null || arr.length < 1) return -1;
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            if (target < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println(binarySearch(arr, 100));
        System.out.println(binarySearch(arr, 2));
        System.out.println(binarySearch(arr, 5));
        System.out.println(binarySearch(arr, 8));
        System.out.println(binarySearch(arr, 12));
        System.out.println(binarySearch(arr, 16));
        System.out.println(binarySearch(arr, 23));
        System.out.println(binarySearch(arr, 38));
        System.out.println(binarySearch(arr, 56));
        System.out.println(binarySearch(arr, 72));
        System.out.println(binarySearch(arr, 91));

        System.out.println(binarySearchIterative(arr, 100));
        System.out.println(binarySearchIterative(arr, 2));
        System.out.println(binarySearchIterative(arr, 5));
        System.out.println(binarySearchIterative(arr, 8));
        System.out.println(binarySearchIterative(arr, 12));
        System.out.println(binarySearchIterative(arr, 16));
        System.out.println(binarySearchIterative(arr, 23));
        System.out.println(binarySearchIterative(arr, 38));
        System.out.println(binarySearchIterative(arr, 56));
        System.out.println(binarySearchIterative(arr, 72));
        System.out.println(binarySearchIterative(arr, 91));
    }
}
