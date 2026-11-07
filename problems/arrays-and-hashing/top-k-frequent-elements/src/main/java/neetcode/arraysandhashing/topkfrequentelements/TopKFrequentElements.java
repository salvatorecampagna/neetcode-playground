package neetcode.arraysandhashing.topkfrequentelements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.merge(num, 1, (existing, incoming) -> existing + 1);
        }

        final List<List<Integer>> counts = new ArrayList<>(nums.length + 1);
        for (int i = 0 ; i < nums.length + 1; i++) {
            counts.add(new ArrayList<>());
        }
        for (final Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            counts.get(count).add(value);
        }

        int[] top = new int[k];
        int r = 0;
        for (int i = counts.size() - 1; i > 0; i--) {
            final List<Integer> values = counts.get(i);
            for (Integer value : values) {
                if (r >= k) {
                    break;
                }
                top[r++] = value;
            }
        }

        return top;
    }
}
