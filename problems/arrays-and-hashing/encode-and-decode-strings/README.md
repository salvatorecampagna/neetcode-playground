# Encode and Decode Strings

**Topic:** Arrays & Hashing · **Difficulty:** Medium

## Summary

Turn a list of strings into a single string, and turn that string back into
exactly the original list: the same strings, in the same order. The strings
may contain any of the 256 ASCII characters, and both the list and its
strings may be empty.

## Approach

The encoded string is a sequence of fixed-width decimal fields followed by raw
string content:

- **`encode`:** write the number of strings as 2 digits, then, for each
  string, its length as 3 digits followed by the string itself. Numbers are
  padded with leading zeros, so `["Java", "Gradle"]` becomes
  `02004Java006Gradle`.
- **`decode`:** read the 2-digit count, then, for each string, read the
  3-digit length `m` and take the next `m` characters with `substring`.

## Correctness

Every field has a known size: the count and the lengths have a fixed width,
and each string has exactly the length written before it. `decode` therefore
always knows where each field ends, and the encoded string can be split in
only one way. `decode` never looks inside a string to find where it ends, so
the content can be any characters, digits included.

The widths fit the constraints: fewer than 100 strings need 2 digits, and
lengths below 200 need 3 (the format itself allows up to 999). The digit
helpers reject a number that does not fit its width, a negative number, and a
non-digit character, instead of silently truncating or misreading them. Empty
cases stay distinct: `[]` encodes as `00`, `[""]` as `01000`, and `["", ""]`
as `02000000`.

## Complexity

`n` is the number of strings and `L` their total length. The encoded string
has 2 + 3n + L characters.

- **Time:** O(n + L) for both methods: each string is copied once, and each
  header costs a constant number of digits. The `n` term matters when strings
  are short; 99 empty strings still encode to 299 characters.
- **Space:** O(n + L): `encode` builds the encoded string, and `decode` builds
  the list of `n` strings with `L` characters in total.

## Follow-ups

### Constraints

- **What if the strings may contain any character?** The format already works:
  it never inspects the content, so no character needs to be reserved.
  Delimiter-based formats, by contrast, need a character the strings cannot
  contain, or an escaping scheme.
- **What if there can be 100 or more strings, or a string of 1,000 or more
  characters?** The fixed widths no longer fit. One option is a header with a
  fixed size that states the width of the length fields.
- Can a length field mark its own end, so that no header and no fixed width
  are needed?
- What should `decode` do with malformed or truncated input?

### Variations

- **Is the count needed?** No: `decode` can read length-and-string blocks
  until the end of the input. The count adds a consistency check at the cost
  of two characters.
- How much shorter is the encoding if each length is stored as a single
  `char` value instead of three decimal digits, and what does it cost in
  readability?

<!--
## References

Optional: only links that add value (Java API, JLS, algorithm sources).
-->
