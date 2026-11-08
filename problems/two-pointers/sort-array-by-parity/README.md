# Sort Array by Parity

**Topic:** Two Pointers · **Difficulty:** Easy

## Summary

Rearrange an array of non-negative integers so that every even value comes
before every odd value. Any arrangement with that property is accepted.

## Approach

`i` marks where the next even value goes and `j` scans the array from left to
right. Whenever `nums[j]` is even, it is swapped into position `i` and `i`
moves forward. The array is rearranged in place and returned.

## Correctness

The loop keeps three regions:

- `nums[0 .. i-1]` holds only even values;
- `nums[i .. j-1]` holds only odd values;
- `nums[j ..]` has not been examined yet.

Initially `i = j = 0`, so all three conditions hold trivially. If `nums[j]`
is odd, it already sits at the end of the odd region and only `j` advances. If
it is even, swapping it with `nums[i]`, the first odd value when `i < j`,
moves it to the end of the even region and the odd value to the end of the
odd region; then both indices advance. When `j` reaches the end, the
unexamined region is empty, so every even value precedes every odd value.
Swaps only exchange elements, so the result is a rearrangement of the input.

While the array starts with even values, `i == j` and the swap would exchange
an element with itself; the `i != j` check skips it at the cost of one
comparison per even value.

## Complexity

- **Time:** O(n): the loop examines each element exactly once, with at most
  one swap per element.
- **Space:** O(1) extra: only the two indices; the array is rearranged in
  place.

<!--
## Follow-ups

Related problems and likely interview follow-up questions, grouped by theme
(variations, constraints, scale, Java specifics). Questions are listed
without answers; add a short answer once a follow-up has been worked through.

## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
