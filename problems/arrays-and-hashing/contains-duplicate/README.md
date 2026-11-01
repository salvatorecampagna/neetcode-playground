# Contains Duplicate

**Topic:** Arrays & Hashing · **Difficulty:** Easy

## Summary

Given an array of integers, return `true` if some value appears at least
twice, and `false` if every value is distinct.

## Approach

Three implementations in `ContainsDuplicate`:

- **`hasDuplicate` (hash set):** add each element to a `HashSet`. `Set.add`
  returns `false` when the value is already present, so the method returns
  `true` at the first repeated value and `false` after a full pass.
- **`hasDuplicateBySorting`:** sort the array in place, then scan adjacent
  pairs for equal values. The caller's array is reordered.
- **`hasDuplicateBySortingAndCopying`:** sort a copy and scan it the same way,
  leaving the caller's array unchanged.

## Correctness

- **Hash set:** the set holds exactly the distinct values seen so far. A value
  is a duplicate if and only if it is already in the set when it is reached,
  which is when `add` returns `false`.
- **Sorting:** after sorting, equal values are contiguous, so a value occurs
  more than once if and only if some element equals its predecessor. Scanning
  all adjacent pairs therefore finds a duplicate exactly when one exists.

## Complexity

| Method | Time | Extra space | Modifies input |
|---|---|---|---|
| `hasDuplicate` | O(n) average | O(n) | No |
| `hasDuplicateBySorting` | O(n log n) | No auxiliary collection | Yes |
| `hasDuplicateBySortingAndCopying` | O(n log n) | O(n) | No |

- **Hash set:** each `add` is O(1) on average. Resizing rehashes the set, but
  the capacity doubles, so the total over n inserts stays O(n). In the worst
  case (no duplicates) every element is stored, each boxed to an `Integer`
  inside a hash-table entry.
- **Sorting:** `Arrays.sort(int[])` is O(n log n) and dominates the O(n) scan
  (and the O(n) copy). The extra space used by the sort itself is not
  specified by its contract.

## Choosing an implementation

- **Default: the hash set.** It is the fastest asymptotically, and it exits at
  the first duplicate, so inputs with an early duplicate are very cheap. The
  sorting variants always pay the full sort, wherever the duplicate is.
- **In-place sort:** when memory is tight and reordering the caller's array is
  acceptable. It allocates no auxiliary collection.
- **Sort a copy:** when the input must not be modified and allocation or GC
  pressure matters more than the log factor. A single primitive `int[]` is far
  more compact than a hash set of boxed `Integer` values, and it avoids hashing.

## References

- [`Set.add`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Set.html#add(E)):
  returns `false` if the element is already present
- [`HashSet`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/HashSet.html):
  constant-time basic operations, assuming a good hash function
- [`Arrays.sort(int[])`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Arrays.html#sort(int%5B%5D)):
  dual-pivot quicksort, O(n log n) on all data sets
