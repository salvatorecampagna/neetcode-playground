# Valid Anagram

**Topic:** Arrays & Hashing · **Difficulty:** Easy

## Summary

Given two strings, return `true` if one is a rearrangement of the other: both
contain exactly the same characters, each the same number of times.

## Approach

Every method first returns `false` when the lengths differ.

- **`isAnagramUsingTwoMaps`:** count the characters of each string into a
  `HashMap<Character, Integer>` and compare the two maps.
- **`isAnagramBySorting`:** sort a `char[]` copy of each string and compare
  the sorted arrays.
- **`isAnagramUsingOneMap`:** count the characters of `s` into a map, then
  decrement the count of each character of `t`, returning `false` as soon as
  a count drops below zero and `true` if none does.
- **`isAnagramUsingTwoCountArrays`:** the two-map approach with an `int[26]`
  per string, indexed by `c - 'a'`, in place of each map: a histogram of the
  letters.
- **`isAnagramUsingOneCountArray`:** the one-map approach with a single
  `int[26]` in place of the map.

## Correctness

- **Comparing counts** (two maps, two count arrays): two strings are anagrams
  exactly when every character occurs the same number of times in both, which
  is what comparing the counts checks. The length check is only a shortcut:
  strings of different lengths always differ in some count.
- **Sorting:** sorting puts the characters of a string in a canonical order,
  so two strings are anagrams exactly when their sorted arrays are equal.
- **One counter** (one map, one count array): while reading `t`, each count
  holds the occurrences in `s` minus those seen so far in `t`. A negative
  count means `t` has more of that character than `s`, so returning `false`
  is correct; counts only decrease while reading `t`, so it cannot recover.
  Returning `true` at the end relies on the length check: equal lengths make
  the counts sum to zero, the early exit guarantees that none is negative, and
  counts that are all non-negative and sum to zero must all be zero.

## Input assumptions

| Method | Relies on | Behavior outside the constraint |
|---|---|---|
| `isAnagramUsingTwoMaps` | No assumption about the alphabet | Works for any character stored as a single `char` |
| `isAnagramBySorting` | No assumption about the alphabet | Works for any character stored as a single `char` |
| `isAnagramUsingOneMap` | No assumption about the alphabet | Works for any character stored as a single `char` |
| `isAnagramUsingTwoCountArrays` | Lowercase `a`–`z` only | Any other character throws `ArrayIndexOutOfBoundsException` |
| `isAnagramUsingOneCountArray` | Lowercase `a`–`z` only | Any other character throws `ArrayIndexOutOfBoundsException` |

## Time complexity

`n` is the length of the strings (both have the same length once the length
check passes); `k` is the number of distinct characters.

| Method | Time | Why |
|---|---|---|
| `isAnagramUsingTwoMaps` | O(n) average | One pass over each string with an O(1) average `merge` per character; `Map.equals` then compares at most `k` entries. |
| `isAnagramBySorting` | O(n log n) | Sorting both copies dominates; the O(n) comparison does not change the bound. See the note on `Arrays.sort(char[])` below. |
| `isAnagramUsingOneMap` | O(n) average | One pass over each string with an O(1) average `merge` per character. |
| `isAnagramUsingTwoCountArrays` | O(n) | One pass over each string with an O(1) array update per character; `Arrays.equals` compares 26 counts. |
| `isAnagramUsingOneCountArray` | O(n) | One pass over `s` to count and one over `t` to decrement, O(1) per character. |

