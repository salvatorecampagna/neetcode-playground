package neetcode.arraysandhashing.containsduplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class ContainsDuplicate {

    // Time complexity: O(N) on average, where N is nums.length. Each add is O(1)
    // on average; resizes rehash the whole set, but because the capacity doubles,
    // their total cost over N inserts is O(N) (amortized).
    // Space complexity: O(N) in the worst case (no duplicates), when every
    // element is stored. Elements are boxed to Integer, so the constant factor is
    // much larger than for an int[] of the same length.
    public boolean (int[] nums) {
        // NOTE: the set is deliberately not presized to nums.length. Presizing
        // avoids rehashing when there are no duplicates, but allocates a table for
        // every element up front, even when a duplicate appears early and
        // Set#add returning false ends the loop.
        final Set<Integer> set = new HashSet<>();

        for (int n: nums) {
            if (!set.add(n)) {
                return true;
            }
        }

        return false;
    }

    // NOTE: sorting places equal values next to each other, so a single pass
    // over adjacent pairs finds any duplicate. The sort is in place: the caller's
    // array is modified.
    // Time complexity: O(N log N). Arrays.sort(int[]) (dual-pivot quicksort) is
    // O(N log N), which dominates the O(N) scan.
    // Space complexity: no auxiliary collection; any extra space is what
    // Arrays.sort uses internally, which its contract does not specify.
    public boolean hasDuplicateBySorting(int[] nums) {

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return true;
            }
        }

        return false;
    }

    // NOTE: same idea as hasDuplicateBySorting, but sorts a copy, so the caller's
    // array is left unchanged.
    // Time complexity: O(N log N). The O(N) copy and the O(N) scan are dominated
    // by the sort.
    // Space complexity: O(N) for the copy, plus what Arrays.sort uses internally.
    // Unlike the hash set, the copy is a primitive int[], with no boxing.
    public boolean hasDuplicateBySortingAndCopying(int[] nums) {
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        return hasDuplicateBySorting(copy);
    }
}
