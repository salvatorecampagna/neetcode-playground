# Two Sum

**Topic:** Arrays & Hashing · **Difficulty:** Easy

## Summary

Given an array of integers and a target, return the indices of the two
different positions whose values add up to the target, smaller index first.
Exactly one such pair exists.

## Approach

- **`twoSumNestedForLoops`:** check every pair of indices `i < j` and return
  the pair whose values add up to `target`.
- **`twoSumWithHashMap`:** a first pass maps each value to an index; a second
  pass looks up `target - nums[i]` for each `i` and accepts a match at a
  different index, returned smaller index first.
- **`twoSumWithHashMapOnePass`:** for each `i`, look up `target - nums[i]`
  among the values already seen, then add `nums[i]` to the map; a match is
  always at an earlier index.

## Correctness

Let `p < q` be the indices of the only pair, as the problem guarantees.

- **One index per value is enough.** If `nums[p] != nums[q]`, each of the two
  values occurs exactly once: a second occurrence of either would form a
  second pair. If `nums[p] == nums[q]`, that value occurs exactly twice. So a
  map from each value to a single index never hides an index the answer
  needs.
- **Any match is the answer.** Every method accepts only two different
  indices whose values add up to `target`, and only one such pair exists.
- **`twoSumNestedForLoops`** examines every pair with `j > i`, so it reaches
  `(p, q)`; starting `j` at `i + 1` keeps the indices different and already
  in order.
- **`twoSumWithHashMap`** maps each value to its last index. At `i = p`, the
  map holds `q` for `target - nums[p]`: either `nums[q]` occurs only at `q`,
  or `nums[p] == nums[q]` and `q` is that value's last index. The `i != j`
  check rejects pairing an element with itself, and the result is sorted
  before being returned.
- **`twoSumWithHashMapOnePass`** looks up before inserting, so at step `i` the
  map holds only indices smaller than `i`. At `i = q`, `nums[p]` is in the map
  with index `p`, because `p` is the only earlier occurrence of that value.
  The match is therefore always at an earlier index: the indices cannot be
  equal, and the earlier one comes first.
- **No overflow.** `|nums[i]|` and `|target|` are at most 10⁷, so
  `nums[i] + nums[j]` and `target - nums[i]` lie between −2·10⁷ and 2·10⁷,
  far inside the `int` range (±2,147,483,647).

## Time complexity

`n` is the length of the array.

| Method | Time | Why |
|---|---|---|
| `twoSumNestedForLoops` | O(n²) | Up to n(n − 1)/2 pairs of indices are checked. |
| `twoSumWithHashMap` | O(n) average | Two passes, with an O(1) average map operation per element. |
| `twoSumWithHashMapOnePass` | O(n) average | One pass, with an O(1) average lookup and insertion per element. |

## Space complexity

The returned two-element array is output and is not counted.

| Method | Extra space | Why |
|---|---|---|
| `twoSumNestedForLoops` | O(1) | Only loop indices. |
| `twoSumWithHashMap` | O(n) | A map with at most one entry per distinct value; keys and values are boxed `Integer`s. |
| `twoSumWithHashMapOnePass` | O(n) | The same map, holding only the elements before the second element of the pair. |

## Early exit

| Method | Can skip input? | When |
|---|---|---|
| `twoSumNestedForLoops` | Yes | Returns as soon as it reaches `(p, q)`; pairs with a first index after `p` are never examined. |
| `twoSumWithHashMap` | No | The first pass always reads the whole array; only the second pass stops, at `i = p`. |
| `twoSumWithHashMapOnePass` | Yes | Returns at `i = q`, having read and stored only the elements before it. |

## Choosing an implementation

`twoSum` delegates to `twoSumWithHashMapOnePass`. Compared with the nested
loops, it trades memory for time: O(n) average time instead of O(n²), at the
cost of an O(n) map of boxed `Integer` keys and values. Compared with the
two-pass version, it has the same asymptotic cost but stops at the second
element of the pair, and its map holds only the elements seen before it.
Under these constraints (n ≤ 1,000), the allocation-free nested loops may be
competitive in practice; that would need measuring.

## Follow-ups

### Variations

- What if several pairs can add up to the target, and you must return a
  specific one (for example, the pair with the smallest indices) or all of
  them?

### Alternative approaches

- Sorting loses the original indices the answer needs. How could a sorting
  approach carry each value's index through the sort (for example, by packing
  value and index into one `long`), how would it then find the pair in the
  sorted order, and what would it cost compared with the hash map?

<!--
Related problems and likely interview follow-up questions, grouped by theme
(variations, constraints, scale, Java specifics). Questions are listed
without answers; add a short answer once a follow-up has been worked through.

## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
