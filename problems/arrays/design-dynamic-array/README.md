# Design Dynamic Array

**Topic:** Arrays · **Difficulty:** Easy

## Summary

Build a resizable `int` array, like a minimal `ArrayList`. It starts with a
given capacity (> 0), supports get/set on existing elements, push and pop at
the end, and doubles its capacity when a push finds it full. Size (elements
stored) and capacity (slots available) are reported separately.

## Approach

A backing `int[]` holds the elements and a `size` counter tracks how many
slots are in use; the capacity is the array's length. `pushback` writes at
index `size` and increments it, `popback` decrements it and returns the
element that was at the end. When a push finds the array full, `resize`
allocates an array twice as long and copies the existing elements with
`System.arraycopy`. Invalid inputs are rejected with the same exceptions the
JDK collections use: `IllegalArgumentException` for a non-positive capacity,
`IndexOutOfBoundsException` for an index outside `[0, size)`, and
`NoSuchElementException` for popping an empty array.

## Correctness

Every operation preserves two invariants:

- `0 <= size <= capacity`.
- Slots `[0, size)` hold exactly the pushed and not yet popped elements, in
  push order; slots `[size, capacity)` are unused.

`get` and `set` accept only indices in `[0, size)`, so they never read or
overwrite an unused slot, including one vacated by `popback`. `resize` copies
slots `[0, size)` unchanged and leaves `size` as it is, so the second invariant
survives growth, and the push that triggered it then has a free slot at index
`size`.

## Complexity

- **Time:** `get`, `set`, `popback`, `getSize`, and `getCapacity` are O(1).
  `pushback` is O(1) amortized: a resize copies all n elements, but because
  the capacity doubles, the copies across n pushes total less than 2n, so n
  pushes cost O(n) overall. A single push that triggers a resize is O(n).
- **Space:** O(capacity) for the backing array. With doubling and no
  shrinking, the capacity is O(the maximum size reached), not necessarily
  O(the current size): popping elements never releases memory. A resize
  temporarily holds both the old and the new array.

## Follow-ups

### Related problems

- **Min Stack**
- **Design Circular Queue**
- **Design Circular Deque**
- **Design HashMap**

### Variations

- Shrink the capacity on `popback`: when should it shrink, and how do you
  avoid repeated resizing when pushes and pops alternate around the threshold?
- Insert and remove at an arbitrary index: what do they cost?
- How would you support push and pop at both ends in O(1)?
- A generic `DynamicArray<T>`: what changes, given generic array creation and
  references left behind in popped slots?

### Growth and limits

- How do a growth factor of 1.5, a factor of 2, and a fixed increment compare
  in amortized cost and wasted memory?
- What happens when doubling the capacity approaches `Integer.MAX_VALUE`?
- Can `pushback` be O(1) in the worst case, not just amortized?

### Java specifics

- How does `java.util.ArrayList` handle initial capacity, growth, and
  modification during iteration?
- What goes wrong if several threads call `pushback` concurrently, and how
  would you make it safe?
- How would you make the class `Iterable`?

## References

- [`System.arraycopy`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/System.html#arraycopy(java.lang.Object,int,java.lang.Object,int,int))
- [`ArrayList`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ArrayList.html):
  the JDK's resizable array, whose `add` runs in amortized constant time
