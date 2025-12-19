class Solution {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int sell = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            if (prices[i] - buy < 0) {
                buy = prices[i];
            } else {
                sell = Math.max(sell, prices[i] - buy);
            }
        }

        return sell;
    }
}