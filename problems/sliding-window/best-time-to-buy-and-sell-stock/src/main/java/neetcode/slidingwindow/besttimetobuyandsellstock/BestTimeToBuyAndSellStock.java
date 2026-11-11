package neetcode.slidingwindow.besttimetobuyandsellstock;

class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        return maxProfitSinglePass(prices);
    }

    int maxProfitNestedLoop(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }

        return maxProfit;
    }

    int maxProfitSinglePass(int[] prices) {
        int maxProfit = 0;
        int minSoFar = prices[0];

        for (int i = 1; i < prices.length; i++) {
            minSoFar = Math.min(minSoFar, prices[i]);
            int profit = prices[i] - minSoFar;
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }
}
