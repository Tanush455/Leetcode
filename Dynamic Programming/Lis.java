class Solution {
     public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] prevIndex = new int[n]; 
        Arrays.fill(dp, 1);
        Arrays.fill(prevIndex, -1);

        int maxLen = 1;
        int lastIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] > nums[prev] && dp[i] < 1 + dp[prev]) {
                    dp[i] = 1 + dp[prev];
                    prevIndex[i] = prev;
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }
        List<Integer> lis = new ArrayList<>();
        while (lastIndex != -1) {
            lis.add(nums[lastIndex]);
            lastIndex = prevIndex[lastIndex];
        }

        Collections.reverse(lis);
        System.out.println("Longest Increasing Subsequence: " + lis);

        return maxLen;
    }
}