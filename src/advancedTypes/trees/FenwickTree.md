# Fenwick Tree
## what is it?
Fenwick tree(aka Binary indexed tree) is a data structure that efficiently supports:
- prefix sum queries: sum[i...j]
- point updates: add a value at index i

Fenwick tree is similar to Segment tree, but
- simpler to implement
- lower memory footprint
- best when we are only interested in prefix queries

## core idea
- a Fenwick tree stores partial sums in a 1-based array
- each index i in the tree represents a range of elements determined by the last set bit of i
  - for eg. if i = 6(0110 in binary), the range is of size 2
  - it stores the sum of the last 2 elements up to index 6

## time and space complexity

| Operation         | Time Complexity | Space Complexity |
|------------------|------------------|------------------|
| Build (n updates) | O(n log n)       | O(n)             |
| Point Update      | O(log n)         | O(n)             |
| Prefix Sum Query  | O(log n)         | O(n)             |
| Range Query       | O(log n)         | O(n)             |

## use cases
- frequency counting
- range sum queries
  - and we don't need full range min/max
- inversion count in arrays
- 2D prefix sums

## motivation
Given an array of integer values compute the range sum between index [i, j)

```java
     0   1  2  3  4   5  6   7  8  9
A = [5, -3, 6, 1, 0, -4, 11, 6, 2, 7]
// we can compute the prefix array of the above like this
P = [0,  5, 2, 8, 9,  9,  5, 16,22,24,31]
```
- sum from [2, 7) = P[7] - P[2] = 16 - 2 = 14
- sum from [0, 4) = P[4] - P[0] = 9 - 0 = 9
- sum from [7, 8) = P[8] - P[7] = 22 - 16 = 6

Above works when calculating prefix sum values. But what about if we want to update our initial array with some new value?
- to update a single value in A array, we would have to recalculate the prefix sum P for every index after the index that's updated.

Therefore, a prefix sum array is great for static arrays, but takes O(n) for updates.

## concept
- unlike a regular array, in a fenwick tree a specific cell is responsible for other cells as well
- the position of the least significant bit(LSB) determines the range of responsibility that cell has to the cells below itself.
  - index 11 in binary is : 1011
  - LSB is at position 1, so this index is responsible for 2^(1-1) = 1 cell (itself).

```java
            pos 1(LSB)  pos 2  pos 3  pos 4  pos 5
16 - 10000                                     |
15 - 01111     |                               |
14 - 01110               |                     |
13 - 01101     |         |                     |
12 - 01100                       |             |
11 - 01011     |                 |             |
10 - 01010               |       |             |
9  - 01001     |         |       |             |
8  - 01000                              |      |
7  - 00111     |                        |      |
6  - 00110               |              |      |
5  - 00101     |         |              |      |
4  - 00100                       |      |      |
3  - 00011     |                 |      |      |
2  - 00010               |       |      |      |
1  - 00001     |         |       |      |      |
```
how do we find the range queries with above?
we will calculate the prefix sum up to a certain index.
idea: suppose we want tot find the prefix sum of [1, i], then we start at i and cascade downwards until we reach zero adding the value
at each of the indices we encounter.

eg.

Find the prefix sum up to index 7.

sum = A[7] + A[6] + A[4]

Find the prefix sum up to index 11

sum = A[11] + A[10] + A[8]

Now we can use the prefix sums to compute the interval sum between [i, j]

Compute the interval sum between [11, 15]
1. compute the prefix sum of [1, 15]
2. compute the prefix sum of [1, 11)
3. get the difference

sum [1, 15] = A[15] + A[14] + A[12] + A[8]
sum [1, 11) = A[10] + A[8]

range sum: (A[15] + A[14] + A[12] + A[8]) - (A[10] + A[8])