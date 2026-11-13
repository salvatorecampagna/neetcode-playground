# Valid Palindrome

**Topic:** Two Pointers · **Difficulty:** Easy

## Summary

Decide whether a string reads the same forwards and backwards when only its
letters and digits are considered, ignoring case.

## Approach

Two indices move inward from both ends of the string. Each one skips
characters that are not letters or digits; the two characters it stops on are
compared case-insensitively. The method returns `false` at the first
mismatch, and `true` when the indices meet.

## Correctness

Skipped characters do not count, so the string is a palindrome exactly when
its letters and digits, read left to right, equal the same sequence read
right to left, ignoring case. Each comparison pairs the k-th counted
character from the left with the k-th from the right. A mismatch proves the
string is not a palindrome. When the indices meet, every pair has matched, and
a middle character left over in an odd-length sequence needs no partner.

## Complexity

`n` is the length of the string.

- **Time:** O(n): each character is visited once, either skipped or compared.
- **Space:** O(1) extra: characters are read in place with `charAt` and
  lowercased one at a time, without building a lowercase or filtered copy of
  the string.

<!--
## Follow-ups

Related problems and likely interview follow-up questions, grouped by theme
(variations, constraints, scale, Java specifics). Questions are listed
without answers; add a short answer once a follow-up has been worked through.

## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
