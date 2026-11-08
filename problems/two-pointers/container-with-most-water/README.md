# Container With Most Water

**Topic:** Two Pointers · **Difficulty:** Medium

## Summary

Given the heights of vertical bars at consecutive positions, choose two bars
so that the water held between them is as large as possible, and return that
amount: the distance between the bars times the height of the shorter one.

## Approach

The water held by bars `i < j` is `min(heights[i], heights[j]) * (j - i)`:
the shorter bar sets the level and the distance sets the width.

- **`maxAreaTwoPointers`:** start with `i` at the first bar and `j` at the
  last, and keep the largest area seen. Moving inward always shrinks the
  width, so a larger area needs a taller shorter bar: after each measurement,
  move the index at the shorter bar inward, or both indices when the bars have
  the same height. `maxArea` delegates to this version.
- **`maxAreaNestedLoops`:** for each `i` from the first bar, and each `j` from
  `i + 1` to the last, compute the area and keep the largest seen so far.

## Correctness

- **`maxAreaTwoPointers`:** say `heights[i] <= heights[j]`. Every other
  container that uses bar `i` pairs it with a bar strictly between `i` and
  `j`: its height is at most `heights[i]` and its width is smaller than
  `j - i`, so it holds no more water than the container just measured. Bar
  `i` therefore cannot be part of a better container and is discarded safely;
  symmetrically for `j`. When the heights are equal, the argument applies to
  both bars, so both are discarded. Each step discards only bars that cannot
  improve on the best area seen, so the best container is measured before the
  indices meet.
- **`maxAreaNestedLoops`:** it measures every pair `i < j`, so it sees the
  best one.

Heights are at most 10⁴ and widths below 10⁵, so every area is below 10⁹ and
fits in an `int`.

## Complexity

`n` is the number of bars.

| Method | Time | Extra space | Why |
|---|---|---|---|
| `maxAreaTwoPointers` | O(n) | O(1) | Each step moves at least one index inward, so the loop ends after at most n - 1 steps, when `i >= j`. Only a few local variables. |
| `maxAreaNestedLoops` | O(n²) | O(1) | Every pair of indices `i < j` is checked: n(n - 1)/2 pairs. Only a few local variables. |

<!--
## Follow-ups

Related problems and likely interview follow-up questions, grouped by theme
(variations, constraints, scale, Java specifics). Questions are listed
without answers; add a short answer once a follow-up has been worked through.

## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
