package neetcode.twopointers.containerwithmostwater;

class ContainerWithMostWater {

    public int maxArea(int[] heights) {
        return maxAreaTwoPointers(heights);
    }

    int maxAreaTwoPointers(int[] heights) {
        int i = 0;
        int j = heights.length - 1;
        int maxWater = 0;

        while (i < j) {
            int water = Math.min(heights[i], heights[j]) * (j - i);
            maxWater = Math.max(water, maxWater);
            if (heights[i] < heights[j]) {
                i++;
            } else if (heights[i] > heights[j]) {
                j--;
            } else {
                i++;
                j--;
            }
        }

        return maxWater;
    }

    int maxAreaNestedLoops(int[] heights) {
        int maxWater = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int water = Math.min(heights[i], heights[j]) * (j - i);
                maxWater = Math.max(maxWater, water);
            }
        }

        return maxWater;
    }
}