The documented contract of
[`Arrays.sort(char[])`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Arrays.html#sort(char%5B%5D))
gives O(n log n). The JDK 25 implementation does better for long inputs:
[`DualPivotQuicksort`](https://github.com/openjdk/jdk/blob/jdk-25-ga/src/java.base/share/classes/java/util/DualPivotQuicksort.java)
switches to a counting sort over all 65,536 `char` values for arrays longer
than 1,750 elements, which runs in O(n + 65,536). This is an implementation
detail, not part of the contract, so the stated bound stays O(n log n).

## Space complexity

| Method | Extra space | Why |
|---|---|---|
| `isAnagramUsingTwoMaps` | O(k), O(1) for `a`–`z` | Two maps with at most `k` entries each; `charAt` reads the strings without copying them. |
| `isAnagramBySorting` | O(n), plus the sort's internal memory | Two `char[]` copies from `toCharArray()`, required because a `String` is immutable and the sort works in place. |
| `isAnagramUsingOneMap` | O(k), O(1) for `a`–`z` | One map with at most `k` entries. |
| `isAnagramUsingTwoCountArrays` | O(1) | Two `int[26]` arrays, independent of `n`. |
| `isAnagramUsingOneCountArray` | O(1) | One `int[26]` array. |

- **Map entries are boxed.** Keys are `Character` and counts are `Integer`
  objects. Counts above 127 fall outside the
  [`Integer.valueOf`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Integer.html#valueOf(int))
  cache, so each `merge` on a large count allocates a new short-lived
  `Integer`. This adds allocation and garbage-collection work, not retained
  space.
- **The sort's constant can be large.** On the counting-sort path, each call
  allocates an `int[65536]` (256 KB). That is O(1) in `n`, but about 2,500
  times the 104 bytes of an `int[26]`.

## Early exit

Every method returns `false` immediately when the lengths differ, before
reading any character. For strings of equal length:

| Method | Can skip input? | When |
|---|---|---|
| `isAnagramUsingTwoMaps` | No | Both strings are counted in full; `Map.equals` then stops at the first differing entry. |
| `isAnagramBySorting` | No | Both copies are sorted in full; `Arrays.equals` then stops at the first differing position. |
| `isAnagramUsingOneMap` | Yes | While reading `t`, as soon as a count drops below 0. |
| `isAnagramUsingTwoCountArrays` | No | Both strings are counted in full; `Arrays.equals` then stops at the first differing count. |
| `isAnagramUsingOneCountArray` | Yes | While reading `t`, as soon as a count drops below 0. |

A negative count means `t` holds more of that character than `s`, whether `s`
has none of it (`"abc"`, `"abd"`) or fewer (`"aab"`, `"abb"`). Counts only
decrease while reading `t`, so a negative count can never return to 0 and the
answer is already `false`.

## Choosing an implementation

Apart from sorting, every method runs in O(n) time and, under the `a`–`z`
constraint, O(1) extra space, so asymptotic complexity does not separate them;
their assumptions and constant factors do. A count array costs one index
computation and one increment per character on 104 contiguous bytes, while a
map hashes, boxes, and allocates entry objects. The maps, however, make no
assumption about the alphabet: their space is O(k) for any input, whereas a
count array needs a slot for every possible character and throws
`ArrayIndexOutOfBoundsException` outside `a`–`z`. Sorting is O(n log n) by
contract and copies both strings.

`isAnagram` delegates to `isAnagramUsingTwoCountArrays`. It fits the
constraints, and its whole working set is two 104-byte arrays that stay in
the CPU cache while each string is read once, front to back, instead of map
entries scattered across the heap. Its counting loops have no
data-dependent exit, so every iteration does the same work, a loop shape
that is simple for the JIT compiler to optimize; the cost is giving up the
early exit of `isAnagramUsingOneCountArray`. Comparing two count arrays is
also directly the definition of an anagram, so its correctness needs no
further argument. Speed differences between the linear methods should be
measured rather than inferred.

## Follow-ups

### Constraints

- **What changes if the input can contain any Unicode character?** A `char`
  is a UTF-16 code unit, and characters outside the Basic Multilingual Plane,
  such as most emoji, are stored as two `char`s
  ([supplementary characters](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Character.html#supplementary)).
  The map and sorting versions count code units, so two strings can share the
  same code units without sharing the same characters: 😀🤀 (`D83D DE00`
  `D83E DD00`) and 🔀 followed by U+1FA00 (`D83D DD00` `D83E DE00`) pair the
  same four halves differently, and all three methods return `true`. Counting code
  points with
  [`String.codePoints()`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/String.html#codePoints())
  into a `Map<Integer, Integer>` fixes this. The count arrays do not
  generalize: they would need a slot for each of the 1,114,112 code points.

<!--
Related problems and likely interview follow-up questions, grouped by theme
(variations, constraints, scale, Java specifics). Questions are listed
without answers; add a short answer once a follow-up has been worked through.

## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
