package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Bucket Sort divides the input into multiple buckets, sorts each bucket individually (using another sorting algorithm
 * like Insertion Sort or QuickSort), and then concatenates all buckets to produce the final sorted list.
 *
 * It’s a non-comparison-based sorting algorithm (in structure), though often uses comparison-based sorts inside the buckets.
 *
 * When to use Bucket sort:
 * - real numbers in range [0, 1)
 * - uniformly distributed values
 * - floating point sorting
 *
 * When NOT to use Bucket sort:
 * - large datasets with skewed distribution
 * - many repeated values or unknown range
 * - integer heavy or very large values
 *
 * Time:
 * - Best: O(n + k)
 * - Average: O(n + k)
 * - Worst: O(n^2) (all values fall into one bucket)
 * where n is the number of elements and k is the number of buckets
 *
 * Space: O(n + k)
 */
public class BucketSort {
    public static void bucketSort(float[] arr) {
        if (arr.length == 0) return;
        // 1. create n empty buckets
        List<Float>[] buckets = new List[arr.length];
        for (int i = 0; i < arr.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 2. put elements into buckets
        for (float num : arr) {
            int bucketIndex = (int) (num * arr.length); // assumes [0,1)
            buckets[bucketIndex].add(num);
        }

        // 3. sort each bucket
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 4. concatenate buckets
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (Float num : bucket) {
                arr[index] = num;
                index += 1;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = {0.42f, 0.32f, 0.23f, 0.52f, 0.25f, 0.47f};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
