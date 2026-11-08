package neetcode.arraysandhashing.productsofarrayexceptself;

class ProductsOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        return productExceptSelfWithoutDivision(nums);
    }

    int[] productExceptSelfWithoutDivision(int[] nums) {
        if (nums.length < 2) {
            throw new IllegalArgumentException("invalid array length");
        }
        int[] prefix = new int[nums.length];
        prefix[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i - 1] * prefix[i - 1];
        }

        int[] suffix = new int[nums.length];
        suffix[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix[i] * suffix[i];
        }

        return result;
    }

    int[] productExceptSelfUsingDivision(int[] nums) {
        if (nums.length < 2) {
            throw new IllegalArgumentException("invalid array length");
        }
        int totalProductWithoutZero = 1;
        int zeroCount = 0;
        int singleZeroPosition = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                if (zeroCount == 1) {
                    singleZeroPosition = i;
                }
            } else {
                totalProductWithoutZero *= nums[i];
            }
        }

        int[] result = new int[nums.length];
        if (zeroCount > 1) {
            return result;
        }

        if (zeroCount == 1) {
            result[singleZeroPosition] = totalProductWithoutZero;
            return result;
        }


        for (int i = 0; i < nums.length; i++) {
            result[i] = totalProductWithoutZero / nums[i];
        }

        return result;
    }
}
