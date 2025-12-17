import java.util.stream.Collectors;

class Solution {

    public HashSet<String> fn(String s1, String s2, int i, int j, int[][] dp, HashSet<String> ans, StringBuffer sb,
            HashMap<String, HashSet<String>> memo) {
        String key = i + "," + j;
        if (memo.containsKey(key))
            return memo.get(key);

        if (i == 0 || j == 0)
            return new HashSet<>(Arrays.asList(""));
        HashSet<String> currAns = new HashSet<>();
        char c1 = s1.charAt(i - 1);
        char c2 = s2.charAt(j - 1);

        if (c1 == c2) {
            HashSet<String> subSet = fn(s1, s2, i - 1, j - 1, dp, ans, sb, memo);
            for (String subString : subSet) {
                currAns.add(subString + c1);
            }
        } else {
            if (dp[i][j] == dp[i - 1][j]) {
                HashSet<String> subSet = fn(s1, s2, i - 1, j, dp, ans, sb, memo);
                for (String subString : subSet) {
                    currAns.add(subString);
                }
            }
            if (dp[i][j] == dp[i][j - 1]) {
                HashSet<String> subSet = fn(s1, s2, i, j - 1, dp, ans, sb, memo);
                for (String subString : subSet) {
                    currAns.add(subString);
                }
            }
        }
        memo.put(key, currAns);
        return currAns;

    }

    public List<String> allLCS(String s1, String s2) {
        // Code here
        int l1 = s1.length(), l2 = s2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);
                if (c1 == c2)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        HashSet<String> ans = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        HashMap<String, HashSet<String>> memo = new HashMap<>();
        return fn(s1, s2, l1, l2, dp, ans, sb, memo)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}