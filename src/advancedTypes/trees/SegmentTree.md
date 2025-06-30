# Segment Tree
## what is it?
- a binary tree used for efficient range queries and updates in an array.
- very useful when we need to repeatedly query or update a subrange of values like the sum, min, or max of the range, in O(logn) time.

## Core idea
A segment tree recursively breaks the arrage into segments
    - leaf nodes represent individual elements
    - internal nodes store results over a range(eg. sum of arr[0...3])

### Example
Given arr = [2, 4, 5, 7, 8, 9]
A segment tree for sum looks like:

```
            [0,5]=35
           /       \
      [0,2]=11     [3,5]=24
     /     \       /     \
 [0,1]=6  [2]=5  [3,4]=15 [5]=9
 /   \
[0]=2 [1]=4
```

## When to use it?
- Range sum, min, max, etc
- fast updates and queries on an array
- better performance than brute-force O(n) operations 

## Goal
Say you have an array:
```java
int[] arr = {2, 4, 5, 7, 8, 9};
```
we want to answer range queries like:
- what's the sum from index 1 to 4?

Instead of recomputing each time, we preprocess a tree so we can:
- query range sums in O(log n)
- update values in O(log n) 