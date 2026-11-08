package neetcode.twopointers.sortarraybyparity;

class SortArrayByParity {

    // Rearranges nums in place, evens first, and returns the same array.
    public int[] sortArrayByParity(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] % 2 == 0) {
                // Skips self-swaps (i == j only while the leading values are
                // even), at the cost of one comparison per even value.
                if (i != j) {
                    swap(nums, i, j);
                }
                i++;
            }
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
