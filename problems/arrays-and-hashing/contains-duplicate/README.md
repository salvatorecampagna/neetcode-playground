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

## Follow-ups

### Related problems

- **Contains Duplicate II:** equal values at most `k` indices apart.
- **Contains Duplicate III:** values differing by at most `t`, at most `k`
  indices apart.
- **Valid Anagram**
- **Find the Duplicate Number**
- **Find All Duplicates in an Array**

### Variations

- Return the duplicated values, how many times each occurs, or the first value
  that repeats.

### Constraints

- Can it be done in O(1) extra space without modifying the input? At what cost?
- What changes if the values lie in a small known range, such as 0 to 1,000 or
  1 to n?
- How much memory does a bit array over the full `int` range take, and when is
  it reasonable?

### Scale

- What if the input is a stream, or does not fit in memory?
- What if the data is spread across several machines?
- If a small false-positive rate is acceptable, which structure uses much less
  memory?

### Java specifics

- Is `Arrays.stream(nums).distinct().count()` a good idea? What does it cost?
- How can boxing be avoided while keeping a hash-based approach?
- For objects instead of `int`, which contract must the element class honor,
  and what happens if an element is mutated after insertion?
- What happens with inputs crafted to collide in the hash table?
- Can the check be parallelized, and with which tools?

## References

- [`Set.add`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Set.html#add(E)):
  returns `false` if the element is already present
- [`HashSet`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/HashSet.html):
  constant-time basic operations, assuming a good hash function
- [`Arrays.sort(int[])`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Arrays.html#sort(int%5B%5D)):
  dual-pivot quicksort, O(n log n) on all data sets
