class Solution {
    int[] cuts;
    int l;
    Integer[][] dp;

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        this.cuts = cuts;
        l = cuts.length;
        dp = new Integer[l][l];
        return f(0, l - 1, 0, n);
    }

    private int f(int start, int end, int rodStart, int rodEnd) {
        if (start < 0 || start > end || end > l)
            return 0;
        if (dp[start][end] != null)
            return dp[start][end];
        int cuttingCost = rodEnd - rodStart;
        if (start == end)
            return cuttingCost;

        int ans = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int currentCost = f(start, i - 1, rodStart, cuts[i]) +
                    f(i + 1, end, cuts[i], rodEnd) + cuttingCost;
            ans = Math.min(ans, currentCost);
        }
        return dp[start][end] = ans;
    }
}