package neetcode.twopointers.twointegersumii;

class TwoIntegerSumII {

    public int[] twoSum(int[] nums, int target) {
        return twoSumTwoPointers(nums, target);
    }

    int[] twoSumTwoPointers(int[] nums, int target) {
        if (nums.length <= 1) {
            throw new IllegalArgumentException("invalid array");
        }
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[] {i + 1, j + 1};
            } else if (sum < target) {
                i += 1;
            } else {
                j -= 1;
            }
        }

        // NOTE: unreachable for valid input: exactly one solution exists
        throw new IllegalArgumentException("no solution");
    }

    int[] twoSumNestedForLoops(int[] nums, int target) {
        if (nums.length <= 1) {
            throw new IllegalArgumentException("invalid array");
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }

        // NOTE: unreachable for valid input: exactly one solution exists
        throw new IllegalArgumentException("no solution");
    }
}
