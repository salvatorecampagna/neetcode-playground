# Two Integer Sum II

**Topic:** Two Pointers · **Difficulty:** Medium

## Summary

Given an array sorted in non-decreasing order and a target, return the
1-based positions of the two different elements that add up to the target,
smaller position first. Exactly one such pair exists, and the solution may
use only O(1) extra space.

## Approach

- **`twoSumTwoPointers`:** start with `i` at the first element and `j` at the
  last. If `nums[i] + nums[j]` equals `target`, return the pair; if it is less
  than `target`, move `i` forward; if it is greater, move `j` back. Only two
  indices are kept, so the extra space is O(1). `twoSum` delegates to this
  version.
- **`twoSumNestedForLoops`:** check every pair of indices `i < j` and return
  the one whose values add up to `target`.

Both return 1-based indices, smaller first.

## Correctness

- **`twoSumTwoPointers`:** the invariant is that, if the answer pair exists,
  both of its indices stay between `i` and `j`. Initially they span the whole
  array. When `nums[i] + nums[j] < target`, every remaining element is at most
  `nums[j]` because the array is sorted, so `nums[i]` plus any of them is
  below `target`: `nums[i]` cannot be part of the answer and dropping it keeps
  the invariant. Symmetrically, when the sum is greater than `target`, every
  remaining element is at least `nums[i]`, so `nums[j]` cannot be part of the
  answer. Each step moves one index inward without discarding an answer
  element, and exactly one pair exists, so the indices reach it. The sum does
  not necessarily get closer to `target` at every step; what matters is that
  no step skips the pair.
- **`twoSumNestedForLoops`:** it examines every pair `i < j`, so it reaches
  the only valid pair, and starting `j` at `i + 1` keeps the indices different
  and in order.

Values and the target lie between -1,000 and 1,000, so every sum fits easily
in an `int`.

## Complexity

`n` is the length of the array.

| Method | Time | Extra space | Why |
|---|---|---|---|
| `twoSumTwoPointers` | O(n) | O(1) | Each step moves one index inward, so there are at most n - 1 steps. |
| `twoSumNestedForLoops` | O(n²) | O(1) | Up to n(n - 1)/2 pairs are checked. |

Both meet the O(1) extra-space requirement; only the two-pointer version also
runs in linear time.

## Follow-ups

### Variations

- What if the array were not sorted? What changes in time and space?
- What if all the pairs that add up to `target` had to be returned, with
  duplicate values in the array?
- How does the idea extend to three numbers that add up to a target?

<!--
## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
