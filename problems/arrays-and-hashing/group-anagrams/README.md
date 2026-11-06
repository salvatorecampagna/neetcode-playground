# Group Anagrams

**Topic:** Arrays & Hashing · **Difficulty:** Medium

## Summary

Given an array of strings, split it into groups so that two strings are in the
same group exactly when they are anagrams of each other. The groups, and the
strings within each group, may be returned in any order.

## Approach

Both methods map each string to a key built from its 26 letter counts,
counted into a `char[26]`, and collect strings with equal keys into one list
with `computeIfAbsent(key, k -> new ArrayList<>()).add(str)`. `groupAnagrams`
delegates to the string-key version.

- **`groupAnagramsWithArrayAsKey`:** the key wraps the counts in a `Key` class
  whose `equals` and `hashCode` use the array's contents, because arrays
  compare by identity and would never match as map keys. `Key` computes its
  hash once, in the constructor; `equals` compares the cached hashes first and
  compares the counts only when the hashes match.
- **`groupAnagramsWithStringAsKey`:** the key is `new String(counts)`, a
  26-character string with one character per count. `String` already provides
  content-based `equals` and `hashCode`, and caches its hash.

## Correctness

Two strings are anagrams exactly when every letter occurs the same number of
times in both, that is, when their count arrays are equal.

- **Array key:** `Key.hashCode` is computed from the counts, so equal keys
  have equal hashes, as `HashMap` requires. `Key.equals` returns `true` only
  when the counts are equal element by element: different hashes prove the
  keys differ, but equal hashes do not prove them equal, because different
  counts can collide (one `y` and thirty-one `z`s, for instance).
- **String key:** each count occupies exactly one character, so the encoding
  has a fixed width and two keys are equal exactly when the counts are equal.
  A variable-width encoding, such as writing the counts as decimal numbers
  without separators, could map different counts to the same string.

Either way, the map puts two strings in the same list exactly when they are
anagrams. Every string is added to exactly one list, so the groups partition
the input, duplicates and empty strings included. A `char` holds counts up to
65,535, well above the 100 characters a string can have.

## Complexity

`n` is the number of strings, `L` their total length, and `k` the number of
groups, with `k <= n`. Both methods have the same bounds.

- **Time:** O(L + 26·n) on average. Counting a string's letters is linear in
  its length; allocating its count array and building and hashing its key,
  once, each cost O(26), and so does comparing two keys whose hashes match.
  Map operations are O(1) on average and appending to an `ArrayList` is
  amortized O(1). The 26 per string matters for short strings: an empty string
  still costs a full key.
- **Space:** O(n + 26·k), which is O(n). The map holds `k` keys of 26 counts;
  the lists hold `n` references to the input strings, which are not copied;
  the result list holds `k` references. Every string allocates a count array
  (and, for the string key, a string), discarded immediately when its group
  already exists.

## Choosing an implementation

`groupAnagrams` delegates to `groupAnagramsWithStringAsKey`. Both are correct
with the same bounds; the string key needs no custom class, relies on the
JDK's `String.equals` and cached `String.hashCode`, and, since every count is
below 256, can be stored compactly at one byte per character. It costs one
extra copy of the 26 counts per string. Which is faster in practice would need
measuring.

<!--
## Follow-ups

Related problems and likely interview follow-up questions, grouped by theme
(variations, constraints, scale, Java specifics). Questions are listed
without answers; add a short answer once a follow-up has been worked through.

## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
