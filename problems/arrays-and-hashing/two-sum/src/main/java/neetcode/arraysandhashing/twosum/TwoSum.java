package neetcode.arraysandhashing.twosum;

import java.util.HashMap;
import java.util.Map;

class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        return twoSumWithHashMapOnePass(nums, target);
    }

    int[] twoSumNestedForLoops(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        throw new IllegalArgumentException("unexpected input without solution");
    }

    int[] twoSumWithHashMap(int[] nums, int target) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            Integer j = map.get(b);
            if (j != null && i != j) {
                return sortedIndices(i, j);
            }
        }

        throw new IllegalArgumentException("unexpected input without solution");
    }

    int[] twoSumWithHashMapOnePass(int[] nums, int target) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int b = target - nums[i];
            Integer j = map.get(b);
            if (j != null) {
                return sortedIndices(i, j);
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("unexpected input without solution");
    }

    private int[] sortedIndices(int i, int j) {
        return new int[]{Math.min(i, j), Math.max(i, j)};
    }
}
