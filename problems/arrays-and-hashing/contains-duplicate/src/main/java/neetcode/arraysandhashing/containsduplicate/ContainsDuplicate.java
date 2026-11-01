package neetcode.arraysandhashing.containsduplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class ContainsDuplicate {

    public boolean hasDuplicate(int[] nums) {
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
    public boolean hasDuplicateBySortingAndCopying(int[] nums) {
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        return hasDuplicateBySorting(copy);
    }
}
