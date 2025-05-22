package search;

/**
 * Searches for an element linearly.
 *
 * Time: O(n)
 * - where n is the size of the input array
 */
public class LinearSearch {
    public static int linearSearch(int[] arr, int target) {
        if (arr == null || arr.length < 1) return -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

        System.out.println(linearSearch(arr, 100));
        System.out.println(linearSearch(arr, 2));
        System.out.println(linearSearch(arr, 5));
        System.out.println(linearSearch(arr, 8));
        System.out.println(linearSearch(arr, 12));
        System.out.println(linearSearch(arr, 16));
        System.out.println(linearSearch(arr, 23));
        System.out.println(linearSearch(arr, 38));
        System.out.println(linearSearch(arr, 56));
        System.out.println(linearSearch(arr, 72));
        System.out.println(linearSearch(arr, 91));
    }
}
